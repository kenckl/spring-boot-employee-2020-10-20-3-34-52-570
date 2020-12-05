package com.thoughtworks.springbootemployee.advice.errorMessage;

public class ErrorResponse {
    private final String status;
    private final String message;

    public ErrorResponse(String message, String status){
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
