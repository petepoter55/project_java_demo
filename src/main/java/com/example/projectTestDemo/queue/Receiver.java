package com.example.projectTestDemo.queue;

import com.example.projectTestDemo.entity.MangePeopleDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    public void receiveUpdateManageDetail(Object message) {
        try {
            MangePeopleDetail mangePeopleDetail = new ObjectMapper().readValue(message.toString(), MangePeopleDetail.class);
            System.out.println("Hello Rabbit MQ");
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
}
