package com.example.projectTestDemo.entity;

import com.example.projectTestDemo.schema.Database;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "UPLOAD_TEMP",schema= Database.UPLOAD_TEMP)
public class ManageUploadTemp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "TRACKINGID")
    private Integer trackingId;
    @Column(name = "FILENAME")
    private String fileName;
    @Column(name = "XMLDATA")
    private byte[] xmlData;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "ISSUCCESS")
    private Integer isSuccess;
    @Column(name = "RESPONSE")
    private byte[] response;
    @Column(name = "STARTDATE")
    private Date startDate;
    @Column(name = "ENDDATE")
    private Date endDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(Integer trackingId) {
        this.trackingId = trackingId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getXmlData() {
        return xmlData;
    }

    public void setXmlData(byte[] xmlData) {
        this.xmlData = xmlData;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public byte[] getResponse() {
        return response;
    }

    public void setResponse(byte[] response) {
        this.response = response;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
