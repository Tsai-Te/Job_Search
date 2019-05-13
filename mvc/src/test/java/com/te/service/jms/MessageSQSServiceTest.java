package com.te.service.jms;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.te.config.AppConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.mockito.Mockito.*;


@WebAppConfiguration
@ContextConfiguration(classes={AppConfig.class}) //also in web.xml
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class MessageSQSServiceTest {
    private String messageBody="hello world";
    private int delaySecond=5;
//    private String queueUrl="job_search_dev.com";

    //also find
    //AmazonSQS sqs= AmazonSQSClientBuilder.standard().withRegion("us-east-1").withCredentials(new DefaultAWSCredentialsProviderChain()).build();

    @Value("#{databaseProperties['amazon.sqs.url']}")
    protected String amazonSQSUrl;

    @InjectMocks
    @Autowired
    private MessageSQSService messageSQSService;

    @Mock
    private AmazonSQS amazonSQS= Mockito.mock(AmazonSQS.class);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        validateMockitoUsage();
    }

    @Test
    public void sendMessageTest(){
        AmazonSQS sqs= AmazonSQSClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(amazonSQSUrl)
                .withMessageBody(messageBody)
                .withDelaySeconds(delaySecond);
        sqs.sendMessage(send_msg_request);

    }


    @Test
    public void sendMessageTest2(){
        messageSQSService.sendMessage(messageBody,delaySecond);
        verify(amazonSQS,times(1)).sendMessage(any());
    }
}
