package com.example.projectTestDemo.unit;

import com.example.projectTestDemo.environment.Constant;
import com.example.projectTestDemo.exception.ResponseException;
import com.example.projectTestDemo.model.DatabindLOGIN;
import com.example.projectTestDemo.model.Properties;
import com.example.projectTestDemo.model.Root;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CaseGenerator {
    private static final Logger logger = Logger.getLogger(CaseGenerator.class);
    private static HashMap<String, Class<?>[]> databindHM = new HashMap<>();

    public static void main(String[] args) {

    }

    public static void init() {
        databindHM.put(Constant.REQUEST_LOGIN, new Class<?>[]{ DatabindLOGIN.Root.class, DatabindLOGIN.Properties.class });
    }

    public static Object [] getTestCase(boolean isMandatory,String requestName,String [] successSample, String [] moreThanMaxSample,String[] nullSample){
        init();
        ObjectMapper om = new ObjectMapper();
        String jsonString;
        ArrayList<String[]> parameterArrayList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(new File(Constant.TEMPLATE_PATH + requestName + Constant.VALIDATION_FILE));
            logger.info("-------------------------- Start read Value file validate.json-------------------------------");
            jsonString = IOUtils.toString(fis, Constant.UTF_8);
            logger.info("line 43 -> jsonString : " + jsonString);
            logger.info("-------------------------- Done read Value file validate.json-------------------------------");

            Class<?> classRoot = null;
            Class<?> classProp = null;

            if (databindHM.get(requestName) != null
                    && databindHM.get(requestName).length == 2
                    && databindHM.get(requestName)[0] != null
                    && databindHM.get(requestName)[1] != null) {
                classRoot = (Class<?>) databindHM.get(requestName)[0];
                classProp = (Class<?>) databindHM.get(requestName)[1];
            }
            logger.info("line 52 -> classRoot : " + classRoot);
            logger.info("line 53 -> classProp : " + classProp);

            Root root = null;
            Properties properties = null;

            root = (Root) om.readValue(jsonString,classRoot);
            properties = root.getProperties();

            logger.info("line 61 -> root : " + root);
            logger.info("line 62 -> properties : " + properties);
            Field[] rootFields = root.getClass().getDeclaredFields();

            for(Field rootField : rootFields){
                logger.info("line 66 -> " + rootField.getName() + "::" + rootField.getType());

                if (Constant.REQUIRED.equals(rootField.getName())) {
                    logger.info("line 69 -> " + "Required : " + Arrays.toString(root.getRequired().toArray()));
                }

                if(rootField.getType().equals(classProp)){
                    logger.info("--------------------------------------------------------------------");
                    Field[] propertiesFields = rootField.getType().getDeclaredFields();
                    String parameterName;
                    String type;
                    List<String> typeList = null;
                    int minLength = 0;
                    int maxLength = 0;
                    int index = 1;
                    String pattern = null;

                    for(Field propertiesField : propertiesFields){
                        parameterName = propertiesField.getName();
                        logger.info("line 85 -> " + parameterName + ":" + propertiesField.getType());

                        Field [] fields = propertiesField.getType().getDeclaredFields();

                        try {
                            Object obj = propertiesField.get(properties);
                            Class<?> classX = (Class<?>) Class.forName(propertiesField.getType().getName());
                            for(Field field : fields){
                                field.setAccessible(true);

                                if (Constant.TYPE.equalsIgnoreCase(field.getName())) {
                                    if (obj instanceof String) {
                                        type = (String) field.get(classX.cast(obj));
                                    } else if (obj instanceof ArrayList<?>) {
                                        typeList = (ArrayList<String>) field.get(classX.cast(obj));
                                    }
                                } else if (Constant.MIN_LENGTH.equalsIgnoreCase(field.getName())) {
                                    minLength = (int) field.get(classX.cast(obj));
                                } else if (Constant.MAX_LENGTH.equalsIgnoreCase(field.getName())) {
                                    maxLength = (int) field.get(classX.cast(obj));
                                } else if (Constant.PATTERN.equalsIgnoreCase(field.getName())) {
                                    pattern = (String) field.get(classX.cast(obj));
                                }
                            }

                            String [] moreThanMaxArray = new String[moreThanMaxSample.length];
                            String [] nullArray = new String[nullSample.length];

                            if (root.getRequired().contains(parameterName) ||
                                    (!root.getRequired().contains(parameterName) && !isMandatory)) {
                                if (successSample != null) {
                                    parameterArrayList.add(successSample);
                                    successSample = null;
                                }
                            }

                            for(int i = 0; i< nullSample.length; i++){
                                if(i == 0){ // mock case detail
                                    nullArray[i] = String.format(nullSample[i], parameterName);
                                }else if(i == nullSample.length - 1){ // mock expected detail
                                    nullArray[i] = String.format(nullSample[i], parameterName, minLength);
                                }else if(i == index){ // mock data detail by case
                                    nullArray[i] = null;
                                }else {
                                    nullArray[i] = nullSample[i];
                                }
                            }

                            if (root.getRequired().contains(parameterName)) { // add data by name parameter to list
                                parameterArrayList.add(nullArray);
                            } else if (!root.getRequired().contains(parameterName) && !isMandatory) {
                                nullArray[nullSample.length - 1] = Constant.NULL_ALLOWED_FOR_OPTIONAL_FIELD;
                                parameterArrayList.add(nullArray);
                            }

                            for (int i = 0; i < moreThanMaxSample.length; i++) {
                                if (i == 0) {
                                    moreThanMaxArray[i] = pattern == null ? String.format(moreThanMaxSample[i], parameterName, maxLength) : parameterName + Constant.DOES_NOT_MATCH_PATTERN + pattern;
                                } else if (i == moreThanMaxSample.length - 1) {
                                    moreThanMaxArray[i] = pattern == null ? String.format(moreThanMaxSample[i], parameterName, maxLength, maxLength + 1) : String.format(Constant.REGEX_DOES_NOT_MATCH_EXPECTED, parameterName, moreThanMaxSample[index].concat("9"), pattern);
                                } else if (index == i) {
                                    if (moreThanMaxSample[i] == null) {
                                        moreThanMaxArray[i] = null;
                                    } else {
                                        if (pattern == null) {
                                            if (moreThanMaxSample[i].length() == maxLength) {
                                                moreThanMaxArray[i] = moreThanMaxSample[i].concat("9");
                                            } else {
                                                StringBuilder tmpSB = new StringBuilder();
                                                tmpSB.append(moreThanMaxSample[i]);
                                                for (int j = 0; j <= maxLength - moreThanMaxSample[i].length(); j++) {
                                                    tmpSB.append("9");
                                                }

                                                moreThanMaxArray[i] = tmpSB.toString();
                                            }
                                        } else {
                                            moreThanMaxArray[i] = moreThanMaxSample[i].concat("9");
                                        }
                                    }
                                } else {
                                    moreThanMaxArray[i] = moreThanMaxSample[i];
                                }
                            }

                            if (root.getRequired().contains(parameterName) ||
                                    (!root.getRequired().contains(parameterName) && !isMandatory)) {
                                parameterArrayList.add(moreThanMaxArray);
                            }

                            minLength = 0;
                            maxLength = 0;
                            pattern = null;
                            type = null;
                            typeList = null;
                        }catch (ResponseException | IllegalAccessException | ClassNotFoundException e){
                            logger.error(String.format(Constant.THROW_EXCEPTION,e.getMessage()));
                        }
                        index++;
                    }
                }
            }

            for (String [] tempArray : parameterArrayList) {
                logger.info("case : " + Arrays.toString(tempArray));
            }
        }catch (ResponseException e){
            logger.error(String.format(Constant.THROW_EXCEPTION,e.getMessage()));
        }catch (IOException ex){
            logger.error(String.format(Constant.THROW_EXCEPTION,ex.getMessage()));
        }
        return parameterArrayList.toArray();
    }
}
