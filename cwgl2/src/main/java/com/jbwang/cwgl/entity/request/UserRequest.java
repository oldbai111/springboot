package com.jbwang.cwgl.entity.request;

public class UserRequest {

    private int pageNo;//当前页数

    private int pageSize;//每页大小


    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
