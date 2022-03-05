package com.example.projectTestDemo.entity;

import com.example.projectTestDemo.schema.Database;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Data

@Table(name = "MANAGE_USER",schema= Database.USER)
public class ManageUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UID")
    private BigInteger uid;

    @Column(name = "UCODE")
    private String ucode;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String lastName;
    @Column(name = "POSTCODE")
    private String postCode;
    @Column(name = "TELNO")
    private String telNo;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "APPROVED")
    private String approved;
    @Column(name = "APPROVEDDATE")
    private Date approvedDate;
    @Column(name = "STATUSLOGIN")
    private String statusLogin;
    @Column(name = "LASTLOGIN")
    private Date lastLogin;
    @Column(name = "PASSWORDDATE")
    private Date passwordDate;
    @Column(name = "CREATEDATE")
    private Date createDate;
    @Column(name = "FORCECHANGEPASSWORD")
    private int forceChangePassword;
    @Column(name = "TOKEN_USER")
    private String token_user;
    @Column(name = "CREATEBY")
    private BigInteger createBy;
}
