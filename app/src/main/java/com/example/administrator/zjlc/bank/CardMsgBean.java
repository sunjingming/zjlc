package com.example.administrator.zjlc.bank;

/**
 * Created by Administrator on 2016/12/6.
 */

public class CardMsgBean {
    /**
     * event : 88
     * msg : success
     * data : {"uid":"4032","bank_num":"6222601050003588631","bank_province":7,"bank_city":101,"bank_address":"超级鸡车","bank_name":"上海浦东发展银行","add_time":"2016-12-06 16:15","bank_province_str":"广西","bank_city_str":"崇左","bank_name_str":"上海浦发银行"}
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
         * uid : 4032
         * bank_num : 6222601050003588631
         * bank_province : 7
         * bank_city : 101
         * bank_address : 超级鸡车
         * bank_name : 上海浦东发展银行
         * add_time : 2016-12-06 16:15
         * bank_province_str : 广西
         * bank_city_str : 崇左
         * bank_name_str : 上海浦发银行
         */

        private String uid;
        private String bank_num;
        private int bank_province;
        private int bank_city;
        private String bank_address;
        private String bank_name;
        private String add_time;
        private String bank_province_str;
        private String bank_city_str;
        private String bank_name_str;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getBank_num() {
            return bank_num;
        }

        public void setBank_num(String bank_num) {
            this.bank_num = bank_num;
        }

        public int getBank_province() {
            return bank_province;
        }

        public void setBank_province(int bank_province) {
            this.bank_province = bank_province;
        }

        public int getBank_city() {
            return bank_city;
        }

        public void setBank_city(int bank_city) {
            this.bank_city = bank_city;
        }

        public String getBank_address() {
            return bank_address;
        }

        public void setBank_address(String bank_address) {
            this.bank_address = bank_address;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getBank_province_str() {
            return bank_province_str;
        }

        public void setBank_province_str(String bank_province_str) {
            this.bank_province_str = bank_province_str;
        }

        public String getBank_city_str() {
            return bank_city_str;
        }

        public void setBank_city_str(String bank_city_str) {
            this.bank_city_str = bank_city_str;
        }

        public String getBank_name_str() {
            return bank_name_str;
        }

        public void setBank_name_str(String bank_name_str) {
            this.bank_name_str = bank_name_str;
        }
    }
}
