package com.jbwang.cwgl.entity;

import java.util.Date;

public class Item {
    private  String itemId;
    private String itemMingcheng;
    private String itemMoney;
    private String itemRenson;
    private Date itemDate;
    private String userId;
    private String itemStatus;
    private String name;

    public Date getItemDate() {
        return itemDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemRenson() {
        return itemRenson;
    }

    public void setItemRenson(String itemRenson) {
        this.itemRenson = itemRenson;
    }

    public void setItemDate(Date itemDate) {
        this.itemDate = itemDate;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemMingcheng() {
        return itemMingcheng;
    }

    public void setItemMingcheng(String itemMingcheng) {
        this.itemMingcheng = itemMingcheng;
    }

    public String getItemMoney() {
        return itemMoney;
    }

    public void setItemMoney(String itemMoney) {
        this.itemMoney = itemMoney;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }
}
