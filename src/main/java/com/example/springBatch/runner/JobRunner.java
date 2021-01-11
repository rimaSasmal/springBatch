package com.example.springBatch.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.batch.core.*;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JobRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobRunner.class);

    @Value("${fileName}")
    String fileName;

    @Autowired
    private JobLauncher simpleJobLauncher;
    @Autowired
    private Job job1;

    public void runBatchJob() {
        runJob(job1, createJobParameter().toJobParameters());
    }

    JobParametersBuilder createJobParameter(){
        System.out.println("fileNmae :"+fileName);
        JobParametersBuilder jpb = new JobParametersBuilder();
        jpb.addString("fileName",fileName);
        return jpb;
    }

    public void runJob(Job job, JobParameters parameters) {
        try {
            JobExecution jobExecution = simpleJobLauncher.run(job, parameters);
        } catch (JobExecutionAlreadyRunningException e) {
            LOGGER.info("Job with fileName={} is already running.", parameters.getParameters().get("fileName"));
        } catch (JobRestartException e) {
            LOGGER.info("Job with fileName={} was not restarted.", parameters.getParameters().get("fileName"));
        } catch (JobInstanceAlreadyCompleteException e) {
            LOGGER.info("Job with fileName={} already completed.", parameters.getParameters().get("fileName"));
        } catch (JobParametersInvalidException e) {
            LOGGER.info("Invalid job parameters.", parameters.getParameters().get("fileName"));
        }
    }
}
