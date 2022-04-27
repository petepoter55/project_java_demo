package com.example.projectTestDemo.service.implement;

import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.repository.UserRepository;
import com.example.projectTestDemo.request.ManageDetailLoginTest;
import com.example.projectTestDemo.response.ResponseResultTest;
import org.junit.Test;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ManageDetailImplementTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private ManageDetailImplement manageDetailImplement;

    @Test
    public void case_login() throws Exception {
        File[] files = readTestCase();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).setDateFormat(simpleDateFormat);
        for(File file : files){
            try {
                // ARRANGE
                ManageDetailLoginTest manageRegisterRequestTest = mapper.readValue(FileUtils.readFileToString(file, StandardCharsets.UTF_8), ManageDetailLoginTest.class);
                setupManageLogin(manageRegisterRequestTest);
                // ACT
                Response actual = manageDetailImplement.login(manageRegisterRequestTest.getLoginRequest());
                ResponseResultTest expected = mapper.readValue(FileUtils.readFileToString(new File(FilenameUtils.concat("./resources/manageDetail/case_login/result", file.getName())), StandardCharsets.UTF_8), ResponseResultTest.class);
                // ASSERT
                assertEquals(expected.getMessage(), actual.getMessage());
                assertEquals(expected.getCode(), actual.getCode());
                assertEquals(expected.isStatus(), actual.isStatus());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private File[] readTestCase() throws Exception {
        File folder = new File("./resources/manageDetail/case_login/testcase");
        return folder.listFiles();
    }

    private void setupManageLogin(ManageDetailLoginTest manageDetailLoginTest) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        when(this.userRepository.findByUsername(any())).thenReturn(manageDetailLoginTest.getManageUser());
    }
}