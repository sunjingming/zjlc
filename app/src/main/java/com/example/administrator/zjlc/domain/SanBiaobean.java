package com.example.administrator.zjlc.domain;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */

public class SanBiaobean {


    /**
     * event : 88
     * msg : success
     * data : [{"id":4,"borrow_name":"PC端发布担保标","borrow_duration":"2个月","borrow_money":9800,"has_borrow":200,"borrow_interest_rate":11.24,"borrow_status":2,"repayment_type":"按月分期还款","borrow_type":"担保标","reward_num":"0.00","credits":20,"level":"HR","progress":2.04,"has_pass":0},{"id":3,"borrow_name":"借款测试1150","borrow_duration":"10个月","borrow_money":120000,"has_borrow":120000,"borrow_interest_rate":12,"borrow_status":6,"repayment_type":"每月还息到期还本","borrow_type":"担保标","reward_num":"0.00","credits":10,"level":"HR","progress":100,"has_pass":0},{"id":2,"borrow_name":"借款测试1100","borrow_duration":"10个月","borrow_money":12000,"has_borrow":12000,"borrow_interest_rate":12,"borrow_status":6,"repayment_type":"每月还息到期还本","borrow_type":"担保标","reward_num":"0.00","credits":10,"level":"HR","progress":100,"has_pass":0},{"id":5,"borrow_name":"抵押标测试","borrow_duration":"1个月","borrow_money":50000,"has_borrow":50000,"borrow_interest_rate":12,"borrow_status":7,"repayment_type":"按月分期还款","borrow_type":"抵押标","reward_num":"2.00","credits":10,"level":"HR","progress":100,"has_pass":0},{"id":1,"borrow_name":"借款测试","borrow_duration":"12个月","borrow_money":10000,"has_borrow":10000,"borrow_interest_rate":12,"borrow_status":7,"repayment_type":"按月分期还款","borrow_type":"担保标","reward_num":"0.00","credits":10,"level":"HR","progress":100,"has_pass":0}]
     * currentPage : 1
     * pageSize : 6
     * maxCount : 5
     * maxPage : 1
     */

    private int event;
    private String msg;
    private int currentPage;
    private int pageSize;
    private int maxCount;
    private int maxPage;
    private List<DataBean> data;

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

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 4
         * borrow_name : PC端发布担保标
         * borrow_duration : 2个月
         * borrow_money : 9800
         * has_borrow : 200
         * borrow_interest_rate : 11.24
         * borrow_status : 2
         * repayment_type : 按月分期还款
         * borrow_type : 担保标
         * reward_num : 0.00
         * credits : 20
         * level : HR
         * progress : 2.04
         * has_pass : 0
         */

        private int id;
        private String borrow_name;
        private String borrow_duration;
        private int borrow_money;
        private int has_borrow;
        private double borrow_interest_rate;
        private int borrow_status;
        private String repayment_type;
        private String borrow_type;
        private String reward_num;
        private int credits;
        private String level;
        private double progress;
        private int has_pass;

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

        public int getHas_borrow() {
            return has_borrow;
        }

        public void setHas_borrow(int has_borrow) {
            this.has_borrow = has_borrow;
        }

        public double getBorrow_interest_rate() {
            return borrow_interest_rate;
        }

        public void setBorrow_interest_rate(double borrow_interest_rate) {
            this.borrow_interest_rate = borrow_interest_rate;
        }

        public int getBorrow_status() {
            return borrow_status;
        }

        public void setBorrow_status(int borrow_status) {
            this.borrow_status = borrow_status;
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

        public String getReward_num() {
            return reward_num;
        }

        public void setReward_num(String reward_num) {
            this.reward_num = reward_num;
        }

        public int getCredits() {
            return credits;
        }

        public void setCredits(int credits) {
            this.credits = credits;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public double getProgress() {
            return progress;
        }

        public void setProgress(double progress) {
            this.progress = progress;
        }

        public int getHas_pass() {
            return has_pass;
        }

        public void setHas_pass(int has_pass) {
            this.has_pass = has_pass;
        }
    }
}
