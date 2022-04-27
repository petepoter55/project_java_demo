package com.example.projectTestDemo.dtoRequest.jaxBRequest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SignOnSessionRq {
    @XmlElement(name = "user_id")
    private String userId;
    @XmlElement(name = "session")
    private String session;
    @XmlElement(name = "lang")
    private String lang;

    public SignOnSessionRq() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
