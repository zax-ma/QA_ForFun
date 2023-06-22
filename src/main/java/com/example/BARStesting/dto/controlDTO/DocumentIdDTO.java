package com.example.BARStesting.dto.controlDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentIdDTO {

    @JsonProperty("DOC_SOURCE")
    String doc_source;

    @JsonProperty("DOC_ID")
    String doc_id;

    @JsonProperty("STATUS")
    String status;

    @JsonProperty("COMMENT")
    String comment;

    @JsonProperty("USER_ID")
    String user_id;

    public DocumentIdDTO() {
    }

    public DocumentIdDTO(String doc_source, String doc_id, String status, String comment, String user_id) {
        this.doc_source = doc_source;
        this.doc_id = doc_id;
        this.status = status;
        this.comment = comment;
        this.user_id = user_id;
    }

    public String getDoc_source() {
        return doc_source;
    }

    public void setDoc_source(String doc_source) {
        this.doc_source = doc_source;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "DocumentTaskDTO{" +
                "doc_source='" + doc_source + '\'' +
                ", doc_id='" + doc_id + '\'' +
                ", status='" + status + '\'' +
                ", comment='" + comment + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
