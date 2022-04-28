package com.example.projectTestDemo.service.implement;

import com.example.projectTestDemo.dtoRequest.CreateAccountRequest;
import com.example.projectTestDemo.dtoRequest.ExportExcelRequest;
import com.example.projectTestDemo.dtoRequest.LoginRequest;
import com.example.projectTestDemo.dtoResponse.ImportExcelManageUserResponse;
import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.dtoResponse.ValidateSchemaResponse;
import com.example.projectTestDemo.entity.MangePeopleDetail;
import com.example.projectTestDemo.entity.ManageUser;
import com.example.projectTestDemo.environment.Constant;
import com.example.projectTestDemo.exception.ResponseException;
import com.example.projectTestDemo.queue.Push;
import com.example.projectTestDemo.repository.ManagePeopleDetailRepository;
import com.example.projectTestDemo.repository.UserRepository;
import com.example.projectTestDemo.response.custom.ResponseMapper;
import com.example.projectTestDemo.service.ManageDetailService;
import com.example.projectTestDemo.service.validation.ValidationAbstract;
import com.example.projectTestDemo.service.validation.ValidatorFactory;
import com.example.projectTestDemo.service.validation.custom.ValidatorSchema;
import com.example.projectTestDemo.tools.UtilityTools;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ManageDetailImplement implements ManageDetailService {
    private static final Logger logger = Logger.getLogger(ManageDetailImplement.class);
    private final ManagePeopleDetailRepository managePeopleDetailRepository;
    private final UserRepository userRepository;
    private final JwtImplement jwtImplement;
    private RabbitTemplate rabbitTemplate;
    private ValidatorSchema validatorSchema;
    private ResponseMapper responseMapper;
    private ValidatorFactory validatorFactory;

    @Autowired
    public ManageDetailImplement(ManagePeopleDetailRepository managePeopleDetailRepository,
            UserRepository userRepository,RabbitTemplate rabbitTemplate,JwtImplement jwtImplement,ValidatorSchema validatorSchema,ResponseMapper responseMapper,ValidatorFactory validatorFactory) {
        this.managePeopleDetailRepository = managePeopleDetailRepository;
        this.userRepository = userRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.jwtImplement = jwtImplement;
        this.validatorSchema = validatorSchema;
        this.responseMapper = responseMapper;
        this.validatorFactory = validatorFactory;
    }

    @Value("${jwt.secretkey}")
    private String jwtSecretkey;

    @Override
    public String generateXml() throws JsonProcessingException {
        MangePeopleDetail mangePeopleDetail = this.managePeopleDetailRepository.findByRefNo("PE68");
        // Solution 1
        // XmlMapper xmlMapper = new XmlMapper();
        // String personXml = xmlMapper.writeValueAsString(mangePeopleDetail);
        XStream xstream = new XStream(new StaxDriver());
        // Solution2
        String dataXml = xstream.toXML(mangePeopleDetail);
        return dataXml;
    }

    @Override
    public Response createAccount(CreateAccountRequest createAccountRequest) {
        logger.info("===== Start CreateAccount =======");
        logger.info("username : " + createAccountRequest.getUsername());
        MangePeopleDetail mangePeopleDetail = this.managePeopleDetailRepository.findById(Integer.parseInt(createAccountRequest.getPeopleDetailId()));
        try {
            UtilityTools utilityTools = new UtilityTools();
            Boolean checkObject = ObjectUtils.isEmpty(createAccountRequest);
            Boolean checkObject2 = ObjectUtils.isEmpty(mangePeopleDetail);

            if (!checkObject && !checkObject2) {
                logger.info("MangePeopleDetail FirstName: " + mangePeopleDetail.getManagePeopleName());
                logger.info("MangePeopleDetail LastName: " + mangePeopleDetail.getManagePeopleLastName());

                if (createAccountRequest.getPassword().equals(createAccountRequest.getConfirmPassword())) {
                    ManageUser user = new ManageUser();
                    String password = utilityTools.hashSha256(createAccountRequest.getPassword());
                    String username = "US" + createAccountRequest.getUsername();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setUcode(utilityTools.randomString(10));
                    user.setCreateDate(new UtilityTools().getFormatsDateMilli());
                    user.setEmail(mangePeopleDetail.getEmail());
                    user.setApprovedDate(new UtilityTools().getFormatsDateMilli());
                    user.setFirstName(mangePeopleDetail.getManagePeopleName());
                    user.setLastName(mangePeopleDetail.getManagePeopleLastName());
                    user.setApproved("Y");
                    user.setStatusLogin("Y");
                    user.setPostCode(mangePeopleDetail.getPostCode());
                    user.setCreateBy(BigInteger.valueOf(0));
                    user.setForceChangePassword(1);
                    user.setToken_user(jwtImplement.generate(username, mangePeopleDetail.getEmail(), mangePeopleDetail.getManagePeopleTaxId()));
                    this.userRepository.save(user);
                } else {
                    return new Response(false, Constant.ERROR_INPUT_CREATE_ACCOUNT,
                            Constant.STATUS_CODE_FAIL);
                }
            } else {
                return new Response(false, Constant.ERROR_CREATE_ACCOUNT, Constant.STATUS_CODE_FAIL);
            }
        } catch (ResponseException | NoSuchAlgorithmException | UnsupportedEncodingException | ParseException e) {
            logger.error("error : "+ e.getMessage());
            return new Response(false, Constant.ERROR_CREATE_ACCOUNT, Constant.STATUS_CODE_FAIL);
        }
        logger.info("===== End CreateAccount =======");
        return new Response(true, Constant.SUCCESS_CREATE_ACCOUNT, Constant.STATUS_CODE_SUCCESS);
    }

    @Override
    public Response login(LoginRequest loginRequest) {
        logger.info("===== Start Login =======");
        logger.info("username : " + loginRequest.getUsername());
        UtilityTools utilityTools = new UtilityTools();

        try {
            ManageUser manageUser = this.userRepository.findByUsername(loginRequest.getUsername());

            Boolean checkPass = true;
            Boolean checkObject = ObjectUtils.isEmpty(manageUser);
            logger.info("checkObject : " + checkObject);
            if (!checkObject) {
                checkPass = utilityTools.checkPassphrases(manageUser.getPassword(),
                        loginRequest.getPassword());
                logger.info("checkpass : " + checkPass);
                if (!checkPass) {
                    return new Response(false, Constant.ERROR_INPUT_LOGIN, Constant.STATUS_CODE_FAIL);
                }
            } else {
                return new Response(false, Constant.ERROR_FOUND_DATA_LOGIN, Constant.STATUS_CODE_FOUND);
            }

        } catch (ResponseException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
            logger.error("error : "+ e.getMessage());
            return new Response(false, Constant.ERROR_LOGIN, Constant.STATUS_CODE_FAIL);
        }
        logger.info("===== End Login =======");
        return new Response(true, Constant.SUCCESS_LOGIN, Constant.STATUS_CODE_SUCCESS);
    }

    @Override
    public Response testValidationLoginRequest(String jsonRequest) {
        UtilityTools utilityTools = new UtilityTools();
        ObjectMapper objectMapper = new ObjectMapper();

        ValidationAbstract validator = validatorFactory.getValidator(Constant.REQUEST_LOGIN);
        ValidateSchemaResponse validateSchemaResponse = validator.validate(Constant.REQUEST_LOGIN, jsonRequest);

        try {
            if(validateSchemaResponse.isStatus()){
                LoginRequest loginRequest = objectMapper.readValue(jsonRequest,LoginRequest.class);
                ManageUser manageUser = this.userRepository.findByUsername(loginRequest.getUsername());
                //map response to file response.vm
                String jsonResponse = responseMapper.mapSpResponseToJson(Constant.REQUEST_LOGIN, objectMapper.writeValueAsString(manageUser));
                Gson gson = new GsonBuilder().serializeNulls().create();
                logger.info("jsonResponse" + gson.toJson(jsonResponse));

                Boolean checkPass = true;
                Boolean checkObject = ObjectUtils.isEmpty(manageUser);
                logger.info("checkObject : " + checkObject);
                if (!checkObject) {
                    checkPass = utilityTools.checkPassphrases(manageUser.getPassword(),
                            loginRequest.getPassword());
                    logger.info("checkpass : " + checkPass);
                    if (!checkPass) {
                        return new Response(false, Constant.ERROR_INPUT_LOGIN, Constant.STATUS_CODE_FAIL);
                    }
                } else {
                    return new Response(false, Constant.ERROR_FOUND_DATA_LOGIN, Constant.STATUS_CODE_FOUND);
                }
            }else {
                return new Response(false, validateSchemaResponse.getMessage(), Constant.STATUS_CODE_FAIL);
            }
        }catch (ResponseException | JsonProcessingException | UnsupportedEncodingException | NoSuchAlgorithmException e){
            logger.error("error : " + e.getMessage());
        }
        return new Response(true, Constant.SUCCESS_LOGIN, Constant.STATUS_CODE_SUCCESS);
    }
}
