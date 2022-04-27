package com.example.projectTestDemo.model;

import java.util.List;

public interface Root {
    public String getSchema();

    public String getType();

    public Properties getProperties();

    public List<String> getRequired();
}
