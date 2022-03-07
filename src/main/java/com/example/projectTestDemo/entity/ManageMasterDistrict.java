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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public int getSubproviceId() {
        return subproviceId;
    }

    public void setSubproviceId(int subproviceId) {
        this.subproviceId = subproviceId;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getSubProvinceCode() {
        return subProvinceCode;
    }

    public void setSubProvinceCode(String subProvinceCode) {
        this.subProvinceCode = subProvinceCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}
