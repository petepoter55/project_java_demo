package com.example.projectTestDemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DatabindLOGIN {
    public static class UserName{
        public String type;
        public int minLength;
        public int maxLength;
    }

    public static class Password{
        public String type;
        public int minLength;
        public int maxLength;
    }

    public static class Properties implements com.example.projectTestDemo.model.Properties{
        public UserName username;
        public Password password;
    }

    public static class Root implements com.example.projectTestDemo.model.Root {
        @JsonProperty("$schema")
        public String schema;
        public String type;
        public Properties properties;
        public List<String> required;


        @Override
        public String getSchema() {
            return schema;
        }

        @Override
        public String getType() {
            return type;
        }

        @Override
        public Properties getProperties() {
            return properties;
        }

        @Override
        public List<String> getRequired() {
            return required;
        }

        public void setSchema(String schema) {
            this.schema = schema;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setProperties(Properties properties) {
            this.properties = properties;
        }

        public void setRequired(List<String> required) {
            this.required = required;
        }
    }
}
