package com.example.projectTestDemo.queue;

import com.example.projectTestDemo.ProjectTestDemoApplication;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Push {
    private final RabbitTemplate rabbitTemplate;

    public Push(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void requestUpdateManage(Object message) {
        ObjectMapper map = new ObjectMapper();
        JsonNode json = map.convertValue(message, JsonNode.class);
        this.rabbitTemplate.convertAndSend(ProjectTestDemoApplication.queueUpdateManage, json.toString());
    }
}
