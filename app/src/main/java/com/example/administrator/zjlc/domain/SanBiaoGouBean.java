package com.example.administrator.zjlc.domain;

import java.util.List;

/**
 * Created by Administrator on 2016/12/10.
 */

public class SanBiaoGouBean {

    /**
     * event : 88
     * msg : success
     * data : [{"investor_capital":100,"add_time":"2016-09-21 15:07","is_auto":"手动","user_name":"182***606","id":577},{"investor_capital":1000,"add_time":"2016-09-21 15:08","is_auto":"手动","user_name":"182***606","id":577},{"investor_capital":1200,"add_time":"2016-09-21 15:11","is_auto":"手动","user_name":"130***380","id":577},{"investor_capital":1000,"add_time":"2016-09-21 15:13","is_auto":"手动","user_name":"130***380","id":577},{"investor_capital":1000,"add_time":"2016-09-21 15:14","is_auto":"手动","user_name":"130***380","id":577},{"investor_capital":1000,"add_time":"2016-09-21 15:14","is_auto":"手动","user_name":"130***380","id":577}]
     * currentPage : 1
     * pageSize : 10
     * maxCount : 6
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
         * investor_capital : 100
         * add_time : 2016-09-21 15:07
         * is_auto : 手动
         * user_name : 182***606
         * id : 577
         */

        private int investor_capital;
        private String add_time;
        private String is_auto;
        private String user_name;
        private int id;

        public int getInvestor_capital() {
            return investor_capital;
        }

        public void setInvestor_capital(int investor_capital) {
            this.investor_capital = investor_capital;
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

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
