package com.example.administrator.zjlc.domain;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */

public class ZQZLbean {

    /**
     * event : 88
     * msg : success
     * data : [{"transfer_price":1012,"status":2,"money":1012.51,"total_period":2,"period":2,"valid":"2016-12-13 10:35","debt_id":15,"deadline":"2016-02-19 23:59","borrow_name":"鍐嶆娴嬭瘯","borrow_type":"鎷呬繚鏍�","borrow_interest_rate":10,"borrow_status":6,"borrow_duration":"2涓湀","credits":20,"creditsb":10,"level":"HR","levelb":"HR"},{"transfer_price":100.14,"status":4,"money":100.14,"total_period":1,"period":1,"valid":"2015-11-28 15:05","debt_id":5,"deadline":"2015-11-26 23:59","borrow_name":"鍒濇娴嬭瘯","borrow_type":"鎷呬繚鏍�","borrow_interest_rate":10,"borrow_status":7,"borrow_duration":"5澶�","credits":20,"creditsb":10,"level":"HR","levelb":"HR"},{"transfer_price":1902.6,"status":4,"money":1902.6,"total_period":1,"period":1,"valid":"2015-11-28 15:05","debt_id":6,"deadline":"2015-11-26 23:59","borrow_name":"鍒濇娴嬭瘯","borrow_type":"鎷呬繚鏍�","borrow_interest_rate":10,"borrow_status":7,"borrow_duration":"5澶�","credits":20,"creditsb":10,"level":"HR","levelb":"HR"},{"transfer_price":20000,"status":4,"money":31950,"total_period":6,"period":6,"valid":"2016-01-29 09:58","debt_id":12,"deadline":"2016-03-15 23:59","borrow_name":"JZDB20150629114","borrow_type":"鍑\u20ac鍊兼爣","borrow_interest_rate":13,"borrow_status":7,"borrow_duration":"6涓湀","credits":30,"creditsb":30,"level":"E","levelb":"E"},{"transfer_price":505,"status":4,"money":508.36,"total_period":3,"period":3,"valid":"2016-12-05 11:04","debt_id":19,"deadline":"2017-02-28 23:59","borrow_name":"娴嬭瘯鏁版嵁777","borrow_type":"鎷呬繚鏍�","borrow_interest_rate":10,"borrow_status":7,"borrow_duration":"3涓湀","credits":20,"creditsb":20,"level":"HR","levelb":"HR"}]
     * currentPage : 1
     * pageSize : 5
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
         * transfer_price : 1012
         * status : 2
         * money : 1012.51
         * total_period : 2
         * period : 2
         * valid : 2016-12-13 10:35
         * debt_id : 15
         * deadline : 2016-02-19 23:59
         * borrow_name : 鍐嶆娴嬭瘯
         * borrow_type : 鎷呬繚鏍�
         * borrow_interest_rate : 10
         * borrow_status : 6
         * borrow_duration : 2涓湀
         * credits : 20
         * creditsb : 10
         * level : HR
         * levelb : HR
         */

        private int transfer_price;
        private int status;
        private double money;
        private int total_period;
        private int period;
        private String valid;
        private int debt_id;
        private String deadline;
        private String borrow_name;
        private String borrow_type;
        private int borrow_interest_rate;
        private int borrow_status;
        private String borrow_duration;
        private int credits;
        private int creditsb;
        private String level;
        private String levelb;

        public int getTransfer_price() {
            return transfer_price;
        }

        public void setTransfer_price(int transfer_price) {
            this.transfer_price = transfer_price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getTotal_period() {
            return total_period;
        }

        public void setTotal_period(int total_period) {
            this.total_period = total_period;
        }

        public int getPeriod() {
            return period;
        }

        public void setPeriod(int period) {
            this.period = period;
        }

        public String getValid() {
            return valid;
        }

        public void setValid(String valid) {
            this.valid = valid;
        }

        public int getDebt_id() {
            return debt_id;
        }

        public void setDebt_id(int debt_id) {
            this.debt_id = debt_id;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public String getBorrow_name() {
            return borrow_name;
        }

        public void setBorrow_name(String borrow_name) {
            this.borrow_name = borrow_name;
        }

        public String getBorrow_type() {
            return borrow_type;
        }

        public void setBorrow_type(String borrow_type) {
            this.borrow_type = borrow_type;
        }

        public int getBorrow_interest_rate() {
            return borrow_interest_rate;
        }

        public void setBorrow_interest_rate(int borrow_interest_rate) {
            this.borrow_interest_rate = borrow_interest_rate;
        }

        public int getBorrow_status() {
            return borrow_status;
        }

        public void setBorrow_status(int borrow_status) {
            this.borrow_status = borrow_status;
        }

        public String getBorrow_duration() {
            return borrow_duration;
        }

        public void setBorrow_duration(String borrow_duration) {
            this.borrow_duration = borrow_duration;
        }

        public int getCredits() {
            return credits;
        }

        public void setCredits(int credits) {
            this.credits = credits;
        }

        public int getCreditsb() {
            return creditsb;
        }

        public void setCreditsb(int creditsb) {
            this.creditsb = creditsb;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLevelb() {
            return levelb;
        }

        public void setLevelb(String levelb) {
            this.levelb = levelb;
        }
    }
}
