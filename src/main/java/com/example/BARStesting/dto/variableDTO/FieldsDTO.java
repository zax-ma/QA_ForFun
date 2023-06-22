package com.example.BARStesting.dto.variableDTO;

public class FieldsDTO {

    private String documentType;

    private String documentTypeAnchor;

    private String fieldName;

    public FieldsDTO() {
    }

    public FieldsDTO(String documentType, String documentTypeAnchor, String fieldName) {
        this.documentType = documentType;
        this.documentTypeAnchor = documentTypeAnchor;
        this.fieldName = fieldName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentTypeAnchor() {
        return documentTypeAnchor;
    }

    public void setDocumentTypeAnchor(String documentTypeAnchor) {
        this.documentTypeAnchor = documentTypeAnchor;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return "FieldsDTO{" +
                "documentType='" + documentType + '\'' +
                ", documentTypeAnchor='" + documentTypeAnchor + '\'' +
                ", fieldName='" + fieldName + '\'' +
                '}';
    }
}
