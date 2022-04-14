package com.example.projectTestDemo.service.validation.custom;

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
            logger.error("error :" + e.getMessage());
        }
        return schemaConfigFactory;
    }

    @Override
    public void validate(String requestName, String jsonRequest) throws ResponseException {
        String pathSchemaRequest = "template/" +requestName +"/validation.json";
        logger.info("serviceNameSchema :" + pathSchemaRequest);
         try {
             String schemaString = getSchemaConfigFactory(requestName, pathSchemaRequest);

             JSONObject jsonSchema = new JSONObject(cleanUpUnwantedSpaces(schemaString));
             JSONObject jsonSubject = new JSONObject(cleanUpUnwantedSpaces(jsonRequest));

             Schema schema = SchemaLoader.load(jsonSchema);
             schema.validate(jsonSubject);
         }catch (ValidationException | JSONException e){
             logger.error("error : " + e.getMessage());
         }
    }

    private String cleanUpUnwantedSpaces(String jsonString) {
        return jsonString.replaceAll("\\t*", "");
    }
}
