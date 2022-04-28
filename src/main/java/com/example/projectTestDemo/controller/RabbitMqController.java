package com.example.projectTestDemo.controller;

import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.service.RabbitMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitMq")
public class RabbitMqController {
    @Autowired
    RabbitMqService rabbitMqService;

    @PostMapping(value = "/sendQueue")
    public Response sendQueue(
    ){
        return this.rabbitMqService.sendMessageToQueue();
    }
}
