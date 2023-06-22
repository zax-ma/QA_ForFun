package com.example.BARStesting.dto.controlDTO;

public class FactorInstanceStatusDTO {

    private int id;

    private String status;

    public FactorInstanceStatusDTO() {
    }

    public FactorInstanceStatusDTO(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FactorInstanceStatusDTO{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
