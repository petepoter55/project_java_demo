package com.example.projectTestDemo.service.implement;

import com.example.projectTestDemo.dtoResponse.ManagePeopleViewResponse;
import com.example.projectTestDemo.entity.MangePeopleDetail;
import com.example.projectTestDemo.exception.ResponseException;
import com.example.projectTestDemo.repository.ManagePeopleDetailRepository;
import com.example.projectTestDemo.request.ManageRegisterRequestTest;
import com.example.projectTestDemo.response.ResponseManagePeopleTest;
import com.example.projectTestDemo.service.ManagePeopleService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
public class ManagePeopleImplementTest {
    @InjectMocks
    private ManagePeopleImplement managePeopleImplement;
    @Mock
    private ManagePeopleDetailRepository managePeopleDetailRepository;
    @Mock
    private ManagePeopleService managePeopleService;

    @Test
    public void getDate() throws Exception {
        File[] files = readTestCase();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).setDateFormat(simpleDateFormat);
        for(File file : files){
           try {
               // ARRANGE
               ManageRegisterRequestTest manageRegisterRequestTest = mapper.readValue(FileUtils.readFileToString(file, StandardCharsets.UTF_8), ManageRegisterRequestTest.class);
               setupManagePeople(manageRegisterRequestTest);
               // ACT
               ManagePeopleViewResponse actual = managePeopleImplement.getDate(manageRegisterRequestTest.getMangeRegisterRequest());
               ResponseManagePeopleTest expected = mapper.readValue(FileUtils.readFileToString(new File(FilenameUtils.concat("./resources/managePeopleDetail/result", file.getName())), StandardCharsets.UTF_8), ResponseManagePeopleTest.class);
               // ASSERT
               assertEquals(expected.getManagePeopleDetailResponse().get(0).getAddress(), actual.getManagePeopleDetailResponse().get(0).getAddress());
               assertEquals(expected.getManagePeopleDetailResponse().get(0).getManageTaxId(), actual.getManagePeopleDetailResponse().get(0).getManageTaxId());
               assertEquals(expected.getManagePeopleDetailResponse().get(0).getFirstName(), actual.getManagePeopleDetailResponse().get(0).getFirstName());
               assertEquals(expected.getManagePeopleDetailResponse().get(0).getLastName(), actual.getManagePeopleDetailResponse().get(0).getLastName());
               assertEquals(expected.getManagePeopleDetailResponse().get(0).getVillage(), actual.getManagePeopleDetailResponse().get(0).getVillage());
               assertEquals(expected.getManagePeopleDetailResponse().get(0).getDistrictId(), actual.getManagePeopleDetailResponse().get(0).getDistrictId());
               assertEquals(expected.getManagePeopleDetailResponse().get(0).getEmail(), actual.getManagePeopleDetailResponse().get(0).getEmail());
           }catch (ResponseException e){
              e.printStackTrace();
           }
        }
    }

    private File[] readTestCase() throws Exception {
        File folder = new File("./resources/managePeopleDetail/testcase");
        return folder.listFiles();
    }

    private void setupManagePeople(ManageRegisterRequestTest manageRegisterRequestTest){
        List<MangePeopleDetail> manageRegisterRequestTestArrayList = new ArrayList<>();
        for(MangePeopleDetail data : manageRegisterRequestTest.getMangePeopleDetail()) {
            manageRegisterRequestTestArrayList.add(data);
        }
        // ตาม step ใน method ว่ามีการใช้ Repository อะไรบ้างและต้องเตรียมข้อมูลใน File.JSON ด้วยตาม CASE
        when(this.managePeopleDetailRepository.searchByManagePeopleTaxIdLike(any())).thenReturn(manageRegisterRequestTestArrayList);
    }

}