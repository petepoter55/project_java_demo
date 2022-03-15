package com.example.projectTestDemo.tools;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;

public class RetrieveXPath {
    Document docXml;

    //Constructor
    public RetrieveXPath(InputStream input) throws IOException, ParserConfigurationException, SAXException {
        if (input == null)
            throw new IOException("InputStream cannot be null");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        DocumentBuilder builder = factory.newDocumentBuilder();
        docXml = builder.parse(input);
    }

    //get data By Xpath
    public String getString(String expression)
            throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        String value = null;
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(docXml, XPathConstants.NODESET);
        if (nodeList.getLength() > 0) {
            Node nNode = nodeList.item(0);
            value = nNode.getTextContent().trim();
        }
        return value;
    }
}
