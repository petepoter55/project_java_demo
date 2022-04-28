package com.example.projectTestDemo.tools;

import com.example.projectTestDemo.environment.Constant;
import com.example.projectTestDemo.exception.ResponseException;
import com.example.projectTestDemo.model.RequestKeeper;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

@Component
public class VelocityUtil {
    private static final Logger logger = Logger.getLogger(VelocityUtil.class);

    @Autowired
    private RequestKeeper requestKeeper;

    private VelocityEngine velocityEngine = new VelocityEngine();

    private VelocityContext velocityContext = new VelocityContext();

    public String fillDataToTemplateFactory(String serviceName, String templateName, Map<String, Object> data) throws ResponseException {

        logger.info("serviceName: "+serviceName+ " ,templateName: " + templateName);
        logger.info("data : "+data.toString());
        return fillDataToTemplate(serviceName, templateName, data);
    }

    public String fillDataToTemplate(String serviceName, String templateName, Map<String, Object> data) throws ResponseException {
        String velocityTemplateString = null;

        velocityTemplateString = requestKeeper.getTemplate(serviceName, templateName);

        Properties prop = new Properties();
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("velocity.properties"));
        } catch (IOException e) {
            logger.error(String.format(Constant.THROW_EXCEPTION,e.getMessage()));
        }
        velocityEngine.init(prop);

        VelocityContext context = putDataToContext(data);

        NumberUtil nu = new NumberUtil();
        context.put("NumberUtil", nu);

        StringWriter writer = new StringWriter();
        velocityEngine.evaluate( context, writer, serviceName, velocityTemplateString);
        writer.flush();

        return writer.toString();
    }

   //put value because map key in file response.vm
    public VelocityContext putDataToContext(Map<String, Object> data) {

        for (Map.Entry<String,Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            velocityContext.put(key, value);
        }
        return velocityContext;
    }
}
