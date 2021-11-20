package com.dsm.nms.global.utils.aws.ses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "aws.ses")
public class AwsSesProperties {

    private final String accessKey;
    private final String secretKey;
    private final String region;
    private final String baseEmail;

}
