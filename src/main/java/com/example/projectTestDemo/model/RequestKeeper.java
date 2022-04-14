package com.example.projectTestDemo.model;

import com.example.projectTestDemo.exception.ResponseException;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RequestKeeper {
    private static final Logger logger = Logger.getLogger(RequestKeeper.class);
    private Map<String, String> requests;

    //constructor assign value to map when call class RequestKeeper
    public RequestKeeper() {
        requests = new ConcurrentHashMap<>();
    }

    public Map<String, String> getRequests() {
        return requests;
    }

    public void setRequests(Map<String, String> requests) {
        this.requests = requests;
    }

    public String getTemplate(String requestName, String pathSchemaRequest) throws ResponseException {
        if (!requests.containsKey(requestName)) {
            logger.info("Template name: {} for service: {} not found, reading a new template" + requestName + pathSchemaRequest);
            try (InputStream template = new ClassPathResource(pathSchemaRequest).getInputStream()) {
                String templateString = new String(FileCopyUtils.copyToByteArray(template), StandardCharsets.UTF_8);
                requests.put(requestName, templateString);
                return templateString;
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return requests.get(requestName);
    }
}
