package com.example.administrator.zjlc.userMessage;

/**
 * Created by Administrator on 2016/12/20.
 */

public class InvestBorrowBean {
    /**
     * event : 3
     * msg : 您的借款申请已经通过审核，不必重新申请
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
