package com.example.projectTestDemo.service.implement;

import com.example.projectTestDemo.dtoResponse.JwtResponse;
import com.example.projectTestDemo.exception.ResponseException;
import com.example.projectTestDemo.repository.UserRepository;
import com.example.projectTestDemo.request.GetDataTokenRequestTest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.Assert.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class JwtImplementTest {

       @InjectMocks
       private JwtImplement jwtImplement;
       @Mock
       private UserRepository userRepository;

       @Before
       public void setUp() {
              ReflectionTestUtils.setField(jwtImplement, "jwtSecretkey",
                            "sdsdlkldfsfkdjsjfgkowflksnfsjfiossmlvsjfiejiwjroiewjirjijwfwjirwjirjiowjirjiwjifjikjsfcksnfjwsifjioejrijwirjfi");
       }

       @Test
       public void getDataToken() throws Exception {

              File[] files = readTestCase();
              SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              ObjectMapper mapper = new ObjectMapper()
                            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                            .setDateFormat(simpleDateFormat);
              try {
                     for (File file : files) {
                            // ARRANGE
                            GetDataTokenRequestTest getDataTokenRequestTest = mapper.readValue(file, GetDataTokenRequestTest.class);
                            setupManageLogin(getDataTokenRequestTest);
                            // ACT
                            JwtResponse actual = this.jwtImplement.getDataToken(getDataTokenRequestTest.getLoginRequest());
                            Map<String, Object> expected = mapper.readValue(FileUtils.readFileToString(new File(FilenameUtils.concat("./resources/manageDetail/case_JWTtoken/result", file.getName())), StandardCharsets.UTF_8), Map.class);
                            //ASSERT
                            assertEquals(expected.get("username"),actual.getUsername());
                            assertEquals(expected.get("email"),actual.getEmail());
                            assertEquals(expected.get("managePeopleTaxId"),actual.getManagePeopleTaxId());
                            assertEquals(expected.get("issueDate"),actual.getIssueDate().toString());
                     }
              } catch (ResponseException e) {
                     e.printStackTrace();
              }
       }

       private File[] readTestCase() throws Exception {
              File folder = new File("./resources/manageDetail/case_JWTtoken/testcase");
              return folder.listFiles();
       }

       private void setupManageLogin(GetDataTokenRequestTest getDataTokenRequestTest)
                     throws UnsupportedEncodingException, NoSuchAlgorithmException {
              when(this.userRepository.findByUsername(any())).thenReturn(getDataTokenRequestTest.getManageUser());
       }
}