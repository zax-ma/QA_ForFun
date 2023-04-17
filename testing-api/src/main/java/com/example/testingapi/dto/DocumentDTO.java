package com.example.testingapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DocumentDTO {
    private String name;
    private String description;

    private List<AttributeDTO> attributes;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS+00:00")
    private Date dtCreate = new Date();
    private int version = 1;

    public DocumentDTO(String name, String description, List<AttributeDTO> attributes) {
        this.name = name;
        this.description = description;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AttributeDTO> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeDTO> attributes) {
        this.attributes = attributes;
    }

    public Date getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(Date dtCreate) {
        this.dtCreate = dtCreate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "DocumentDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", attributes=" + attributes +
                ", dtCreate=" + dtCreate +
                ", version=" + version +
                '}';
    }
}
