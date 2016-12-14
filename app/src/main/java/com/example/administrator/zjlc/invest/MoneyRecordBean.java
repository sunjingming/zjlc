package com.example.administrator.zjlc.invest;

import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */

public class MoneyRecordBean {
    /**
     * event : 88
     * msg : success
     * data : [{"id":"11135","type":"管理员操作","affect_money":"2100000.00","account_money":"98995500000.00","back_money":"-98995000000.00","collect_money":"10000.00","freeze_money":"100000.00","info":"efd","add_time":"2016-12-12 09:01"},{"id":"11134","type":"管理员操作","affect_money":"8000000.00","account_money":"98995500000.00","back_money":"-98995000000.00","collect_money":"10000.00","freeze_money":"-2000000.00","info":"ewdwq","add_time":"2016-12-12 09:01"},{"id":"11133","type":"管理员操作","affect_money":"30000000.00","account_money":"98995500000.00","back_money":"-98995000000.00","collect_money":"10000.00","freeze_money":"-10000000.00","info":"dcwqed","add_time":"2016-12-12 09:01"},{"id":"11132","type":"管理员操作","affect_money":"-50000000.00","account_money":"98995500000.00","back_money":"-98995000000.00","collect_money":"10000.00","freeze_money":"-40000000.00","info":"wdqwd","add_time":"2016-12-12 09:01"},{"id":"11131","type":"管理员操作","affect_money":"500000.00","account_money":"98995500000.00","back_money":"-98995000000.00","collect_money":"10000.00","freeze_money":"10000000.00","info":"eewf","add_time":"2016-12-12 09:00"}]
     * currentPage : 1
     * pageSize : 5
     * maxCount : 13
     * maxPage : 3
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
         * id : 11135
         * type : 管理员操作
         * affect_money : 2100000.00
         * account_money : 98995500000.00
         * back_money : -98995000000.00
         * collect_money : 10000.00
         * freeze_money : 100000.00
         * info : efd
         * add_time : 2016-12-12 09:01
         */

        private String id;
        private String type;
        private String affect_money;
        private String account_money;
        private String back_money;
        private String collect_money;
        private String freeze_money;
        private String info;
        private String add_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAffect_money() {
            return affect_money;
        }

        public void setAffect_money(String affect_money) {
            this.affect_money = affect_money;
        }

        public String getAccount_money() {
            return account_money;
        }

        public void setAccount_money(String account_money) {
            this.account_money = account_money;
        }

        public String getBack_money() {
            return back_money;
        }

        public void setBack_money(String back_money) {
            this.back_money = back_money;
        }

        public String getCollect_money() {
            return collect_money;
        }

        public void setCollect_money(String collect_money) {
            this.collect_money = collect_money;
        }

        public String getFreeze_money() {
            return freeze_money;
        }

        public void setFreeze_money(String freeze_money) {
            this.freeze_money = freeze_money;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }
}
