package com.example.projectTestDemo.dtoRequest;


public class CreateAccountRequest {
    private String username;
    private String password;
    private String confirmPassword;
    private String peopleDetailId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPeopleDetailId() {
        return peopleDetailId;
    }

    public void setPeopleDetailId(String peopleDetailId) {
        this.peopleDetailId = peopleDetailId;
    }
}
