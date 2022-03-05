package com.example.projectTestDemo.service;

import com.example.projectTestDemo.dtoRequest.ManagePeopleRequest;
import com.example.projectTestDemo.dtoRequest.MangeRegisterRequest;
import com.example.projectTestDemo.dtoResponse.ManagePeopleViewResponse;
import com.example.projectTestDemo.dtoResponse.Response;
import com.example.projectTestDemo.entity.ManageMasterDistrict;

import java.util.List;


public interface ManagePeopleService {

    public ManagePeopleViewResponse getDate(MangeRegisterRequest mangeRegisterRequest);

    public Response deleteDate(int id);

    public Response createDataPeople(MangeRegisterRequest mangeRegisterRequest);

    public Response updateDataPeople(int id);

    public List<ManageMasterDistrict> getTest ();
}
