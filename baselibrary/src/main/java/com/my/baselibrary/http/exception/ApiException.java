package com.my.baselibrary.http.exception;

public class ApiException extends Exception {
    private int code;
    private String disPlayMessage;

    public ApiException(int code, String disPlayMessage) {
        this.code = code;
        this.disPlayMessage = disPlayMessage;
    }

    public ApiException(int code,String message,String disPlayMessage){
        super(message);
        this.code = code;
        this.disPlayMessage = disPlayMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDisPlayMessage() {
        return disPlayMessage;
    }

    public void setDisPlayMessage(String disPlayMessage) {
        this.disPlayMessage = disPlayMessage;
    }
}
