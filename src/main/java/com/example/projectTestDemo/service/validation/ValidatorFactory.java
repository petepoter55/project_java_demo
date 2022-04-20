package com.example.projectTestDemo.service.validation;

import com.example.projectTestDemo.service.validation.custom.ValidatorSchema;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ValidatorFactory {
    private static final Logger logger = Logger.getLogger(ValidatorFactory.class);
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ValidatorSchema validatorSchema;

    public ValidationAbstract getValidator(String serviceName) {
        Object validator = null;
        try {
            validator = applicationContext.getBean("validator" + serviceName);
        } catch (Exception ite) {
        }

        if(validator==null) {
            logger.debug("use default validator");
            validator = validatorSchema;
        }
        logger.info("serviceName : {"+serviceName+"}, validator : {"+validator+"}");
        return (ValidationAbstract)validator;
    }

}
