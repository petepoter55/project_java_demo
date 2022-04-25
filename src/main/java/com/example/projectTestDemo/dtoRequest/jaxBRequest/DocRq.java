package com.example.projectTestDemo.dtoRequest.jaxBRequest;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DocRq")
public class DocRq<T> {
    @XmlElement(name = "ServiceName")
    private String serviceName;
    @XmlElement(name = "SignonSessionRq")
    private SignOnSessionRq signonSessionRq;
    @XmlAnyElement(lax = true)
    private T data;

    public DocRq() {
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public SignOnSessionRq getSignonSessionRq() {
        return signonSessionRq;
    }

    public void setSignonSessionRq(SignOnSessionRq signonSessionRq) {
        this.signonSessionRq = signonSessionRq;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
