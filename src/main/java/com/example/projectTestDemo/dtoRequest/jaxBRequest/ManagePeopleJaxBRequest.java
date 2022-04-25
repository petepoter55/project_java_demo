package com.example.projectTestDemo.dtoRequest.jaxBRequest;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "ManagePeopleJaxBRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class ManagePeopleJaxBRequest {
    @XmlElement(name = "ManageID", required = true) // use name element ManageID
    private ManagePeopleTaxId managePeopleTaxId;

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = { "lastName", "firstName"}) // order element in tag ManageID
    public static class ManagePeopleTaxId {
        @XmlElement(name = "FirstName", required = true)
        private String firstName;
        @XmlElement(name = "LastName", required = true)
        private String lastName;
    }


}
