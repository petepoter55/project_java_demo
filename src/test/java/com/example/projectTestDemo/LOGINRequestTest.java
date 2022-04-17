package com.example.projectTestDemo;

import com.example.projectTestDemo.dtoRequest.LoginRequest;
import com.example.projectTestDemo.dtoResponse.ValidateSchemaResponse;
import com.example.projectTestDemo.environment.Constant;
import com.example.projectTestDemo.exception.ResponseException;
import com.example.projectTestDemo.model.RequestKeeper;
import com.example.projectTestDemo.service.validation.custom.ValidatorSchema;
import com.example.projectTestDemo.unit.CaseGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.lang.reflect.Type;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.initMocks;


@RunWith(Parameterized.class)
public class LOGINRequestTest {
    private static final Logger logger = Logger.getLogger(LOGINRequestTest.class);

    @InjectMocks
    private ValidatorSchema validatorSchema;

    @Spy
    private RequestKeeper requestKeeper;

    @Before
    public void init() {
        initMocks(this);
    }

    private String caseName;
    private String username;
    private String password;
    private String expected;
    public LOGINRequestTest(String caseName,String username,String password,String expected) {
        this.caseName = caseName;
        this.username = username;
        this.password = password;
        this.expected = expected;
    }
    @Parameterized.Parameters
    public static Collection<Object> input() {
        final String [] successSample = {Constant.CASE_SUCCESS, "US001","Register@123",Constant.NO_EXCEPTION_FOUND};
        final String [] nullSample = {Constant.NULL_CASE, "US001","Register@123",Constant.NULL_CASE_EXPECTED};
        final String [] moreThanMaxSample = {Constant.MORE_THAN_CASE, "US001","Register@123", Constant.MORE_THAN_CASE_EXPECTED};

        return Arrays.asList(CaseGenerator.getTestCase(false,Constant.REQUEST_LOGIN,successSample,moreThanMaxSample,nullSample));
    }

    @Test
    public void validateLOGINRequestTest() throws Exception {
        // ARRANGE
        LoginRequest loginRequest = new LoginRequest();
        BeanUtils.copyProperties(loginRequest, this);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type gsonType = new TypeToken<LoginRequest>(){}.getType();
        logger.info(gson.toJson(loginRequest, gsonType));
        // ACT
        String jsonRequest = gson.toJson(loginRequest, gsonType);
        logger.info(jsonRequest);
//        ValidateSchemaResponse raisedException = validatorSchema.validate(Constant.REQUEST_LOGIN, jsonRequest);
//        logger.info("raisedException: " + validatorSchema.validate(Constant.REQUEST_LOGIN, jsonRequest));
        // ASSERT
//        if (!Constant.CASE_SUCCESS.equalsIgnoreCase(this.caseName) && !this.expected.contains(Constant.NULL_ALLOWED)
//                && !this.expected.contains(Constant.NO_EXCEPTION_FOUND)) {
//            final Throwable raisedException = catchThrowable(() ->
//                    validatorSchema.validate(Constant.REQUEST_LOGIN, jsonRequest));
//            assertNotNull(Constant.RAISED_EXCEPTION_IS_NULL, raisedException);
//            System.out.printf("raisedException :" + raisedException);
//            assertThat(raisedException).isInstanceOf(ResponseException.class);

//            if (raisedException instanceof ResponseException) {
//                InputValidationException ive = (InputValidationException) raisedException;
//                log.info(String.format(EXPECTED_RESULT, expected));
//                log.info(String.format(ACTUAL_RESULT, ive.getErrors().getErrors().get(0).getMessage()));
//                assertEquals(expected, ive.getErrors().getErrors().get(0).getMessage());
//            }
//        }
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }
}
