package com.example.administrator.zjlc.domain;

/**
 * Created by Administrator on 2016/12/3.
 */

public class DetailsBean {


    /**
     * event : 88
     * msg : success
     * data : {"id":0,"borrow_duration":"涓湀","borrow_money":0,"borrow_interest_rate":0,"has_borrow":0,"borrow_times":0,"repayment_type":null,"borrow_type":null,"borrow_status_str":null,"borrow_status":0,"add_time":"1970-01-01 08:00","collect_day":0,"reward_num":0,"borrow_min":0,"borrow_max":0,"has_pass":0,"is_new":0,"money_collect":0,"updata":false,"progress":0,"borrow_info":""}
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
         * id : 0
         * borrow_duration : 涓湀
         * borrow_money : 0
         * borrow_interest_rate : 0
         * has_borrow : 0
         * borrow_times : 0
         * repayment_type : null
         * borrow_type : null
         * borrow_status_str : null
         * borrow_status : 0
         * add_time : 1970-01-01 08:00
         * collect_day : 0
         * reward_num : 0
         * borrow_min : 0
         * borrow_max : 0
         * has_pass : 0
         * is_new : 0
         * money_collect : 0
         * updata : false
         * progress : 0
         * borrow_info :
         */

        private int id;
        private String borrow_duration;
        private int borrow_money;
        private int borrow_interest_rate;
        private int has_borrow;
        private int borrow_times;
        private Object repayment_type;
        private Object borrow_type;
        private Object borrow_status_str;
        private int borrow_status;
        private String add_time;
        private int collect_day;
        private int reward_num;
        private int borrow_min;
        private int borrow_max;
        private int has_pass;
        private int is_new;
        private int money_collect;
        private boolean updata;
        private int progress;
        private String borrow_info;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public Object getRepayment_type() {
            return repayment_type;
        }

        public void setRepayment_type(Object repayment_type) {
            this.repayment_type = repayment_type;
        }

        public Object getBorrow_type() {
            return borrow_type;
        }

        public void setBorrow_type(Object borrow_type) {
            this.borrow_type = borrow_type;
        }

        public Object getBorrow_status_str() {
            return borrow_status_str;
        }

        public void setBorrow_status_str(Object borrow_status_str) {
            this.borrow_status_str = borrow_status_str;
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

        public int getHas_pass() {
            return has_pass;
        }

        public void setHas_pass(int has_pass) {
            this.has_pass = has_pass;
        }

        public int getIs_new() {
            return is_new;
        }

        public void setIs_new(int is_new) {
            this.is_new = is_new;
        }

        public int getMoney_collect() {
            return money_collect;
        }

        public void setMoney_collect(int money_collect) {
            this.money_collect = money_collect;
        }

        public boolean isUpdata() {
            return updata;
        }

        public void setUpdata(boolean updata) {
            this.updata = updata;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }

        public String getBorrow_info() {
            return borrow_info;
        }

        public void setBorrow_info(String borrow_info) {
            this.borrow_info = borrow_info;
        }
    }
}
