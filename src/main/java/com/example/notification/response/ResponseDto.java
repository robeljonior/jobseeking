package com.example.notification.response;


public class ResponseDto {
    private boolean status;
    private String statusDesc;

    // Parameterless constructor
    public ResponseDto() {}

    // Constructor with 2 parameters
    public ResponseDto(boolean status, String statusDesc) {
        this.status = status;
        this.statusDesc = statusDesc;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
