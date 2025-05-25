package com.example.notification.response;


import com.example.notification.model.CreateJobs;

public class BaseResponseDto<T> {
    private boolean status;
    private String statusDesc;
    private T data;
    private String errorCode;

    public BaseResponseDto() {}

    // Constructor with 4 parameters
    public BaseResponseDto(boolean status, String statusDesc, T data, String errorCode) {
        this.status = status;
        this.statusDesc = statusDesc;
        this.data = data;
        this.errorCode = errorCode;
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

    public T getData(CreateJobs entity) {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}

