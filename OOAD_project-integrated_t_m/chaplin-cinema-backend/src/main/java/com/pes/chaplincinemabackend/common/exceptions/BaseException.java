package com.pes.chaplincinemabackend.common.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class BaseException extends RuntimeException{
    private final Date timestamp = new Date();
    private String error;
    private HttpStatus status;

    public Date getTimestamp() {
        return timestamp;
    }

    public String getError() {
        return error;
    }


    public HttpStatus getStatus() {
        return status;
    }


    public BaseException(HttpStatus status,
                                          String error) {
        this.status = status;
        this.error = error;
    }

    public BaseException(HttpStatus status, String error, String reason) {
        super(reason);
        this.error = error;
        this.status = status;
    }

    public BaseException(HttpStatus status, String error, String reason, Throwable cause) {
        super(reason, cause);
        this.error = error;
        this.status = status;

    }
}
