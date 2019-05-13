package com.te.service.jms;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

public class MessageSQSService {
    private AmazonSQS sqs;
    private String queueUrl;

    public MessageSQSService(AmazonSQS sqs, String queueUrl){
        this.sqs=sqs;
        this.queueUrl=queueUrl;
    }

    public void sendMessage(Map<String, MessageAttributeValue> messageBody, Integer delaySecond){
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageAttributes(messageBody)
                .withDelaySeconds(delaySecond);
        sqs.sendMessage(send_msg_request);
    }

    public void sendMessage(String messageBody, Integer delaySecond){
        SendMessageRequest sendMsgRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(messageBody)
                .withDelaySeconds(delaySecond);
        sqs.sendMessage(sendMsgRequest);
    }

}
