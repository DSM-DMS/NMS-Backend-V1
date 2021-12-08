package com.dsm.nms.global.batch;

import com.dsm.nms.domain.image.entity.Image;
import com.dsm.nms.global.utils.aws.s3.S3Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class CustomItemReader {

    private static final int chunkSize = 100;

    private final S3Util s3Util;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public Job jpaPagingItemReaderJob() {
        return jobBuilderFactory.get("jpaPagingItemReaderJob")
                .start(jpaPagingItemReaderStep())
                .build();
    }

    @Bean
    public Step jpaPagingItemReaderStep() {
        return stepBuilderFactory.get("jpaItemReaderStep")
                .<Image, Image>chunk(chunkSize)
                .reader(jpaPagingItemReader())
                .writer(jpaPagingItemWriter())
                .build();
    }

    @Bean
    public JpaPagingItemReader<Image> jpaPagingItemReader() {
        return new JpaPagingItemReaderBuilder<Image>()
                .name("jpaPagingItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString("SELECT i FROM Image i")
                .build();
    }

    private ItemWriter<Image> jpaPagingItemWriter() {
        return list -> {
            for (Image image : list) {
                log.info("Current Image={}", image.getImagePath());
            }
        };
    }

//    @Bean
//    public Job tutorialJob() {
//        return jobBuilderFactory.get("imageJob")
//                .start(step1())
//                    .on("FAILED")
//                    .to(step3())
//                    .on("*")
//                    .end()
//                .from(step1())
//                    .on("*")
//                    .to(step2())
//                    .next(step3())
//                    .on("*")
//                    .end()
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("getImageStep")
//                .tasklet((contribution, chunkContext) -> {
//                    log.info("get image step");
//
//                    images = imageRepository.findAll().stream()
//                            .map(Image::getImagePath)
//                            .collect(toList());
//
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step2() {
//        return stepBuilderFactory.get("removeImageStep")
//                .tasklet((contribution, chunkContext) -> {
//                    log.info("remove image step");
//
//                    s3Util.removeFromS3();
//
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step3() {
//        return stepBuilderFactory.get("lastStep")
//                .tasklet((contribution, chunkContext) -> {
//                    log.info("last Step");
//
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }

}
