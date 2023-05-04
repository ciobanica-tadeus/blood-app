package com.example.bloodapp.controllers.dtos;

public class ErrorResponse extends BaseResponse{
    private String errorMessage;

    public ErrorResponse(){
        this.errorMessage = "";
    }

    public ErrorResponse(String message ){
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
