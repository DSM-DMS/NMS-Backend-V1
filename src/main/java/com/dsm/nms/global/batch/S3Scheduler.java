package com.dsm.nms.global.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class S3Scheduler {

    private final CustomItemReader customItemReader;

    @Scheduled(cron = "0 0 0 1/1 * ? *")
    public void cleanFiles() {
        customItemReader.jpaPagingItemReaderJob();
    }

}
