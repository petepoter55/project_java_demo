package com.example.projectTestDemo.service.implement;

import com.example.projectTestDemo.dtoRequest.GetDataRequest;
import com.example.projectTestDemo.dtoResponse.ValidateSchemaResponse;
import com.example.projectTestDemo.entity.MangePeopleDetail;
import com.example.projectTestDemo.environment.Constant;
import com.example.projectTestDemo.exception.ResponseException;
import com.example.projectTestDemo.repository.ManagePeopleDetailRepository;
import com.example.projectTestDemo.response.custom.ResponseMapper;
import com.example.projectTestDemo.service.ManageDetailVelocityService;
import com.example.projectTestDemo.service.validation.ValidationAbstract;
import com.example.projectTestDemo.service.validation.ValidatorFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ManageDetailVelocityImplement implements ManageDetailVelocityService {
    private static final Logger logger = Logger.getLogger(ManageDetailVelocityImplement.class);

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ManagePeopleDetailRepository managePeopleDetailRepository;
    @Autowired
    private ValidatorFactory validatorFactory;
    @Autowired
    private ResponseMapper responseMapper;

    @Override
    public ResponseEntity<String> getDateVelocity(String jsonRequest) {
        String spResponse = null;
        String jsonResponse = null;
        Map<String, Object> response = null;
        try {
            ValidationAbstract validator = validatorFactory.getValidator(Constant.REQUEST_GETDATA);
            ValidateSchemaResponse validateSchemaResponse = validator.validate(Constant.REQUEST_GETDATA, jsonRequest);
            if(validateSchemaResponse.isStatus()){
                GetDataRequest getDataRequest = objectMapper.readValue(jsonRequest,GetDataRequest.class);

                List<MangePeopleDetail> mangePeopleDetailList = this.managePeopleDetailRepository.searchByManagePeopleTaxIdLike(getDataRequest.getManageId());
//                TODO : research how to convert arraylist to hashMap or Map Array to VelocityTemplate
                response = objectMapper.convertValue(mangePeopleDetailList.get(0),Map.class);

                JSONObject jsonObject = new JSONObject(response);
                spResponse = jsonObject.toString();
                //check valueReference velocity by name properties in POJO class
                jsonResponse = responseMapper.mapSpResponseToJson(Constant.REQUEST_GETDATA,spResponse );

            }else {
                return new ResponseEntity<>(validateSchemaResponse.getMessage().replace("#/",""), HttpStatus.LENGTH_REQUIRED);
            }
        }catch (ResponseException | JsonProcessingException ex){
            logger.error(String.format(Constant.THROW_EXCEPTION,ex.getMessage()));
        }
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }
}
