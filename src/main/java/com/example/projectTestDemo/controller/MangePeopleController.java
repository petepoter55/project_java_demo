package com.example.projectTestDemo.controller;

import com.example.projectTestDemo.dtoRequest.ManageDetailGenXmlRequest;
import com.example.projectTestDemo.dtoRequest.MangeRegisterRequest;
import com.example.projectTestDemo.dtoResponse.ManagePeopleViewResponse;
import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.repository.ManageMasterDistrictRepository;
import com.example.projectTestDemo.service.ManageDetailService;
import com.example.projectTestDemo.service.ManagePeopleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MangePeopleController {

    @Autowired
    ManagePeopleService managePeopleService;
    @Autowired
    ManageDetailService manageDetailService;
    @Autowired
    ManageMasterDistrictRepository manageMasterDistrictRepository;


    @GetMapping(value = "/getData",consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ManagePeopleViewResponse getDatePeople(
            @RequestBody MangeRegisterRequest mangeRegisterRequest
    ){
        return this.managePeopleService.getDate(mangeRegisterRequest);
    }

    @GetMapping(value = "/getData1")
    public String getDataTest(
//            @RequestBody MangeRegisterRequest mangeRegisterRequest
    ){
        return this.manageDetailService.gen();
    }


    @PostMapping(value = "/createRegister" ,consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Response createRegister(
            @RequestBody MangeRegisterRequest mangeRegisterRequest
    ){
        return this.managePeopleService.createDataPeople(mangeRegisterRequest);
    }

    @PatchMapping(value = "/updateData")
    public Response updateData(
            @RequestParam(name = "id") Integer itemid
    ){
        return this.managePeopleService.updateDataPeople(itemid);
    }

    @DeleteMapping(value = "/deleteData")
    public Response deleteDate(
            @RequestParam(name = "id") Integer itemid
    ){
        return this.managePeopleService.deleteDate(itemid);
    }

    @PostMapping(value = "/generateXml")
    public String generateXml(
            @RequestBody ManageDetailGenXmlRequest manageDetailGenXmlRequest
    ) throws JsonProcessingException {
        return this.manageDetailService.generateXml();
    }

    @PostMapping(value = "/sendQueue")
    public Response sendQueue(
    ){
        return this.manageDetailService.sendMessageToQueue();
    }

}
