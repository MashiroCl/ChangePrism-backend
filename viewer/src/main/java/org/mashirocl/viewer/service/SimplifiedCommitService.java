package org.mashirocl.viewer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.mashirocl.visualize.SimplifiedCommit;
import org.mashirocl.visualize.SimplifiedUtilCommit;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mashirocl@gmail.com
 * @since 2024/05/05 13:10
 */
@Slf4j
@Service
public class SimplifiedCommitService {
    public List<SimplifiedUtilCommit> getAllSimplifiedCommits(){
        try{
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mbassador.json");
            if (inputStream == null) {
                throw new FileNotFoundException("Resource file 'mbassador.json' not found in the classpath");
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inputStream, new TypeReference<List<SimplifiedUtilCommit>>() {});
        }
        catch (IOException e){
            log.error(e.getMessage(), e);
        }
        return new LinkedList<>();
    }

    public SimplifiedUtilCommit getSingleSimplifiedCommit(String sha1){
        List<SimplifiedUtilCommit> simplifiedCommits = getAllSimplifiedCommits();
        for(SimplifiedUtilCommit simplifiedCommit: simplifiedCommits){
            if(simplifiedCommit.getSha1().equals(sha1)){
                return simplifiedCommit;
            }
        }
        return null;
    }
}
