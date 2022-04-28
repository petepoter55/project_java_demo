package com.example.projectTestDemo.controller;

import com.example.projectTestDemo.dtoRequest.ManageDetailGenXmlRequest;
import com.example.projectTestDemo.dtoRequest.MangeRegisterRequest;
import com.example.projectTestDemo.dtoResponse.ManagePeopleViewResponse;
import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.repository.ManageMasterDistrictRepository;
import com.example.projectTestDemo.service.ManageDetailService;
import com.example.projectTestDemo.service.ManagePeopleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
public class MangePeopleController {

    @Autowired
    ManagePeopleService managePeopleService;
    @Autowired
    ManageDetailService manageDetailService;
    @Autowired
    ManageMasterDistrictRepository manageMasterDistrictRepository;

    @ApiOperation(value = "get Data ManagePeople", notes = "Inquiry ManagePeople by manageId")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 409, message = "Business Error"),
            @ApiResponse(code = 500, message = "Internal server error occurred"),
            @ApiResponse(code = 503, message = "Service Unavailable")})
    @RequestMapping(value = "/getData/{manageId}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody ManagePeopleViewResponse getDatePeople(
            @ApiParam(name = "manageId", value = "The manage ID", required = true)
            @PathVariable(value = "manageId") String manageId
    ){
        return this.managePeopleService.getDate(manageId);
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
