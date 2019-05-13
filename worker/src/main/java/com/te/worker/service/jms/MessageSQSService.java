package com.te.worker.service.jms;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;

import java.util.List;

public class MessageSQSService {
    private AmazonSQS sqs;
    private String queueUrl;

    public MessageSQSService(AmazonSQS sqs, String queueUrl){
        this.sqs=sqs;
        this.queueUrl=queueUrl;
    }

    public void receiveMessage() {
        List<Message> messages = sqs.receiveMessage(queueUrl).getMessages();
        for (Message m : messages) {
            sqs.deleteMessage(queueUrl, m.getReceiptHandle());
        }
    }


}
