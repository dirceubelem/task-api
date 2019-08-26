package br.com.task.to;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class TOAccount {

    private String id;
    private String name;
    private String email;
    private String password;
    private String token;
    private String picture;
    private boolean active;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-3")
    private Timestamp createdAt;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-3")
    private Timestamp expiredAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Timestamp expiredAt) {
        this.expiredAt = expiredAt;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
