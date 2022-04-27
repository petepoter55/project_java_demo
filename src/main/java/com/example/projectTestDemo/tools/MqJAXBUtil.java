package com.example.projectTestDemo.tools;

import com.example.projectTestDemo.dtoRequest.jaxBRequest.ManagePeopleJaxBRequest;
import com.example.projectTestDemo.dtoRequest.jaxBRequest.ManagePeopleJaxBResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

@Component
public class MqJAXBUtil {
    private static final Logger logger = Logger.getLogger(MqJAXBUtil.class);

    //Unmarshalling the String content
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

    public static ManagePeopleJaxBRequest unmarshalMQResponseMessage(String message){
        ManagePeopleJaxBRequest responseData = new ManagePeopleJaxBRequest();

        try {
            message = message.replace("\u0000", " ");
            logger.info("Response received from MQ " + message);

            JAXBContext context = JAXBContext.newInstance(ManagePeopleJaxBRequest.class);
            responseData = (ManagePeopleJaxBRequest) context.createUnmarshaller().unmarshal(new StringReader(message));
            System.out.println(responseData);
        } catch (Exception var12) {
            logger.error("Unhandled exception caught", var12);
        }

        return responseData;
    }

    public static ManagePeopleJaxBResponse unmarshalMQResponseMessageByRootElement(String message){
        Object responseData = null;
        ManagePeopleJaxBResponse managePeopleJaxBResponse = new ManagePeopleJaxBResponse();

        try {
            message = message.replace("\u0000", " ");
            logger.info("Response received from MQ " + message);

            StringBuilder xmlStringBuilder = new StringBuilder(message);
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc;
            doc = builder.parse(new ByteArrayInputStream(xmlStringBuilder.toString().getBytes(StandardCharsets.UTF_8)));
            Element root = doc.getDocumentElement();

            managePeopleJaxBResponse.setLastName(root.getElementsByTagName("LastName").item(0).getTextContent());
            managePeopleJaxBResponse.setFirstName(root.getElementsByTagName("FirstName").item(0).getTextContent());


        } catch (Exception var12) {
            logger.error("Unhandled exception caught", var12);
        }

        return managePeopleJaxBResponse;
    }
}
