package com.example.projectTestDemo.tools;

import com.example.projectTestDemo.dtoRequest.jaxBRequest.ManagePeopleJaxBRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

@Component
public class MqJAXBUtil {
    private static final Logger logger = Logger.getLogger(MqJAXBUtil.class);
    @Autowired
    private static JAXBContext jaxbContext;

    public static String marshalToMQRequest(ManagePeopleJaxBRequest managePeopleJaxBRequest) throws Exception {
        StringWriter writer = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(ManagePeopleJaxBRequest.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            marshaller.marshal(managePeopleJaxBRequest, writer);
        } catch (JAXBException var3) {
            logger.error("Unhandled exception caught", var3);
        }

        return writer.toString();
    }
}
