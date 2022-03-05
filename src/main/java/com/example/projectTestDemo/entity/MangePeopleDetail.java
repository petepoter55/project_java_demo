package com.example.projectTestDemo.entity;

import com.example.projectTestDemo.schema.Database;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Data
@Table(name = "PEOPLEDETAIL",schema= Database.PEOPLEDETAIL)
public class MangePeopleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "REFNO")
    private String refNo;
    @Column(name = "MANAGEPEOPLETAXID")
    private String managePeopleTaxId;
    @Column(name = "MANAGEPEOPLENAME")
    private String managePeopleName;
    @Column(name = "MANAGEPEOPLELASTNAME")
    private String managePeopleLastName;
    @Column(name = "MANAGEPEOPLETYPE")
    private String managePeopleType;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "BUILDING")
    private String building;
    @Column(name = "ROOMNO")
    private String roomNo;
    @Column(name = "FLOOR")
    private String floor;
    @Column(name = "VILLAGE")
    private String village;
    @Column(name = "moo")
    private String moo;
    @Column(name = "SOI")
    private String soi;
    @Column(name = "ROAD")
    private String road;
    @Column(name = "DISTRICTID")
    private int districtId;
    @Column(name = "SUBPROVINCEID")
    private int subProvinceId;
    @Column(name = "PROVINCEID")
    private int proviceId;
    @Column(name = "POSTCODE")
    private String postCode;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NEWEMAIL")
    private String newEmail;
    @Column(name = "TEL")
    private String tel;
    @Column(name = "TELEXT")
    private String telext;
    @Column(name = "UID")
    private BigInteger uid;
    @Column(name = "APPROVEDATETIME1")
    private Date approvedDateTime1;
    @Column(name = "APPROVEDATETIME2")
    private Date approvedDateTime2;
    @Column(name = "APPROVEDATETIME3")
    private Date approvedDateTime3;
    @Column(name = "APPROVEDATETIME4")
    private Date approvedDateTime4;
    @Column(name = "APPROVEDATETIME5")
    private Date approvedDateTime5;
    @Column(name = "ACTIVATECODE")
    private String activateCode;
    @Column(name = "ACTIVATECODECREATETIME")
    private Date activateCodeCreateDate;
    @Column(name = "STATUSNAME")
    private String statusName;
    @Column(name = "IPADDRESS")
    private String ip;
    @Column(name = "CREATEDATETIME")
    private Date createDateTime;
    @Column(name = "UPDATEDATETIME")
    private Date updateDateTime;

}
