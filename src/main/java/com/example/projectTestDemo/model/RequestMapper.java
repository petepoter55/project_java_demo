package com.example.projectTestDemo.model;


import com.example.projectTestDemo.dtoRequest.jaxBRequest.ManagePeopleJaxBRequest;
import com.example.projectTestDemo.dtoRequest.jaxBRequest.ManagePeopleJaxBResponse;
import com.example.projectTestDemo.environment.Constant;
import com.example.projectTestDemo.tools.MqJAXBUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class RequestMapper {

    public String mapToManagePeopleJaxBString() throws Exception {
        return MqJAXBUtil.marshalToMQRequest(mapToManagePeopleJaxBRequest());
    }

    public ManagePeopleJaxBRequest mapToManagePeopleJaxBRequest() throws Exception {
        LocalDateTime requestDateTime = LocalDateTime.now();
        String rqDate = DateTimeFormatter.ofPattern(Constant.RQ_DATE_PATTERN)
                .format(requestDateTime.toLocalDate());
        String rqTime = DateTimeFormatter.ofPattern(Constant.RQ_TIME_PATTERN)
                .format(requestDateTime.toLocalTime());

        ManagePeopleJaxBRequest data = new ManagePeopleJaxBRequest();
        ManagePeopleJaxBRequest.ManagePeopleTaxId managePeopleTaxId = new ManagePeopleJaxBRequest.ManagePeopleTaxId();
        managePeopleTaxId.setFirstName("boonyaris");
        managePeopleTaxId.setLastName("pensri");
        data.setManagePeopleTaxId(managePeopleTaxId);

        return data;
    }

    public ManagePeopleJaxBRequest mapToManagePeopleJaxBResponse(String message){
        ManagePeopleJaxBRequest managePeopleJaxBResponse;
        try {
            managePeopleJaxBResponse = MqJAXBUtil.unmarshalMQResponseMessage(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return managePeopleJaxBResponse;
    }

    public ManagePeopleJaxBResponse mapToManagePeopleJaxBResponseGetByRoot(String message){
        ManagePeopleJaxBResponse managePeopleJaxBResponse;
        try {
            managePeopleJaxBResponse = MqJAXBUtil.unmarshalMQResponseMessageByRootElement(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return managePeopleJaxBResponse;
    }
}
