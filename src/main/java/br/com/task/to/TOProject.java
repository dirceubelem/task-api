package br.com.task.to;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class TOProject {

    private String id;
    private String idAccountOwner;
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Timestamp createdAt;
    private boolean active;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdAccountOwner() {
        return idAccountOwner;
    }

    public void setIdAccountOwner(String idAccountOwner) {
        this.idAccountOwner = idAccountOwner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
