package com.example.BARStesting.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskDTO {

    @JsonProperty("DOC_ID")
    private String docId;

    @JsonProperty("DOC_SOURCE")
    private String docSource;

    public TaskDTO() {
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

    @Override
    public String toString() {
        return "TaskDTO{" +
                "docId='" + docId + '\'' +
                ", docSource='" + docSource + '\'' +
                '}';
    }
}
