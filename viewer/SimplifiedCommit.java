@NoArgsConstructor
@Slf4j
public class SimplifiedCommit {
    @JsonProperty("repository")
    private String repository;
    @JsonProperty("sha1")
    private String sha1;
    @JsonProperty("url")
    private String url;
    @JsonProperty("preChangeSourceCode")
    private Map<String, String> preChangeSourceCode;
    @JsonProperty("postChangeSourceCode")
    private Map<String, String> postChangeSourceCode;

    @JsonProperty("preChangeRange")
    private Map<String, List<List<Integer>>> preChangeRange;
    @JsonProperty("postChangeRange")
    private Map<String, List<List<Integer>>> postChangeRange;

    @JsonProperty("microChanges")

    private List<SpecialChange> microChanges;
    @JsonProperty("refactorings")

    private List<SpecialChange> refactorings;


    public SimplifiedCommit(Commit commit){
        this.repository = commit.getRepository();
        this.sha1 = commit.getSha1();
        this.url = commit.getUrl();
        this.preChangeSourceCode = commit.getPreChangeSourceCode();
        this.postChangeSourceCode = commit.getPostChangeSourceCode();
        this.preChangeRange = new HashMap<>();
        this.postChangeRange = new HashMap<>();
        for(String filePath:commit.getChangeRanges().getLeftSide().keySet()){
            this.preChangeRange.put(filePath,
                    commit.getChangeRanges().getLeftSide().get(filePath).asRanges().stream()
                            .map(r->List.of(r.lowerEndpoint(),r.upperEndpoint())).collect(Collectors.toList()));
        }
        for(String filePath:commit.getChangeRanges().getRightSide().keySet()){
            this.postChangeRange.put(filePath,
                    commit.getChangeRanges().getRightSide().get(filePath).asRanges().stream()
                            .map(r->List.of(r.lowerEndpoint(),r.upperEndpoint())).collect(Collectors.toList()));
        }
        this.microChanges = new LinkedList<>();
        commit.getMicroChanges().forEach(p->this.microChanges.add(new SpecialChange(p)));
        this.refactorings = new LinkedList<>();
        commit.getRefactorings().forEach(p->this.refactorings.add(new SpecialChange(p)));
    }
}