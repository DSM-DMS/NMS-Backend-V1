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
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class BatchConfig {

    private static final int chunkSize = 100;

    private final S3Util s3Util;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private Set<String> keys = new HashSet<>();
    private final Map<String, String> images = new HashMap<>();

    @Bean
    public Job jpaPagingItemJob() {
        return jobBuilderFactory.get("jpaItemJob")
                .start(jpaPagingItemStep())
                .build();
    }

    @Bean
    public Step jpaPagingItemStep() {
        return stepBuilderFactory.get("jpaItemStep")
                .<Image, Image>chunk(chunkSize)
                .reader(reader())
                .writer(compositeItem())
                .build();
    }

    @Bean
    public JpaPagingItemReader<Image> reader() {
        return new JpaPagingItemReaderBuilder<Image>()
                .name("jpaPagingItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString("SELECT i FROM Image i Order by id")
                .build();
    }

    @Bean
    public CompositeItemWriter<Image> compositeItem() {
        final CompositeItemWriter<Image> compositeItemWriter = new CompositeItemWriter<>();
        compositeItemWriter.setDelegates(Arrays.asList(getImages(), cleanS3()));
        return compositeItemWriter;
    }

    @Bean
    public ItemWriter<Image> getImages() {
        return list -> {
            for (Image image : list) {
                images.put(image.getImagePath(), null);
            }
        };
    }

    @Bean
    public ItemWriter<Image> cleanS3() {
        return list -> {
            keys = s3Util.getKeys();

            for (String imagePath : keys) {
                if(!images.containsKey(imagePath))
                    s3Util.removeFile(imagePath);
            }
        };
    }

}
