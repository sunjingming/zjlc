package com.example.administrator.zjlc.domain;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */

public class JsonRootBean {

    /**
     * event : 88
     * msg : success
     * data : ["http://zhuojin.petope.com/Static/Uploads/App/20151224144518174.jpg","http://zhuojin.petope.com/Static/Uploads/App/20151224144522937.jpg"]
     */

    private int event;
    private String msg;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
