package com.example.administrator.zjlc.bank;

import java.util.List;

/**
 * Created by Administrator on 2016/11/19.
 */
public class BankBean {

    /**
     * event : 88
     * msg : success
     * data : [{"bank_name":"招商银行","bank":"招商银行"},{"bank_name":"中国银行","bank":"中国银行"},{"bank_name":"工商银行","bank":"中国工商银行"},{"bank_name":"建设银行","bank":"中国建设银行"},{"bank_name":"农业银行","bank":"中国农业银行"},{"bank_name":"邮政储蓄银行","bank":"中国邮政储蓄银行"},{"bank_name":"交通银行","bank":"交通银行"},{"bank_name":"上海浦发银行","bank":"上海浦东发展银行"},{"bank_name":"深圳发展银行","bank":"深圳发展银行"},{"bank_name":"民生银行","bank":"中国民生银行"},{"bank_name":"兴业银行","bank":"兴业银行"},{"bank_name":"平安银行","bank":"平安银行"},{"bank_name":"北京银行","bank":"北京银行"},{"bank_name":"天津银行","bank":"天津银行"},{"bank_name":"上海银行","bank":"上海银行"},{"bank_name":"华夏银行","bank":"华夏银行"},{"bank_name":"光大银行","bank":"光大银行"},{"bank_name":"广发银行","bank":"广发银行"},{"bank_name":"中信银行","bank":"中信银行"},{"bank_name":"上海农商银行","bank":"上海农商银行"},{"bank_name":"农村信用社","bank":"农村信用社"}]
     */

    private int event;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bank_name : 招商银行
         * bank : 招商银行
         */

        private String bank_name;
        private String bank;

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }
    }
}
