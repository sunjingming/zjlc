package com.example.administrator.zjlc.domain;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */

public class SanBiaobean {

    /**
     * event : 88
     * msg : success
     * data : [{"id":615,"borrow_name":"鍊熸娴嬭瘯1616","borrow_duration":"12涓湀","borrow_money":160000,"has_borrow":1000,"borrow_interest_rate":12,"borrow_status":4,"repayment_type":"鎸夋湀鍒嗘湡杩樻","borrow_type":"鎶垫娂鏍�","reward_num":"3.00","credits":21,"level":"E","progress":0.63},{"id":611,"borrow_name":"鍊熸娴嬭瘯1030","borrow_duration":"12涓湀","borrow_money":120000,"has_borrow":6000,"borrow_interest_rate":12,"borrow_status":4,"repayment_type":"姣忔湀杩樻伅鍒版湡杩樻湰","borrow_type":"鎶垫娂鏍�","reward_num":"0.00","credits":21,"level":"E","progress":5},{"id":605,"borrow_name":"100","borrow_duration":"3涓湀","borrow_money":1000,"has_borrow":100,"borrow_interest_rate":10,"borrow_status":4,"repayment_type":"鎸夋湀鍒嗘湡杩樻","borrow_type":"鎶垫娂鏍�","reward_num":"0.00","credits":10,"level":"HR","progress":10},{"id":604,"borrow_name":"瀹氬悜鏍�","borrow_duration":"3涓湀","borrow_money":100000,"has_borrow":200,"borrow_interest_rate":10,"borrow_status":4,"repayment_type":"鎸夋湀鍒嗘湡杩樻","borrow_type":"鎶垫娂鏍�","reward_num":"0.00","credits":10,"level":"HR","progress":0.2},{"id":602,"borrow_name":"鍊熸娴嬭瘯1144","borrow_duration":"6涓湀","borrow_money":120000,"has_borrow":3950,"borrow_interest_rate":12,"borrow_status":4,"repayment_type":"鎸夋湀鍒嗘湡杩樻","borrow_type":"鎶垫娂鏍�","reward_num":"0.00","credits":21,"level":"E","progress":3.29},{"id":601,"borrow_name":"鍊熸娴嬭瘯0845","borrow_duration":"1涓湀","borrow_money":60000,"has_borrow":1000,"borrow_interest_rate":12,"borrow_status":4,"repayment_type":"鎸夋湀鍒嗘湡杩樻","borrow_type":"鎶垫娂鏍�","reward_num":"0.00","credits":21,"level":"E","progress":1.67}]
     * currentPage : 1
     * pageSize : 6
     * maxCount : 477
     * maxPage : 80
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
         * id : 615
         * borrow_name : 鍊熸娴嬭瘯1616
         * borrow_duration : 12涓湀
         * borrow_money : 160000
         * has_borrow : 1000
         * borrow_interest_rate : 12
         * borrow_status : 4
         * repayment_type : 鎸夋湀鍒嗘湡杩樻
         * borrow_type : 鎶垫娂鏍�
         * reward_num : 3.00
         * credits : 21
         * level : E
         * progress : 0.63
         */

        private int id;
        private String borrow_name;
        private String borrow_duration;
        private int borrow_money;
        private int has_borrow;
        private int borrow_interest_rate;
        private int borrow_status;
        private String repayment_type;
        private String borrow_type;
        private String reward_num;
        private int credits;
        private String level;
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

        public int getHas_borrow() {
            return has_borrow;
        }

        public void setHas_borrow(int has_borrow) {
            this.has_borrow = has_borrow;
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
    }
}
