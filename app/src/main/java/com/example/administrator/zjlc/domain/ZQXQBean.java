package com.example.administrator.zjlc.domain;

/**
 * Created by Administrator on 2016/12/10.
 */

public class ZQXQBean {

    /**
     * event : 88
     * msg : success
     * data : {"rate":10,"transfer_price":1012,"money":1012.51,"valid":"2016-12-13 10:35","period":2,"total_period":2,"credits":20,"deadline":"2016-02-19","id_status":1,"invest_user":"182*****6606","borrow_user":"","phone_status":1,"status":2,"email_status":0,"debt_name":"再次测试","borrow_info":"<p>0<\/p>","invest_id":366,"level":"HR","borrow_use":null}
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
         * rate : 10
         * transfer_price : 1012
         * money : 1012.51
         * valid : 2016-12-13 10:35
         * period : 2
         * total_period : 2
         * credits : 20
         * deadline : 2016-02-19
         * id_status : 1
         * invest_user : 182*****6606
         * borrow_user :
         * phone_status : 1
         * status : 2
         * email_status : 0
         * debt_name : 再次测试
         * borrow_info : <p>0</p>
         * invest_id : 366
         * level : HR
         * borrow_use : null
         */

        private int rate;
        private int transfer_price;
        private double money;
        private String valid;
        private int period;
        private int total_period;
        private int credits;
        private String deadline;
        private int id_status;
        private String invest_user;
        private String borrow_user;
        private int phone_status;
        private int status;
        private int email_status;
        private String debt_name;
        private String borrow_info;
        private int invest_id;
        private String level;
        private Object borrow_use;

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public int getTransfer_price() {
            return transfer_price;
        }

        public void setTransfer_price(int transfer_price) {
            this.transfer_price = transfer_price;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getValid() {
            return valid;
        }

        public void setValid(String valid) {
            this.valid = valid;
        }

        public int getPeriod() {
            return period;
        }

        public void setPeriod(int period) {
            this.period = period;
        }

        public int getTotal_period() {
            return total_period;
        }

        public void setTotal_period(int total_period) {
            this.total_period = total_period;
        }

        public int getCredits() {
            return credits;
        }

        public void setCredits(int credits) {
            this.credits = credits;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public int getId_status() {
            return id_status;
        }

        public void setId_status(int id_status) {
            this.id_status = id_status;
        }

        public String getInvest_user() {
            return invest_user;
        }

        public void setInvest_user(String invest_user) {
            this.invest_user = invest_user;
        }

        public String getBorrow_user() {
            return borrow_user;
        }

        public void setBorrow_user(String borrow_user) {
            this.borrow_user = borrow_user;
        }

        public int getPhone_status() {
            return phone_status;
        }

        public void setPhone_status(int phone_status) {
            this.phone_status = phone_status;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getEmail_status() {
            return email_status;
        }

        public void setEmail_status(int email_status) {
            this.email_status = email_status;
        }

        public String getDebt_name() {
            return debt_name;
        }

        public void setDebt_name(String debt_name) {
            this.debt_name = debt_name;
        }

        public String getBorrow_info() {
            return borrow_info;
        }

        public void setBorrow_info(String borrow_info) {
            this.borrow_info = borrow_info;
        }

        public int getInvest_id() {
            return invest_id;
        }

        public void setInvest_id(int invest_id) {
            this.invest_id = invest_id;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public Object getBorrow_use() {
            return borrow_use;
        }

        public void setBorrow_use(Object borrow_use) {
            this.borrow_use = borrow_use;
        }
    }
}
