package com.example.projectTestDemo.request;


import com.example.projectTestDemo.dtoRequest.MangeRegisterRequest;
import com.example.projectTestDemo.dtoResponse.ManagePeopleDetailResponse;
import com.example.projectTestDemo.entity.MangePeopleDetail;
import lombok.Data;

import java.util.List;

@Data
public class ManageRegisterRequestTest {
    private MangeRegisterRequest mangeRegisterRequest;
    private List<MangePeopleDetail> mangePeopleDetail;
}
