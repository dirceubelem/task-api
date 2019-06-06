package br.com.task.to;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class TOTask {

    private String id;
    private String idProject;
    private Integer idStatus;
    private String idAccountFrom;
    private String idAccountTo;
    private String name;
    private String description;
    private String tags;
    private Double estimate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone="GMT-3")
    private Timestamp createdAt;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone="GMT-3")
    private Timestamp startedAt;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone="GMT-3")
    private Timestamp deliveredAt;
    private Integer priority;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public String getIdAccountFrom() {
        return idAccountFrom;
    }

    public void setIdAccountFrom(String idAccountFrom) {
        this.idAccountFrom = idAccountFrom;
    }

    public String getIdAccountTo() {
        return idAccountTo;
    }

    public void setIdAccountTo(String idAccountTo) {
        this.idAccountTo = idAccountTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Double getEstimate() {
        return estimate;
    }

    public void setEstimate(Double estimate) {
        this.estimate = estimate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Timestamp startedAt) {
        this.startedAt = startedAt;
    }

    public Timestamp getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(Timestamp deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
