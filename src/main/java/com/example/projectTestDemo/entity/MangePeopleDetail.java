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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getManagePeopleTaxId() {
        return managePeopleTaxId;
    }

    public void setManagePeopleTaxId(String managePeopleTaxId) {
        this.managePeopleTaxId = managePeopleTaxId;
    }

    public String getManagePeopleName() {
        return managePeopleName;
    }

    public void setManagePeopleName(String managePeopleName) {
        this.managePeopleName = managePeopleName;
    }

    public String getManagePeopleLastName() {
        return managePeopleLastName;
    }

    public void setManagePeopleLastName(String managePeopleLastName) {
        this.managePeopleLastName = managePeopleLastName;
    }

    public String getManagePeopleType() {
        return managePeopleType;
    }

    public void setManagePeopleType(String managePeopleType) {
        this.managePeopleType = managePeopleType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getMoo() {
        return moo;
    }

    public void setMoo(String moo) {
        this.moo = moo;
    }

    public String getSoi() {
        return soi;
    }

    public void setSoi(String soi) {
        this.soi = soi;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getSubProvinceId() {
        return subProvinceId;
    }

    public void setSubProvinceId(int subProvinceId) {
        this.subProvinceId = subProvinceId;
    }

    public int getProviceId() {
        return proviceId;
    }

    public void setProviceId(int proviceId) {
        this.proviceId = proviceId;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTelext() {
        return telext;
    }

    public void setTelext(String telext) {
        this.telext = telext;
    }

    public BigInteger getUid() {
        return uid;
    }

    public void setUid(BigInteger uid) {
        this.uid = uid;
    }

    public Date getApprovedDateTime1() {
        return approvedDateTime1;
    }

    public void setApprovedDateTime1(Date approvedDateTime1) {
        this.approvedDateTime1 = approvedDateTime1;
    }

    public Date getApprovedDateTime2() {
        return approvedDateTime2;
    }

    public void setApprovedDateTime2(Date approvedDateTime2) {
        this.approvedDateTime2 = approvedDateTime2;
    }

    public Date getApprovedDateTime3() {
        return approvedDateTime3;
    }

    public void setApprovedDateTime3(Date approvedDateTime3) {
        this.approvedDateTime3 = approvedDateTime3;
    }

    public Date getApprovedDateTime4() {
        return approvedDateTime4;
    }

    public void setApprovedDateTime4(Date approvedDateTime4) {
        this.approvedDateTime4 = approvedDateTime4;
    }

    public Date getApprovedDateTime5() {
        return approvedDateTime5;
    }

    public void setApprovedDateTime5(Date approvedDateTime5) {
        this.approvedDateTime5 = approvedDateTime5;
    }

    public String getActivateCode() {
        return activateCode;
    }

    public void setActivateCode(String activateCode) {
        this.activateCode = activateCode;
    }

    public Date getActivateCodeCreateDate() {
        return activateCodeCreateDate;
    }

    public void setActivateCodeCreateDate(Date activateCodeCreateDate) {
        this.activateCodeCreateDate = activateCodeCreateDate;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
