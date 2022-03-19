package com.example.projectTestDemo.entity;

import com.example.projectTestDemo.schema.Database;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "UPLOADXML_TRACKING",schema= Database.UPLOADXML_TRACKING)
public class ManageUploadTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "FILENAME")
    private String fileName;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "SENDDATE")
    private Date sendDate;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "MANAGEID")
    private Integer manageId;
    @Column(name = "CPID")
    private Integer cpid;
    @Column(name = "FILECOUNT")
    private String fileCount;
    @Column(name = "FILESUCCESS")
    private String fileSuccess;
    @Column(name = "FILEFAIL")
    private String fileFail;
    @Column(name = "CREATEDATETIME")
    private Date createDateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getManageId() {
        return manageId;
    }

    public void setManageId(Integer manageId) {
        this.manageId = manageId;
    }

    public Integer getCpid() {
        return cpid;
    }

    public void setCpid(Integer cpid) {
        this.cpid = cpid;
    }

    public String getFileCount() {
        return fileCount;
    }

    public void setFileCount(String fileCount) {
        this.fileCount = fileCount;
    }

    public String getFileSuccess() {
        return fileSuccess;
    }

    public void setFileSuccess(String fileSuccess) {
        this.fileSuccess = fileSuccess;
    }

    public String getFileFail() {
        return fileFail;
    }

    public void setFileFail(String fileFail) {
        this.fileFail = fileFail;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }
}
