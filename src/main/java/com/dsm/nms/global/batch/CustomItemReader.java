package com.dsm.nms.global.batch;

import com.dsm.nms.domain.image.entity.Image;
import com.dsm.nms.global.utils.aws.s3.S3Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class CustomItemReader {

    private static final int chunkSize = 100;

    private final S3Util s3Util;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private Set<String> keys = new HashSet<>();
    private final Map<String, String> images = new HashMap<>();

    // job
    @Bean
    public Job jpaPagingItemJob() {
        return jobBuilderFactory.get("jpaPagingItemJob")
                .start(jpaPagingItemStep())
                .build();
    }

    // step
    @Bean
    public Step jpaPagingItemStep() {
        return stepBuilderFactory.get("jpaPagingItemStep")
                .<Image, Image>chunk(chunkSize)
                .reader(reader())
//                .processor(processor())
                .writer(writer())
                .build();
    }

    // item reader
    @Bean
    public JpaPagingItemReader<Image> reader() {
        return new JpaPagingItemReaderBuilder<Image>()
                .name("jpaPagingItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString("SELECT i FROM Image i Order by id")
                .build();
    }

    // item processor
//    @Bean
//    @StepScope
//    public ItemProcessor<List<Image>, Map<String, String>> processor() {
//        return list -> {
//            for (Image image : list) {
//                images.put(image.getImagePath(), null);
//            }
//            return images;
//        };
//    }

    // item writer
    @Bean
    public ItemWriter<Image> writer() {
        return list -> {
            keys = s3Util.getKeys();

            for (Image image : list) {
                images.put(image.getImagePath(), null);
            }

            for (String imagePath : keys) {
                if(!images.containsKey(imagePath))
                    s3Util.removeFile(imagePath);
            }
        };
    }

}
