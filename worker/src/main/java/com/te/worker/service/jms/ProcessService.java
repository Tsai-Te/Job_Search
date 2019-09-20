package com.te.worker.service.jms;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.worker.service.SendGridEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Service
public class ProcessService {
    protected final Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private SendGridEmailService sendGridEmailService;

    @JmsListener(destination = "${jms.queue.name}")
    public void processMessage(String msg) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String, Object> map=new HashMap<>();
        map = objectMapper.readValue(msg, new TypeReference<Map<String, Object>>(){});
        String msgType = (String)map.get("id");
        String msgText = (String)map.get("domainName");
        Long userID=Long.valueOf(msgText);
        logger.info("receive msgType:" +msgType);
        logger.info("receive msgText:" +msgText);
    }
}
