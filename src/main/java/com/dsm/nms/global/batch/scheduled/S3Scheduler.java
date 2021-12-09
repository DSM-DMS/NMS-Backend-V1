package com.dsm.nms.global.batch.scheduled;

import com.dsm.nms.global.batch.UniqueIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class S3Scheduler {

    private final Job job;
    private final JobLauncher jobLauncher;
    private final UniqueIdGenerator uniqueIdGenerator;

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void cleanFiles()
            throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        jobLauncher.run(job, uniqueIdGenerator.getNext(null));
    }

}
