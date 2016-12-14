package com.example.administrator.zjlc.userMessage;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MoreBean {
    /**
     * event : 88
     * msg : success
     * data : {"erweima":"http://zhuojin.petope.com/Static/Uploads/App/20151224144812634.png","weixin":"12312312312","weibo":"123123123123","more_img":"http://zhuojin.petope.com/Static/Uploads/App/20151224144924540.jpg","kefu":"123123","kefu_time":"9:00-18:00","text_content":"<p>sadfadsfjaksdfajksdfhajskdhflashkdflukawehlfadjk<\/p>"}
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
         * erweima : http://zhuojin.petope.com/Static/Uploads/App/20151224144812634.png
         * weixin : 12312312312
         * weibo : 123123123123
         * more_img : http://zhuojin.petope.com/Static/Uploads/App/20151224144924540.jpg
         * kefu : 123123
         * kefu_time : 9:00-18:00
         * text_content : <p>sadfadsfjaksdfajksdfhajskdhflashkdflukawehlfadjk</p>
         */

        private String erweima;
        private String weixin;
        private String weibo;
        private String more_img;
        private String kefu;
        private String kefu_time;
        private String text_content;

        public String getErweima() {
            return erweima;
        }

        public void setErweima(String erweima) {
            this.erweima = erweima;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getWeibo() {
            return weibo;
        }

        public void setWeibo(String weibo) {
            this.weibo = weibo;
        }

        public String getMore_img() {
            return more_img;
        }

        public void setMore_img(String more_img) {
            this.more_img = more_img;
        }

        public String getKefu() {
            return kefu;
        }

        public void setKefu(String kefu) {
            this.kefu = kefu;
        }

        public String getKefu_time() {
            return kefu_time;
        }

        public void setKefu_time(String kefu_time) {
            this.kefu_time = kefu_time;
        }

        public String getText_content() {
            return text_content;
        }

        public void setText_content(String text_content) {
            this.text_content = text_content;
        }
    }
}
