package com.jbwang.cwgl.entity;

public class Manager {
    private String managerId;
    private  String managerName;
    private  String managerPassword;
    private char loadPower;
    private String managerTouxiang;
    private char userStatus;

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public char getLoadPower() {
        return loadPower;
    }

    public void setLoadPower(char loadPower) {
        this.loadPower = loadPower;
    }

    public String getManagerTouxiang() {
        return managerTouxiang;
    }

    public void setManagerTouxiang(String managerTouxiang) {
        this.managerTouxiang = managerTouxiang;
    }

    public char getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(char userStatus) {
        this.userStatus = userStatus;
    }
}
