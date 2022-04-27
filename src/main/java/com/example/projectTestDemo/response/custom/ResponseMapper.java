package com.example.projectTestDemo.response.custom;

import com.example.projectTestDemo.exception.ResponseException;
import com.example.projectTestDemo.response.ResponseMapperAbstract;
import com.example.projectTestDemo.tools.VelocityUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
@Component
public class ResponseMapper extends ResponseMapperAbstract {
    private static final Logger logger = Logger.getLogger(ResponseMapper.class);
    @Autowired
    private VelocityUtil velocityUtil;

    @Override
    public String mapSpResponseToJson(String requestName, String response) throws ResponseException {
        Map<String, Object> respData = extractResponseToMap(response);
        String responseTemplate = "template/" +requestName +"/response.vm";
        return velocityUtil.fillDataToTemplateFactory(requestName, responseTemplate, respData);
    }

    public static Map<String, Object> extractResponseToMap(String response) throws
            ResponseException {
        //String docRsString = VelocityUtil.convertSoapResponseToDocRs(responseTagServiceName, response);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
        Map<String, Object> respData = null;
        try {
            TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {};
            respData = mapper.readValue(response, typeRef);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return respData;
    }
}
