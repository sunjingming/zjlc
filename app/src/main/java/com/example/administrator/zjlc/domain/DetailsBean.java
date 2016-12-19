package com.example.administrator.zjlc.domain;

/**
 * Created by Administrator on 2016/12/3.
 */

public class DetailsBean {

    /**
     * event : 88
     * msg : success
     * data : {"id":643,"borrow_name":"借款测试1416","borrow_duration":"12个月","borrow_money":120000,"borrow_interest_rate":12,"has_borrow":1000,"borrow_times":10,"repayment_type":"每月还息到期还本","borrow_type":"担保标","borrow_status":2,"add_time":"2016-12-19 14:16","collect_day":10,"borrow_info":"<p>12<\/p>","reward_num":0,"borrow_min":50,"borrow_max":0,"is_new":0,"updata":null,"money_collect":0,"borrow_bid":1,"borrow_status_str":"正在招标中","has_pass":0,"progress":0.83}
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
         * id : 643
         * borrow_name : 借款测试1416
         * borrow_duration : 12个月
         * borrow_money : 120000
         * borrow_interest_rate : 12
         * has_borrow : 1000
         * borrow_times : 10
         * repayment_type : 每月还息到期还本
         * borrow_type : 担保标
         * borrow_status : 2
         * add_time : 2016-12-19 14:16
         * collect_day : 10
         * borrow_info : <p>12</p>
         * reward_num : 0
         * borrow_min : 50
         * borrow_max : 0
         * is_new : 0
         * updata : null
         * money_collect : 0
         * borrow_bid : 1
         * borrow_status_str : 正在招标中
         * has_pass : 0
         * progress : 0.83
         */

        private int id;
        private String borrow_name;
        private String borrow_duration;
        private int borrow_money;
        private int borrow_interest_rate;
        private int has_borrow;
        private int borrow_times;
        private String repayment_type;
        private String borrow_type;
        private int borrow_status;
        private String add_time;
        private int collect_day;
        private String borrow_info;
        private int reward_num;
        private int borrow_min;
        private int borrow_max;
        private int is_new;
        private Object updata;
        private int money_collect;
        private int borrow_bid;
        private String borrow_status_str;
        private int has_pass;
        private double progress;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBorrow_name() {
            return borrow_name;
        }

        public void setBorrow_name(String borrow_name) {
            this.borrow_name = borrow_name;
        }

        public String getBorrow_duration() {
            return borrow_duration;
        }

        public void setBorrow_duration(String borrow_duration) {
            this.borrow_duration = borrow_duration;
        }

        public int getBorrow_money() {
            return borrow_money;
        }

        public void setBorrow_money(int borrow_money) {
            this.borrow_money = borrow_money;
        }

        public int getBorrow_interest_rate() {
            return borrow_interest_rate;
        }

        public void setBorrow_interest_rate(int borrow_interest_rate) {
            this.borrow_interest_rate = borrow_interest_rate;
        }

        public int getHas_borrow() {
            return has_borrow;
        }

        public void setHas_borrow(int has_borrow) {
            this.has_borrow = has_borrow;
        }

        public int getBorrow_times() {
            return borrow_times;
        }

        public void setBorrow_times(int borrow_times) {
            this.borrow_times = borrow_times;
        }

        public String getRepayment_type() {
            return repayment_type;
        }

        public void setRepayment_type(String repayment_type) {
            this.repayment_type = repayment_type;
        }

        public String getBorrow_type() {
            return borrow_type;
        }

        public void setBorrow_type(String borrow_type) {
            this.borrow_type = borrow_type;
        }

        public int getBorrow_status() {
            return borrow_status;
        }

        public void setBorrow_status(int borrow_status) {
            this.borrow_status = borrow_status;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public int getCollect_day() {
            return collect_day;
        }

        public void setCollect_day(int collect_day) {
            this.collect_day = collect_day;
        }

        public String getBorrow_info() {
            return borrow_info;
        }

        public void setBorrow_info(String borrow_info) {
            this.borrow_info = borrow_info;
        }

        public int getReward_num() {
            return reward_num;
        }

        public void setReward_num(int reward_num) {
            this.reward_num = reward_num;
        }

        public int getBorrow_min() {
            return borrow_min;
        }

        public void setBorrow_min(int borrow_min) {
            this.borrow_min = borrow_min;
        }

        public int getBorrow_max() {
            return borrow_max;
        }

        public void setBorrow_max(int borrow_max) {
            this.borrow_max = borrow_max;
        }

        public int getIs_new() {
            return is_new;
        }

        public void setIs_new(int is_new) {
            this.is_new = is_new;
        }

        public Object getUpdata() {
            return updata;
        }

        public void setUpdata(Object updata) {
            this.updata = updata;
        }

        public int getMoney_collect() {
            return money_collect;
        }

        public void setMoney_collect(int money_collect) {
            this.money_collect = money_collect;
        }

        public int getBorrow_bid() {
            return borrow_bid;
        }

        public void setBorrow_bid(int borrow_bid) {
            this.borrow_bid = borrow_bid;
        }

        public String getBorrow_status_str() {
            return borrow_status_str;
        }

        public void setBorrow_status_str(String borrow_status_str) {
            this.borrow_status_str = borrow_status_str;
        }

        public int getHas_pass() {
            return has_pass;
        }

        public void setHas_pass(int has_pass) {
            this.has_pass = has_pass;
        }

        public double getProgress() {
            return progress;
        }

        public void setProgress(double progress) {
            this.progress = progress;
        }
    }
}
