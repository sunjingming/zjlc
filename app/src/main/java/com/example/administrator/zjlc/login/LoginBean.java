package com.example.administrator.zjlc.login;

/**
 * Created by Administrator on 2016/12/2.
 */

public class LoginBean {
    /**
     * event : 88
     * msg : 登录成功
     * data : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW0iOiJTNCIsInBobyI6IjEzNTYxMDg2NjgzIiwiaWRzIjoiNDAwNSIsInZhbHAiOjE0fQ.yb5sfuweN77cYn_7k1BvIDyL4m3ul2vATvOWNejbJrY
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
