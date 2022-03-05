package com.example.projectTestDemo.dtoResponse;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import java.util.Date;

@Getter
@Setter
public class ManagePeopleResponse {
    private int manageId;
    private String name;
    @Lob
    private byte[] content;
    private Date createDate;
    private Date updateDate;
    private String createBy;
}
