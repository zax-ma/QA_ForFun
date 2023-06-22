package com.example.BARStesting.dto.controlDTO;

import java.util.List;

public class CheckResultDTO {

    private DocumentIdDTO document;

    private String result;

    private String resolutionComment;

    private String errorMessage;

    private List<String> processes;

    private boolean isCritical;

    private String categories;

    public CheckResultDTO() {
    }

    public CheckResultDTO(DocumentIdDTO document,
                          String result,
                          String resolutionComment,
                          String errorMessage,
                          List<String> processes,
                          boolean isCritical,
                          String categories) {
        this.document = document;
        this.result = result;
        this.resolutionComment = resolutionComment;
        this.errorMessage = errorMessage;
        this.processes = processes;
        this.isCritical = isCritical;
        this.categories = categories;
    }

    public DocumentIdDTO getDocument() {
        return document;
    }

    public void setDocument(DocumentIdDTO document) {
        this.document = document;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResolutionComment() {
        return resolutionComment;
    }

    public void setResolutionComment(String resolutionComment) {
        this.resolutionComment = resolutionComment;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getProcesses() {
        return processes;
    }

    public void setProcesses(List<String> processes) {
        this.processes = processes;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "CheckResultDTO{" +
                "document=" + document +
                ", result='" + result + '\'' +
                ", resolutionComment='" + resolutionComment + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", processes=" + processes +
                ", isCritical=" + isCritical +
                ", categories='" + categories + '\'' +
                '}';
    }
}
