package br.com.task.to;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class TOTask {

    private String id;
    private String idProject;
    private int idStatus;
    private String idAccountFrom;
    private String idAccountTo;
    private String name;
    private String description;
    private String tags;
    private double estimate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Timestamp createdAt;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Timestamp startedAt;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Timestamp deliveredAt;
    private int priority;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
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

    public double getEstimate() {
        return estimate;
    }

    public void setEstimate(double estimate) {
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }
}
