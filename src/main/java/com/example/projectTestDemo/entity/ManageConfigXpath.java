package com.example.projectTestDemo.entity;

import com.example.projectTestDemo.schema.Database;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "CONFIG_XPATH",schema= Database.CONFIG_XPATH)
public class ManageConfigXpath {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "XPATH")
    private String xpath;
    @Column(name = "DESC")
    private String desc;
    @Column(name = "ISACTIVE")
    private String isActive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
