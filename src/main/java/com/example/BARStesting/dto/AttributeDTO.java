package com.example.BARStesting.dto;


public class AttributeDTO {

    private String attributeName;

    private int version;

    private String description;

    private String createDate;

    private String creator;

    private String modifyDate;

    private String modifier;

    public AttributeDTO() {
    }

    public AttributeDTO(String attributeName, int version, String description, String createDate, String creator, String modifyDate, String modifier) {
        this.attributeName = attributeName;
        this.version = version;
        this.description = description;
        this.createDate = createDate;
        this.creator = creator;
        this.modifyDate = modifyDate;
        this.modifier = modifier;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "AttributeDTO{" +
                "attributeName='" + attributeName + '\'' +
                ", version=" + version +
                ", description='" + description + '\'' +
                ", createDate='" + createDate + '\'' +
                ", creator='" + creator + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", modifier='" + modifier + '\'' +
                '}';
    }
}
