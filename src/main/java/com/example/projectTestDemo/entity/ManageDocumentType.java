package com.example.projectTestDemo.entity;

import com.example.projectTestDemo.schema.Database;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "DOCUMENT_TYPE",schema= Database.DOCUMENT_TYPE)
public class ManageDocumentType {
    @Id
    @Column(name = "DOCUMENTTYPECODE")
    private String documentTypeCode;
    @Column(name = "DOCUMENTTYPENAME")
    private String documentTypeName;
    @Column(name = "MESSAGETYPE")
    private String messageType;
    @Column(name = "TYPE")
    private String type;

    public String getDocumentTypeCode() {
        return documentTypeCode;
    }

    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }

    public String getDocumentTypeName() {
        return documentTypeName;
    }

    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = documentTypeName;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
