package com.dsm.nms.global.utils.aws;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "aws.credentials")
public class AwsProperties {

    private final String accessKey;
    private final String secretKey;

}
