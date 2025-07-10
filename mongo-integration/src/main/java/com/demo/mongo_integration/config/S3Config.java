package com.demo.mongo_integration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Value("${aws.accessKeyId:dummyAccessKeyId}")
    private String accessKeyId;

    @Value("${aws.secretKey:dummySecretKey}")
    private String secretKey;

    @Value("${aws.region:us-east-1}")
    private String region;

    @Bean
    public S3Client s3Client() {
        Region awsRegion;
        if (region == null || region.trim().isEmpty()) {
            awsRegion = Region.US_EAST_1; // Default to US_EAST_1 if region is blank or null
        } else {
            awsRegion = Region.of(region);
        }

        String awsAccessKeyId = accessKeyId.isBlank() ? "dummyAccessKeyId" : accessKeyId;
        String awsSecretKey = secretKey.isBlank() ? "dummySecretKey" : secretKey;

        return S3Client.builder()
                .region(awsRegion)
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(awsAccessKeyId, awsSecretKey)))
                .build();
    }
}
