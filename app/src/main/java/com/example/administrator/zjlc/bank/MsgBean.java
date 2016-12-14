package com.example.administrator.zjlc.bank;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MsgBean {
    /**
     * event : 88
     * msg : success
     * data : {"header_img":"http://zhuojin.petope.com/Style/header/images/noavatar_middle.gif","user_name":"18353616467","user_phone":"183***467","real_name":"Libing","idcard":"371491199402210031","all_money":610000,"balance_money":500000,"freeze_money":100000,"collect_interest":0,"credit_lvl":"HR","invest_lvl":"一级","id_status":1,"phone_status":1,"email_status":0,"has_pin":1,"vip":0,"invite_code":"DCHE8MW25P"}
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
         * header_img : http://zhuojin.petope.com/Style/header/images/noavatar_middle.gif
         * user_name : 18353616467
         * user_phone : 183***467
         * real_name : Libing
         * idcard : 371491199402210031
         * all_money : 610000
         * balance_money : 500000
         * freeze_money : 100000
         * collect_interest : 0
         * credit_lvl : HR
         * invest_lvl : 一级
         * id_status : 1
         * phone_status : 1
         * email_status : 0
         * has_pin : 1
         * vip : 0
         * invite_code : DCHE8MW25P
         */

        private String header_img;
        private String user_name;
        private String user_phone;
        private String real_name;
        private String idcard;
        private int all_money;
        private int balance_money;
        private int freeze_money;
        private int collect_interest;
        private String credit_lvl;
        private String invest_lvl;
        private int id_status;
        private int phone_status;
        private int email_status;
        private int has_pin;
        private int vip;
        private String invite_code;

        public String getHeader_img() {
            return header_img;
        }

        public void setHeader_img(String header_img) {
            this.header_img = header_img;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public int getAll_money() {
            return all_money;
        }

        public void setAll_money(int all_money) {
            this.all_money = all_money;
        }

        public int getBalance_money() {
            return balance_money;
        }

        public void setBalance_money(int balance_money) {
            this.balance_money = balance_money;
        }

        public int getFreeze_money() {
            return freeze_money;
        }

        public void setFreeze_money(int freeze_money) {
            this.freeze_money = freeze_money;
        }

        public int getCollect_interest() {
            return collect_interest;
        }

        public void setCollect_interest(int collect_interest) {
            this.collect_interest = collect_interest;
        }

        public String getCredit_lvl() {
            return credit_lvl;
        }

        public void setCredit_lvl(String credit_lvl) {
            this.credit_lvl = credit_lvl;
        }

        public String getInvest_lvl() {
            return invest_lvl;
        }

        public void setInvest_lvl(String invest_lvl) {
            this.invest_lvl = invest_lvl;
        }

        public int getId_status() {
            return id_status;
        }

        public void setId_status(int id_status) {
            this.id_status = id_status;
        }

        public int getPhone_status() {
            return phone_status;
        }

        public void setPhone_status(int phone_status) {
            this.phone_status = phone_status;
        }

        public int getEmail_status() {
            return email_status;
        }

        public void setEmail_status(int email_status) {
            this.email_status = email_status;
        }

        public int getHas_pin() {
            return has_pin;
        }

        public void setHas_pin(int has_pin) {
            this.has_pin = has_pin;
        }

        public int getVip() {
            return vip;
        }

        public void setVip(int vip) {
            this.vip = vip;
        }

        public String getInvite_code() {
            return invite_code;
        }

        public void setInvite_code(String invite_code) {
            this.invite_code = invite_code;
        }
    }
}
