package com.jbwang.cwgl.entity;

public class User {
    private String userId;

    private String userName;

    private char userSex;

    private String userPassword;

    private String loadPower;

    private String userTouxiang;

    private char userStatus;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public char getUserSex() {
        return userSex;
    }

    public void setUserSex(char userSex) {
        this.userSex = userSex;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getLoadPower() {
        return loadPower;
    }

    public void setLoadPower(String loadPower) {
        this.loadPower = loadPower;
    }

    public String getUserTouxiang() {
        return userTouxiang;
    }

    public void setUserTouxiang(String userTouxiang) {
        this.userTouxiang = userTouxiang;
    }

    public char getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(char userStatus) {
        this.userStatus = userStatus;
    }
}
