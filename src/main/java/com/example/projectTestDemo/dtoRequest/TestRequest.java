package com.example.projectTestDemo.dtoRequest;

import lombok.Getter;
import lombok.Setter;

public class TestRequest {
    private final String firstname;
    private final String lastName;
    private final String username;


    public TestRequest(String firstname, String lastName, String username) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.username = username;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getUsername() {
        return this.username;
    }


    public static class Builder{
        private String firstname;
        private String lastName;
        private String username;

        public Builder() {
        }

        public Builder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public TestRequest build() {
            return new TestRequest(this.firstname, this.lastName, this.username);
        }
    }
}
