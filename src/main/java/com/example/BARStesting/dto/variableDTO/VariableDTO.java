package com.example.BARStesting.dto.variableDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({
        "id",
        "anchor",
        "period",
        "function",
        "value",
        "isAggregate",
        "version",
        "creator",
        "createDate",
        "modifier",
        "modifyDate",
        "description",
        "fields"
    })
public class VariableDTO {

    private String id;

    private String anchor;

    private String period;

    private String function;

    private String value;

    @JsonProperty("isAggregate")
    private boolean isAggregate = true;

    private int version;

    private String creator;

    private String createDate;

    private String modifier;

    private String modifyDate;

    private String description;

    private List<FieldsDTO> fields;

    public VariableDTO() {
    }

    public VariableDTO(String id,
                       String anchor,
                       String period,
                       String function,
                       String value,
                       boolean isAggregate,
                       int version,
                       String creator,
                       String createDate,
                       String modifier,
                       String modifyDate,
                       String description,
                       List<FieldsDTO> fields) {
        this.id = id;
        this.anchor = anchor;
        this.period = period;
        this.function = function;
        this.value = value;
        this.isAggregate = isAggregate;
        this.version = version;
        this.creator = creator;
        this.createDate = createDate;
        this.modifier = modifier;
        this.modifyDate = modifyDate;
        this.description = description;
        this.fields = fields;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnchor() {
        return anchor;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FieldsDTO> getFields() {
        return fields;
    }

    public void setFields(List<FieldsDTO> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "VariableDTO{" +
                "id='" + id + '\'' +
                ", anchor='" + anchor + '\'' +
                ", period='" + period + '\'' +
                ", function='" + function + '\'' +
                ", value='" + value + '\'' +
                ", isAggregate=" + isAggregate +
                ", version=" + version +
                ", creator='" + creator + '\'' +
                ", createDate='" + createDate + '\'' +
                ", modifier='" + modifier + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", description='" + description + '\'' +
                ", fields=" + fields +
                '}';
    }
}
