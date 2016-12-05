package com.example.administrator.zjlc.bank;

/**
 * Created by Administrator on 2016/12/5.
 */

public class ApproveJuadgeBean {
    /**
     * event : 0
     * msg : 待审核
     * data : {"real_name":"李冰","idNo":"371481199402210031"}
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
         * real_name : 李冰
         * idNo : 371481199402210031
         */

        private String real_name;
        private String idNo;

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }
    }
}
