package com.zq.wanandroid.http.responsebean;

public class IndexBeanCache {

    private int status;

    private String msg;

    private  IndexBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public IndexBean getData() {
        return data;
    }

    public void setData(IndexBean data) {
        this.data = data;
    }
}
