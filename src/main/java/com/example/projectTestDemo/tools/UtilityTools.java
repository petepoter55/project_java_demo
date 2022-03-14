package com.example.projectTestDemo.tools;



import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.tomcat.util.codec.binary.Base64;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;


import javax.xml.XMLConstants;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class UtilityTools {
    private XSSFSheet sheet;

    public Date getFormatsDateMilli() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        Date dates = cal.getTime();
        return dates;
    }

    public String getFormatsDateMilliString() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        Date dates = cal.getTime();
        return df.format(dates);
    }

    public static boolean isNumberic(String number){
        String regex = "\\d+";
        return number.matches(regex);
    }

    public String deCodeBase64ToString(String dataXml){
        byte[] xmlData = Base64.decodeBase64(dataXml);
        String decodedString = new String(xmlData);

        return decodedString;
    }

    private String generateXml(Object object){
//        Solution 1
//        XmlMapper xmlMapper = new XmlMapper();
//        String personXml = xmlMapper.writeValueAsString(mangePeopleDetail);
        XStream xstream = new XStream(new StaxDriver());
//        Solution2
        String dataXml = xstream.toXML(object);
        return dataXml;
    }

    public String generateDatetimeAndMillisec(String date) throws ParseException {
        String fomatDate = "yyyy-MM-dd HH:mm:ss.SSS";
        DateFormat dateFormat = new SimpleDateFormat(fomatDate);
        SimpleDateFormat df = new SimpleDateFormat(fomatDate);
        Date d = df.parse(date);
        return dateFormat.format(d);
    }

    public Date date2DatetimeDbFormatsDateMilli(String date) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(df.parse(date));

        Date dates = cal.getTime();
        return dates;
    }

    public static boolean checkPassphrases(String phrases, String pass)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        boolean status = true;
//        String salt = phrases.substring(0, 26);
        String passwordRequest = UtilityTools.hashSha256(pass);
        if (!passwordRequest.equals(phrases)) {
            status = false;
        }
        return status;
    }

    public static String hashSha256 (String msg)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(msg.getBytes("UTF-8")); // Change this to "UTF-16" if needed
        byte[] digest = md.digest();
        return String.format("%064x", new java.math.BigInteger(1, digest));
    }

    public static String hashSha256Test (String msg) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(
                msg.getBytes(StandardCharsets.UTF_8));
//        String sha256hex = new String(Hex.encode(hash));
//
//        return sha256hex;
        return null;
    }

    public static String randomNumber(int length){
        String alphabet = "1234567890";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);

            sb.append(randomChar);
        }

        String randomString = sb.toString();
        return randomString;
    }

    public static String randomString(int length){
        String alphabet = "abcdefghijklmnopqrstuvwxyz@#$%^&ABCDEFGHIJKLMNOP1234567890";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);

            sb.append(randomChar);
        }

        String randomString = sb.toString();
        return randomString;
    }

    public static boolean doValidateXml(String xml) throws SAXException, IOException {
        boolean status = false;
        XMLReader parser = XMLReaderFactory.createXMLReader();
        parser.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        parser.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        parser.setContentHandler(new DefaultHandler());
        InputSource source = new InputSource(new ByteArrayInputStream(xml.getBytes("UTF-8")));
        try {
            parser.parse(source);
            status = true;
        } catch (Exception e) {
             e.printStackTrace();
        }
        return status;
    }


}
