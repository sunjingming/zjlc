package com.example.administrator.zjlc.cash;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */

public class CashRecordBean {
    /**
     * event : 88
     * msg : success
     * data : [{"id":119,"add_time":"2016-12-07 11:03","withdraw_status":"待审核","withdraw_money":10000},{"id":118,"add_time":"2016-12-07 10:58","withdraw_status":"待审核","withdraw_money":10000},{"id":117,"add_time":"2016-12-07 10:58","withdraw_status":"待审核","withdraw_money":8000},{"id":116,"add_time":"2016-12-07 10:57","withdraw_status":"待审核","withdraw_money":1000},{"id":115,"add_time":"2016-12-07 10:54","withdraw_status":"待审核","withdraw_money":10000},{"id":114,"add_time":"2016-12-07 10:48","withdraw_status":"待审核","withdraw_money":1000}]
     * currentPage : 1
     * pageSize : 6
     * maxCount : 8
     * maxPage : 2
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
         * id : 119
         * add_time : 2016-12-07 11:03
         * withdraw_status : 待审核
         * withdraw_money : 10000
         */

        private int id;
        private String add_time;
        private String withdraw_status;
        private double withdraw_money;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getWithdraw_status() {
            return withdraw_status;
        }

        public void setWithdraw_status(String withdraw_status) {
            this.withdraw_status = withdraw_status;
        }

        public double getWithdraw_money() {
            return withdraw_money;
        }

        public void setWithdraw_money(double withdraw_money) {
            this.withdraw_money = withdraw_money;
        }
    }
}
