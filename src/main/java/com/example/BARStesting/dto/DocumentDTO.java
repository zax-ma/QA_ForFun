package com.example.BARStesting.dto;

import java.util.List;

public class DocumentDTO {

    private String docType;

    private int version;

    private String description;

 //   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS+00:00")
    private String createDate;

    private String creator;

 //   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS+00:00")
    private String modifyDate;

    private String modifier;

    private List<AttributeDTO> attributes;

    private String docTypeAnchor;

    private List<String> wlcFactorNames;

    private String filter;

    public DocumentDTO() {
    }

    public DocumentDTO(String docType,
                       int version,
                       String description,
                       String createDate,
                       String creator,
                       String modifyDate,
                       String modifier,
                       List<AttributeDTO> attributes,
                       String docTypeAnchor,
                       List<String> wlcFactorNames,
                       String filter) {
        this.docType = docType;
        this.version = version;
        this.description = description;
        this.createDate = createDate;
        this.creator = creator;
        this.modifyDate = modifyDate;
        this.modifier = modifier;
        this.attributes = attributes;
        this.docTypeAnchor = docTypeAnchor;
        this.wlcFactorNames = wlcFactorNames;
        this.filter = filter;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
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

    public List<AttributeDTO> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeDTO> attributes) {
        this.attributes = attributes;
    }

    public String getDocTypeAnchor() {
        return docTypeAnchor;
    }

    public void setDocTypeAnchor(String docTypeAnchor) {
        this.docTypeAnchor = docTypeAnchor;
    }

    public List<String> getWlcFactorNames() {
        return wlcFactorNames;
    }

    public void setWlcFactorNames(List<String> wlcFactorNames) {
        this.wlcFactorNames = wlcFactorNames;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Override
    public String toString() {
        return "DocumentDTO{" +
                "docType='" + docType + '\'' +
                ", version=" + version +
                ", description='" + description + '\'' +
                ", createDate='" + createDate + '\'' +
                ", creator='" + creator + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", modifier='" + modifier + '\'' +
                ", attributes=" + attributes +
                ", docTypeAnchor='" + docTypeAnchor + '\'' +
                ", wlcFactorNames=" + wlcFactorNames +
                ", filter='" + filter + '\'' +
                '}';
    }
}
