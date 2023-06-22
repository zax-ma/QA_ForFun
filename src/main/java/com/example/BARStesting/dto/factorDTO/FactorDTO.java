package com.example.BARStesting.dto.factorDTO;

import com.example.BARStesting.dto.ruleDTO.JoinsDTO;

import java.util.List;

public class FactorDTO<T> {

    private String factorName;

    private int version;

    private String description;

    private String createDate;

    private String creator;

    private String modifyDate;

    private String modifier;

    private String type;

    private String status;

    private String errorMessage;

    private String period;

    private boolean repeatable;

    private String instancePeriod;

    private T properties;

    private List<JoinsDTO> joins;

    public FactorDTO() {
    }


    public FactorDTO(String factorName,
                     int version,
                     String description,
                     String createDate,
                     String creator,
                     String modifyDate,
                     String modifier,
                     String type,
                     String status,
                     String errorMessage,
                     String period,
                     boolean repeatable,
                     String instancePeriod,
                     T properties,
                     List<JoinsDTO> joins) {
        this.factorName = factorName;
        this.version = version;
        this.description = description;
        this.createDate = createDate;
        this.creator = creator;
        this.modifyDate = modifyDate;
        this.modifier = modifier;
        this.type = type;
        this.status = status;
        this.errorMessage = errorMessage;
        this.period = period;
        this.repeatable = repeatable;
        this.instancePeriod = instancePeriod;
        this.properties = properties;
        this.joins = joins;
    }

    public String getFactorName() {
        return factorName;
    }

    public void setFactorName(String factorName) {
        this.factorName = factorName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public boolean isRepeatable() {
        return repeatable;
    }

    public void setRepeatable(boolean repeatable) {
        this.repeatable = repeatable;
    }

    public String getInstancePeriod() {
        return instancePeriod;
    }

    public void setInstancePeriod(String instancePeriod) {
        this.instancePeriod = instancePeriod;
    }

    public T getProperties() {
        return properties;
    }

    public void setProperties(T properties) {
        this.properties = properties;
    }

    public List<JoinsDTO> getJoins() {
        return joins;
    }

    public void setJoins(List<JoinsDTO> joins) {
        this.joins = joins;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "FactorDTO{" +
                "factorName='" + factorName + '\'' +
                ", version=" + version +
                ", description='" + description + '\'' +
                ", createDate='" + createDate + '\'' +
                ", creator='" + creator + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", modifier='" + modifier + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", period='" + period + '\'' +
                ", repeatable=" + repeatable +
                ", instancePeriod='" + instancePeriod + '\'' +
                ", properties=" + properties +
                ", joins=" + joins +
                '}';
    }
}
