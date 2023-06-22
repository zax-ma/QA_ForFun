package com.example.BARStesting.dto.ruleDTO;

import java.util.List;

public class RuleDTO {

    private String ruleName;

    private int version;

    private String description;

    private String status;

    private String createDate;

    private String creator;

    private String modifyDate;

    private String modifier;

    private String errorMessage;

    private List<JoinsDTO> joins;

    private boolean session;

    private String className;

    private String duration;

    private int score;

    private boolean secondPhase;

    private int riskProfileId;

    public RuleDTO() {
    }

    public RuleDTO(String ruleName,
                   int version,
                   String description,
                   String status,
                   String createDate,
                   String creator,
                   String modifyDate,
                   String modifier,
                   String errorMessage,
                   List<JoinsDTO> joins,
                   boolean session,
                   String className,
                   String duration,
                   int score,
                   boolean secondPhase,
                   int riskProfileId) {
        this.ruleName = ruleName;
        this.version = version;
        this.description = description;
        this.status = status;
        this.createDate = createDate;
        this.creator = creator;
        this.modifyDate = modifyDate;
        this.modifier = modifier;
        this.errorMessage = errorMessage;
        this.joins = joins;
        this.session = session;
        this.className = className;
        this.duration = duration;
        this.score = score;
        this.secondPhase = secondPhase;
        this.riskProfileId = riskProfileId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<JoinsDTO> getJoins() {
        return joins;
    }

    public void setJoins(List<JoinsDTO> joins) {
        this.joins = joins;
    }

    public boolean isSession() {
        return session;
    }

    public void setSession(boolean session) {
        this.session = session;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isSecondPhase() {
        return secondPhase;
    }

    public void setSecondPhase(boolean secondPhase) {
        this.secondPhase = secondPhase;
    }

    public int getRiskProfileId() {
        return riskProfileId;
    }

    public void setRiskProfileId(int riskProfileId) {
        this.riskProfileId = riskProfileId;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "RuleDTO{" +
                "ruleName='" + ruleName + '\'' +
                ", version=" + version +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createDate='" + createDate + '\'' +
                ", creator='" + creator + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", modifier='" + modifier + '\'' +
                ", joins=" + joins +
                ", session=" + session +
                ", score=" + score +
                ", secondPhase=" + secondPhase +
                ", riskProfileId=" + riskProfileId +
                '}';
    }
}
