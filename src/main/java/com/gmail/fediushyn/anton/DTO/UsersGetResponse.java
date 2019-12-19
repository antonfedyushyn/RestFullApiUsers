package com.gmail.fediushyn.anton.DTO;

public class UsersGetResponse {
    private int code;
    private String status;
    private UsersDTO user;

    public UsersGetResponse() {
    }

    public UsersGetResponse(int code, String status, UsersDTO user) {
        this.code = code;
        this.status = status;
        this.user = user;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UsersDTO getUser() {
        return user;
    }

    public void setUser(UsersDTO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UsersModifyResponse{" +
                "code=" + code +
                ", status='" + status + '\'' +
                '}';
    }
}
