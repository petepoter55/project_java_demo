package com.example.projectTestDemo.service.implement;

import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.entity.MangePeopleDetail;
import com.example.projectTestDemo.environment.Constant;
import com.example.projectTestDemo.exception.ResponseException;
import com.example.projectTestDemo.queue.Push;
import com.example.projectTestDemo.repository.ManagePeopleDetailRepository;
import com.example.projectTestDemo.service.RabbitMqService;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqImplement implements RabbitMqService {
    private static final Logger logger = Logger.getLogger(RabbitMqImplement.class);
    @Autowired
    private ManagePeopleDetailRepository managePeopleDetailRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Response sendMessageToQueue() {
        MangePeopleDetail mangePeopleDetail = this.managePeopleDetailRepository.findById(1);

        try {
            if(mangePeopleDetail != null){
                Push push = new Push(rabbitTemplate);
                push.requestUpdateManage(mangePeopleDetail);
            }
        }catch (ResponseException e){
            logger.error(String.format(Constant.THROW_EXCEPTION,e.getMessage()));
            return new Response(false, Constant.ERROR_SEND_MQ, Constant.STATUS_CODE_FAIL);
        }
        return new Response(true, Constant.SUCCESS_SEND_MQ, Constant.STATUS_CODE_SUCCESS);
    }
}
