package com.dsm.nms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@ConfigurationPropertiesScan
@SpringBootApplication
public class NmsBackendV1Application {

    public static void main(String[] args) {
        SpringApplication.run(NmsBackendV1Application.class, args);
    }

}
