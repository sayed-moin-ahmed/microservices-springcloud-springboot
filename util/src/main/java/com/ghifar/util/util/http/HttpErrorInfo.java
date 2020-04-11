package com.ghifar.util.util.http;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class HttpErrorInfo {
    private final ZonedDateTime timeStamp;
    private final String path;
    private final HttpStatus httpStatus;
    private final String message;

    public HttpErrorInfo() {
        this.timeStamp = null;
        this.path = null;
        this.httpStatus = null;
        this.message = null;
    }

    public HttpErrorInfo( String path, HttpStatus httpStatus, String message) {
        this.timeStamp = ZonedDateTime.now();
        this.path = path;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getPath() {
        return path;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
