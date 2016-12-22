package com.example.administrator.zjlc.cash;

/**
 * Created by Administrator on 2016/12/7.
 */

public class CashingFeeBean {
    /**
     * event : 88
     * msg : success
     * data : {"fee":0}
     */

    private int event;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * fee : 0
         */

        private float fee;

        public float getFee() {
            return fee;
        }

        public void setFee(float fee) {
            this.fee = fee;
        }
    }
}
