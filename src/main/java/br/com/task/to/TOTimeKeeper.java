package br.com.task.to;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class TOTimeKeeper {

    private String id;
    private String idTask;
    private String idAccount;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone="GMT-3")
    private Timestamp startedAt;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone="GMT-3")
    private Timestamp finalizedAt;
    private Integer time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdTask() {
        return idTask;
    }

    public void setIdTask(String idTask) {
        this.idTask = idTask;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public Timestamp getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Timestamp startedAt) {
        this.startedAt = startedAt;
    }

    public Timestamp getFinalizedAt() {
        return finalizedAt;
    }

    public void setFinalizedAt(Timestamp finalizedAt) {
        this.finalizedAt = finalizedAt;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
    
}
