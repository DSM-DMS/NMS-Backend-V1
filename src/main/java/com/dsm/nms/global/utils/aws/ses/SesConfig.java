package com.dsm.nms.global.utils.aws.ses;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.dsm.nms.global.utils.aws.AwsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class SesConfig {

    private final AwsProperties awsProperties;

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        final BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsProperties.getAccessKey(), awsProperties.getSecretKey());
        final AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);

        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withRegion(awsProperties.getRegion())
                .build();
    }
}
