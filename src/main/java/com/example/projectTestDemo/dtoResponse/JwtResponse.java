package com.example.projectTestDemo.dtoResponse;

import java.util.Date;


public class JwtResponse {
    private String username;
    private String email;
    private String managePeopleTaxId;
    private Date issueDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getManagePeopleTaxId() {
        return managePeopleTaxId;
    }

    public void setManagePeopleTaxId(String managePeopleTaxId) {
        this.managePeopleTaxId = managePeopleTaxId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
}
