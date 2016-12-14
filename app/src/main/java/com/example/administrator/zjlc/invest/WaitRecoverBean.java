package com.example.administrator.zjlc.invest;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class WaitRecoverBean {
    /**
     * event : 88
     * msg : success
     * data : [{"id":"1715","borrow_id":"626","investor_uid":"4005","borrow_name":"傻狗","investor_capital":"20000.00","investor_interest":"351.45","receive_capital":"0.00","receive_interest":"0.00","add_time":"2016-12-08 16:51","is_auto":"0","rate":"6.00"},{"id":"1695","borrow_id":"606","investor_uid":"4005","borrow_name":"借款测试0924","investor_capital":"10000.00","investor_interest":"661.85","receive_capital":"2389.20","receive_interest":"276.27","add_time":"2016-10-24 09:25","is_auto":"0","rate":"12.00"},{"id":"1600","borrow_id":"576","investor_uid":"4005","borrow_name":"借款测试1500","investor_capital":"9000.00","investor_interest":"540.00","receive_capital":"0.00","receive_interest":"0.00","add_time":"2016-09-21 15:07","is_auto":"0","rate":"12.00"},{"id":"1599","borrow_id":"576","investor_uid":"4005","borrow_name":"借款测试1500","investor_capital":"1000.00","investor_interest":"60.00","receive_capital":"0.00","receive_interest":"0.00","add_time":"2016-09-21 15:07","is_auto":"0","rate":"12.00"},{"id":"1589","borrow_id":"554","investor_uid":"4005","borrow_name":"借款测试0944","investor_capital":"34000.00","investor_interest":"2720.00","receive_capital":"0.00","receive_interest":"0.00","add_time":"2016-09-13 09:51","is_auto":"0","rate":"12.00"},{"id":"1588","borrow_id":"554","investor_uid":"4005","borrow_name":"借款测试0944","investor_capital":"2000.00","investor_interest":"160.00","receive_capital":"0.00","receive_interest":"0.00","add_time":"2016-09-13 09:46","is_auto":"0","rate":"12.00"}]
     * currentPage : 1
     * pageSize : 6
     * maxCount : 15
     * maxPage : 3
     */

    private int event;
    private String msg;
    private int currentPage;
    private int pageSize;
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
         * id : 1715
         * borrow_id : 626
         * investor_uid : 4005
         * borrow_name : 傻狗
         * investor_capital : 20000.00
         * investor_interest : 351.45
         * receive_capital : 0.00
         * receive_interest : 0.00
         * add_time : 2016-12-08 16:51
         * is_auto : 0
         * rate : 6.00
         */

        private String id;
        private String borrow_id;
        private String investor_uid;
        private String borrow_name;
        private String investor_capital;
        private String investor_interest;
        private String receive_capital;
        private String receive_interest;
        private String add_time;
        private String is_auto;
        private String rate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBorrow_id() {
            return borrow_id;
        }

        public void setBorrow_id(String borrow_id) {
            this.borrow_id = borrow_id;
        }

        public String getInvestor_uid() {
            return investor_uid;
        }

        public void setInvestor_uid(String investor_uid) {
            this.investor_uid = investor_uid;
        }

        public String getBorrow_name() {
            return borrow_name;
        }

        public void setBorrow_name(String borrow_name) {
            this.borrow_name = borrow_name;
        }

        public String getInvestor_capital() {
            return investor_capital;
        }

        public void setInvestor_capital(String investor_capital) {
            this.investor_capital = investor_capital;
        }

        public String getInvestor_interest() {
            return investor_interest;
        }

        public void setInvestor_interest(String investor_interest) {
            this.investor_interest = investor_interest;
        }

        public String getReceive_capital() {
            return receive_capital;
        }

        public void setReceive_capital(String receive_capital) {
            this.receive_capital = receive_capital;
        }

        public String getReceive_interest() {
            return receive_interest;
        }

        public void setReceive_interest(String receive_interest) {
            this.receive_interest = receive_interest;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getIs_auto() {
            return is_auto;
        }

        public void setIs_auto(String is_auto) {
            this.is_auto = is_auto;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }
    }
}
