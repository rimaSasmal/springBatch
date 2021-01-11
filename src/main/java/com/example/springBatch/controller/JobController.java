package com.example.springBatch.controller;

import com.example.springBatch.runner.JobRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobController")
public class JobController {
    @Autowired
    private JobRunner jobRunner;


    @RequestMapping(value = "/job")
    public String runJob() {
        jobRunner.runBatchJob();
        return "Data Dumped Successfully";
    }
}
