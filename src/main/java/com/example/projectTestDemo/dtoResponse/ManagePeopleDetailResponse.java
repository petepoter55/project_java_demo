package com.example.projectTestDemo.dtoResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ManagePeopleDetailResponse {
    private String manageTaxId;
    private String refNo;
    private String firstName;
    private String lastName;
    private String address;
    private String building;
    private String roomNo;
    private String floor;
    private String village;
    private String moo;
    private String soi;
    private String road;
    private int districtId;
    private int subProvinceId;
    private int proviceId;
    private String postCode;
    private String email;
    private String newEmail;
    private String tel;
    private String telext;
//    private Date lastUpdate;
    private Date createDateTime;
    private Date updateDateTime;
}
