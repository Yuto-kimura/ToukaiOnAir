package com.kawakawaryuryu.samplespringbatch.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "key")
public class ToukaiEntity {
    private String accessToken;
    private String accessTokenSecret;
    private String apiKey;
    private String apiSecretKey;
}
