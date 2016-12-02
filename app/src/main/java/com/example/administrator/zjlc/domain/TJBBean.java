package com.example.administrator.zjlc.domain;

/**
 * Created by Administrator on 2016/12/2.
 */

public class TJBBean {

    /**
     * event : 88
     * msg : success
     * data : {"id":"616","borrow_name":"娴嬭瘯涓夌櫨","borrow_type":5,"borrow_money":"1000.00","borrow_duration":"3涓湀","borrow_interest_rate":"10.00","type":2,"progress":"0.00"}
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
         * id : 616
         * borrow_name : 娴嬭瘯涓夌櫨
         * borrow_type : 5
         * borrow_money : 1000.00
         * borrow_duration : 3涓湀
         * borrow_interest_rate : 10.00
         * type : 2
         * progress : 0.00
         */

        private String id;
        private String borrow_name;
        private int borrow_type;
        private String borrow_money;
        private String borrow_duration;
        private String borrow_interest_rate;
        private int type;
        private String progress;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBorrow_name() {
            return borrow_name;
        }

        public void setBorrow_name(String borrow_name) {
            this.borrow_name = borrow_name;
        }

        public int getBorrow_type() {
            return borrow_type;
        }

        public void setBorrow_type(int borrow_type) {
            this.borrow_type = borrow_type;
        }

        public String getBorrow_money() {
            return borrow_money;
        }

        public void setBorrow_money(String borrow_money) {
            this.borrow_money = borrow_money;
        }

        public String getBorrow_duration() {
            return borrow_duration;
        }

        public void setBorrow_duration(String borrow_duration) {
            this.borrow_duration = borrow_duration;
        }

        public String getBorrow_interest_rate() {
            return borrow_interest_rate;
        }

        public void setBorrow_interest_rate(String borrow_interest_rate) {
            this.borrow_interest_rate = borrow_interest_rate;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }
    }
}
