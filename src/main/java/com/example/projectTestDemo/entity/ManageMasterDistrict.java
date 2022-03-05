package com.example.projectTestDemo.entity;

import com.example.projectTestDemo.schema.Database;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "MASTER_DISTRICT",schema= Database.PEOPLEDETAIL)
public class ManageMasterDistrict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "CODE")
    private String code;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NAMEENG")
    private String nameEng;
    @Column(name = "SUBPROVINCEID")
    private int subproviceId;
    @Column(name = "PROVINCEID")
    private int provinceId;
    @Column(name = "SUBPROVINCECODE")
    private String subProvinceCode;
    @Column(name = "PROVINCECODE")
    private String provinceCode;
}
