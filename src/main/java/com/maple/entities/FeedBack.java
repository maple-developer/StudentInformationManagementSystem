package com.maple.entities;

public class FeedBack {

    private boolean flag;
    private String data;
    private String info;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Data { flag: " + this.isFlag()
                + ", data: " + this.getData()
                + ", data: " + this.getInfo()
                + " }";
    }
}
