package com.example.administrator.zjlc.invest;

import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */

public class RedPacketBean {
    /**
     * event : 88
     * msg : success
     * data : [{"id":"4871","money":"100.00","type":"活动发放","duration":"2,1|10","status":"1","deadline":"2038-01-01 00:00","name":"活动特权金---杨","multiple_money":"800.00","str":"用于期限(月)为1-10的标，且投标800.00元及以上可用"},{"id":"4311","money":"200.00","type":"活动发放","duration":"2,1|10","status":"1","deadline":"2038-01-01 00:00","name":"活动特权金","multiple_money":"1000.00","str":"用于期限(月)为1-10的标，且投标1000.00元及以上可用"},{"id":"2871","money":"50.00","type":"标的第一投资人","duration":"2,12|0","status":"1","deadline":"2038-01-01 00:00","name":"抢投特权金","multiple_money":"3000.00","str":"用于期限(月)为12以上的标，且投标3000.00元及以上可用"},{"id":"2814","money":"50.00","type":"标的第一投资人","duration":"2,12|0","status":"1","deadline":"2038-01-01 00:00","name":"抢投特权金","multiple_money":"3000.00","str":"用于期限(月)为12以上的标，且投标3000.00元及以上可用"},{"id":"2809","money":"50.00","type":"标的第一投资人","duration":"2,12|0","status":"1","deadline":"2038-01-01 00:00","name":"抢投特权金","multiple_money":"3000.00","str":"用于期限(月)为12以上的标，且投标3000.00元及以上可用"}]
     * currentPage : 1
     * pageSize : 5
     * maxCount : 21
     * maxPage : 5
     */

    private int event;
    private String msg;
    private String currentPage;
    private String pageSize;
    private String maxCount;
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

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(String maxCount) {
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
         * id : 4871
         * money : 100.00
         * type : 活动发放
         * duration : 2,1|10
         * status : 1
         * deadline : 2038-01-01 00:00
         * name : 活动特权金---杨
         * multiple_money : 800.00
         * str : 用于期限(月)为1-10的标，且投标800.00元及以上可用
         */

        private String id;
        private String money;
        private String type;
        private String duration;
        private String status;
        private String deadline;
        private String name;
        private String multiple_money;
        private String str;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMultiple_money() {
            return multiple_money;
        }

        public void setMultiple_money(String multiple_money) {
            this.multiple_money = multiple_money;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }
}
