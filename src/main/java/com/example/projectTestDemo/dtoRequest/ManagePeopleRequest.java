package com.example.projectTestDemo.dtoRequest;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.Lob;
import java.util.Date;

public class ManagePeopleRequest {
    private String name;
    @Lob
    private byte[] content;
    private Date createDate;
    private Date updateDate;
    private String createBy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
