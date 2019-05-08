package com.te.service.jms;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class MessageSQSServiceTest {

    //also find
    //AmazonSQS sqs= AmazonSQSClientBuilder.standard().withRegion("us-east-1").withCredentials(new DefaultAWSCredentialsProviderChain()).build();

    @Value("#{databaseProperties['amazon.sqs.url']}")
    protected String amazonSQSUrl;

    @Autowired
    private MessageSQSService messageSQSService;

    @Test
    public void sendMessageTest(){
        AmazonSQS sqs= AmazonSQSClientBuilder.standard().withRegion("us-east-1").withCredentials(new DefaultAWSCredentialsProviderChain()).build();
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(amazonSQSUrl)
                .withMessageBody("hello world 3")
                .withDelaySeconds(5);
        sqs.sendMessage(send_msg_request);

    }


    @Test
    public void sendMessageTest2(){
        messageSQSService.sendMessage("hello world 3",5);
    }
}
