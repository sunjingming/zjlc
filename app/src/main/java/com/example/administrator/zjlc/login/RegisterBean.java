package com.example.administrator.zjlc.login;

/**
 * Created by Administrator on 2016/12/3.
 */

public class RegisterBean {
    /**
     * event : 88
     * msg : 注册成功
     * data : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW0iOiIxODM1MzYxNjQ2NyIsInBobyI6IjE4MzUzNjE2NDY3IiwiaWRzIjo0MDI5LCJ2YWxwIjoxNH0._-h-5LFpTpO4OsFOvbzOEUTvzjV03Wxe4-ZnvHoZWv4
     */

    private int event;
    private String msg;
    private String data;

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
