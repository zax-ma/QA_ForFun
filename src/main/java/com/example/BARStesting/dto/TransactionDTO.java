package com.example.BARStesting.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionDTO {

    @JsonProperty("DOC_ID")
    private String docId;

    @JsonProperty("DOC_SOURCE")
    private String docSource;

    @JsonProperty("DOC_TYPE")
    private String docType;

    @JsonProperty("PRECHECK")
    private String precheck;

    public TransactionDTO() {
    }

    public TransactionDTO(String docId, String docSource, String docType, String precheck) {
        this.docId = docId;
        this.docSource = docSource;
        this.docType = docType;
        this.precheck = precheck;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocSource() {
        return docSource;
    }

    public void setDocSource(String docSource) {
        this.docSource = docSource;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getPrecheck() {
        return precheck;
    }

    public void setPrecheck(String precheck) {
        this.precheck = precheck;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "docId='" + docId + '\'' +
                ", docSource='" + docSource + '\'' +
                ", docType='" + docType + '\'' +
                ", precheck='" + precheck + '\'' +
                '}';
    }
}
