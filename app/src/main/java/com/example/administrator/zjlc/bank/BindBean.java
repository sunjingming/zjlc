package com.example.administrator.zjlc.bank;

/**
 * Created by Administrator on 2016/12/6.
 */

public class BindBean {

    /**
     * event : 0
     * msg : 更新失败:新卡与原卡相同
     */

    private int event;
    private String msg;

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
}
