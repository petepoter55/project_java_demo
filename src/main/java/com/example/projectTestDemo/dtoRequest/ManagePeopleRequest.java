package com.example.projectTestDemo.dtoRequest;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.Lob;
import java.util.Date;
@Getter
@Setter
public class ManagePeopleRequest {
    private String name;
    @Lob
    private byte[] content;
    private Date createDate;
    private Date updateDate;
    private String createBy;
}
