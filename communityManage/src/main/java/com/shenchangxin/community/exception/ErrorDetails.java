package com.shenchangxin.community.exception;

public class ErrorDetails {
    private String message;
    private String details;

    public  ErrorDetails(String message,String details){
        this.details=details;
        this.message=message;
    }
    public  ErrorDetails(){

    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
