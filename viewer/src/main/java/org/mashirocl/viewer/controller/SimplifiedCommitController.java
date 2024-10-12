package org.mashirocl.viewer.controller;

import lombok.extern.slf4j.Slf4j;
import org.mashirocl.viewer.service.SimplifiedCommitService;
import org.mashirocl.visualize.SimplifiedCommit;
import org.mashirocl.visualize.SimplifiedUtilCommit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mashirocl@gmail.com
 * @since 2024/05/05 13:30
 */
@Slf4j
@RestController
public class SimplifiedCommitController {
    @Autowired
    private SimplifiedCommitService simplifiedCommitService;

    @GetMapping({"/commits", "/commits/"})
    public List<SimplifiedUtilCommit> getAllSimplifiedCommits(){
        return simplifiedCommitService.getAllSimplifiedCommits();
    }

    @GetMapping("/commits/{sha1}")
    public SimplifiedUtilCommit getSimplifiedCommit(@PathVariable String sha1){
        log.info("commits/sha1 {}", sha1);
        return simplifiedCommitService.getSingleSimplifiedCommit(sha1);
    }


}
