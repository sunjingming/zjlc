package com.example.administrator.zjlc.userMessage;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */

public class UserMailBean {
    /**
     * event : 88
     * msg : success
     * data : [{"msg":"您刚刚申请了提现操作,如不是自己操作,请尽快联系客服","send_time":"2016-12-07 11:03","status":0,"title":"您刚刚申请了提现操作","id":4444},{"msg":"您刚刚申请了提现操作,如不是自己操作,请尽快联系客服","send_time":"2016-12-07 10:58","status":0,"title":"您刚刚申请了提现操作","id":4443},{"msg":"您刚刚申请了提现操作,如不是自己操作,请尽快联系客服","send_time":"2016-12-07 10:58","status":0,"title":"您刚刚申请了提现操作","id":4442},{"msg":"您刚刚申请了提现操作,如不是自己操作,请尽快联系客服","send_time":"2016-12-07 10:57","status":0,"title":"您刚刚申请了提现操作","id":4441},{"msg":"您刚刚申请了提现操作,如不是自己操作,请尽快联系客服","send_time":"2016-12-07 10:54","status":0,"title":"您刚刚申请了提现操作","id":4440},{"msg":"您刚刚申请了提现操作,如不是自己操作,请尽快联系客服","send_time":"2016-12-07 10:48","status":0,"title":"您刚刚申请了提现操作","id":4439}]
     * currentPage : 1
     * pageSize : 6
     * maxCount : 370
     * maxPage : 62
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
         * msg : 您刚刚申请了提现操作,如不是自己操作,请尽快联系客服
         * send_time : 2016-12-07 11:03
         * status : 0
         * title : 您刚刚申请了提现操作
         * id : 4444
         */

        private String msg;
        private String send_time;
        private int status;
        private String title;
        private int id;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getSend_time() {
            return send_time;
        }

        public void setSend_time(String send_time) {
            this.send_time = send_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
