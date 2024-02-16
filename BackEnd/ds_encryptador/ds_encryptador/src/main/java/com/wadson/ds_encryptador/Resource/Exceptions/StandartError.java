package com.wadson.ds_encryptador.Resource.Exceptions;

import jakarta.persistence.Column;

import java.time.Instant;

public class StandartError {
    @Column(columnDefinition = "TIMEOUT WITHOUT TIMESTAMP")
    private Instant timestamp;
    private String message;
    private String err;
    private String path;
    private Integer status;

    public StandartError() {
    }

    public StandartError(Instant timestamp, String message, String err, String path, Integer status) {
        this.timestamp = timestamp;
        this.message = message;
        this.err = err;
        this.path = path;
        this.status = status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
