package com.example.BARStesting.dto.ruleDTO;

public class JoinsDTO {

    private String ruleName;

    private String factorName;

    private int orderNum;

    public JoinsDTO() {
    }

    public JoinsDTO(String ruleName, String factorName, int orderNum) {
        this.ruleName = ruleName;
        this.factorName = factorName;
        this.orderNum = orderNum;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getFactorName() {
        return factorName;
    }

    public void setFactorName(String factorName) {
        this.factorName = factorName;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "JoinsDTO{" +
                "ruleName='" + ruleName + '\'' +
                ", factorName='" + factorName + '\'' +
                ", orderNum=" + orderNum +
                '}';
    }
}
