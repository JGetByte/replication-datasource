package com.job.piyawut.replicationdatasource.exception;

public class DataNotFoundException extends Exception {

    private final String errorCode;
    private final String message;

    public DataNotFoundException(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

