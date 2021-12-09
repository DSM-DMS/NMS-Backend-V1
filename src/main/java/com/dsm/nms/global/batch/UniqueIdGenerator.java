package com.dsm.nms.global.batch;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UniqueIdGenerator extends RunIdIncrementer {

    private static final String key = "job.id";
    private static final Long default_value = 0L;

    @Override
    public JobParameters getNext(@Nullable JobParameters parameters) {
        JobParameters jobParameters = new JobParameters();
        return new JobParametersBuilder()
                .addLong(key, jobParameters.getLong(key, default_value) + 1)
                .addDate("date", new Date())
                .toJobParameters();
    }

}
