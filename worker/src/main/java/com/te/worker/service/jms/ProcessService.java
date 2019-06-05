package com.te.worker.service.jms;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProcessService {
    protected final Logger logger= LoggerFactory.getLogger(getClass());

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
