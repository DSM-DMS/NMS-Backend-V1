package com.dsm.nms.global.batch;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class S3Scheduler {

    @Scheduled(cron = "0 0 0 1/1 * ? *")
    public void removeFiles() {

    }

}
