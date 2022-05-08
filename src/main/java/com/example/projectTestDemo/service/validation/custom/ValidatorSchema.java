package com.example.projectTestDemo.service.validation.custom;

import com.example.projectTestDemo.dtoResponse.ValidateSchemaResponse;
import com.example.projectTestDemo.environment.Constant;
import com.example.projectTestDemo.exception.ResponseException;
import com.example.projectTestDemo.model.RequestKeeper;
import com.example.projectTestDemo.service.validation.ValidationAbstract;

import org.json.JSONException;
import org.json.JSONObject;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorSchema extends ValidationAbstract {
    private static final Logger logger = Logger.getLogger(ValidationAbstract.class);

    @Autowired
    private RequestKeeper requestKeeper;

    private String getSchemaConfigFactory(String requestName, String pathSchemaRequest){
        String schemaConfigFactory = "";
        try {
            schemaConfigFactory = requestKeeper.getTemplate(requestName, pathSchemaRequest);
        }catch (ResponseException e){
            logger.error(String.format(Constant.THROW_EXCEPTION,e.getMessage()));
        }
        return schemaConfigFactory;
    }

    @Override
    public ValidateSchemaResponse validate(String requestName, String jsonRequest){
        String pathSchemaRequest = "template/" +requestName +"/validation.json";
        logger.info("serviceNameSchema :" + pathSchemaRequest);
         try {
             String schemaString = getSchemaConfigFactory(requestName, pathSchemaRequest);
             logger.info("schemaString :" + schemaString);
             JSONObject jsonSchema = new JSONObject(cleanUpUnwantedSpaces(schemaString));
             JSONObject jsonSubject = new JSONObject(cleanUpUnwantedSpaces(jsonRequest));
             logger.info("jsonSchema :" + jsonSchema);
             logger.info("jsonSubject :" + jsonSubject);

             Schema schema = SchemaLoader.load(jsonSchema);
             schema.validate(jsonSubject);
         }catch (ValidationException | JSONException ex){
             logger.error(String.format(Constant.THROW_EXCEPTION,ex.getMessage()));
             return new ValidateSchemaResponse(false,ex.getMessage());
         }
        return new ValidateSchemaResponse(true, Constant.SUCCESS);
    }

    private String cleanUpUnwantedSpaces(String jsonString) {
        return jsonString.replaceAll("\\t*", "");
    }
}
