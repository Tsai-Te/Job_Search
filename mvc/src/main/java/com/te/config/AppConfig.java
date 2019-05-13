package com.te.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.te.service.StorageService;
import com.te.service.jms.MessageSQSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static javax.swing.plaf.basic.BasicHTML.propertyKey;

@Configuration
@ComponentScan(basePackages = "com.te",
        excludeFilters = @ComponentScan.Filter(type= FilterType.REGEX, pattern="com.te.api.*")) //todo ask
public class AppConfig { //whole project configuration //backend

    @Autowired
    private Environment environment; //todo ask what is environment //spring environment is bean that you take profile
    private final Logger logger = LoggerFactory.getLogger(getClass()); //logger factory, (there is also entity factory) create a logger instance. so logger can invoke debug method.

    @Bean(name="databaseProperties")
    public PropertiesFactoryBean getDbProperties(){ // PropertiesFactoryBean is to generate Properties Factory Bean class. so we can invoke the bean in dataSourceInitializer.
        PropertiesFactoryBean bean= new PropertiesFactoryBean();
        String profile=environment.getActiveProfiles()[0];//todo ask what is [0], index 1
        logger.debug("databaseProperties is"+profile);
        bean.setLocation(new ClassPathResource("META-INF/env/application-"+profile+".properties")); //appConfig
        return bean;
    }

    //todo

    @Bean(name="shareProperties")
    public PropertiesFactoryBean getShareProperties(){
        PropertiesFactoryBean shareProperties=new PropertiesFactoryBean();
        shareProperties.setLocation(new ClassPathResource("META-INF/share-runtime.properties"));
        return shareProperties;
    }

    @Bean
//    @Profile({"dev","test","prod","staging"})
    public StorageService getStorageService(@Autowired @Qualifier("databaseProperties") PropertiesFactoryBean beanFactory) throws IOException {
        AmazonS3 s3Client= AmazonS3ClientBuilder.standard().withRegion("us-east-1").withCredentials(new DefaultAWSCredentialsProviderChain()).build();
        StorageService storageService=new StorageService(s3Client);
        storageService.setBucket(beanFactory.getObject().getProperty("amazon.s3.bucket"));
//        storageService.setBucket("testbucket");
        return storageService;
    }

    @Bean
    public MessageSQSService getSQSService(@Autowired @Qualifier("databaseProperties") PropertiesFactoryBean beanFactory) throws IOException {
        AmazonSQS sqs= AmazonSQSClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();
        String queueUrl = beanFactory.getObject().getProperty("amazon.sqs.url");
        MessageSQSService messageSQSService = new MessageSQSService(sqs,queueUrl);
        return messageSQSService;
    }

//    @Bean
//    public AmazonSQS getAmazonSQS(){
//        AmazonSQS client=AmazonSQSClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();
//        return client;
//    }

}
