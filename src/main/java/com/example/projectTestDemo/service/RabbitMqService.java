package com.example.projectTestDemo.service;

import com.example.projectTestDemo.dtoResponse.Response;

public interface RabbitMqService {
    public Response sendMessageToQueue();
}
