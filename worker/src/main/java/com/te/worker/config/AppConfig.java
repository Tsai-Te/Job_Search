package com.te.worker.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.sendgrid.SendGrid;
import com.te.worker.service.jms.MessageSQSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

@Configuration
@Profile({"dev", "test", "stage", "prod"})
public class AppConfig {
    private AmazonSQS sqs;
    private String queueUrl;

    @Value("${sendgrid.apiKey}")
    private String apiKey;

    @Bean
    public MessageSQSService getSQSService() throws IOException{
        AmazonSQS sqs= AmazonSQSClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();
//        String queueUrl = beanFactory.getObject().getProperty("amazon.sqs.url");
        MessageSQSService messageSQSService = new MessageSQSService(sqs,queueUrl);
        return messageSQSService;
    }
//
    @Bean
    public SendGrid getSendGridBean(){
        return new SendGrid(apiKey);
    }

    @Bean
    public AmazonSQS getAmazonSQSBean() {
        return AmazonSQSClientBuilder
                .standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
    }
}
