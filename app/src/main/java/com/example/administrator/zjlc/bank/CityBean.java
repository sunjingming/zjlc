package com.example.administrator.zjlc.bank;

import java.util.List;

/**
 * Created by Administrator on 2016/11/19.
 */
public class CityBean {
    /**
     * event : 88
     * msg : success
     * obj : [{"id":"53","reid":"4","name":"福州","sort_order":"2","is_open":"0","domain":""},{"id":"54","reid":"4","name":"龙岩","sort_order":"2","is_open":"0","domain":""},{"id":"55","reid":"4","name":"南平","sort_order":"2","is_open":"0","domain":""},{"id":"56","reid":"4","name":"宁德","sort_order":"2","is_open":"0","domain":""},{"id":"57","reid":"4","name":"莆田","sort_order":"2","is_open":"0","domain":""},{"id":"58","reid":"4","name":"泉州","sort_order":"2","is_open":"0","domain":""},{"id":"59","reid":"4","name":"三明","sort_order":"2","is_open":"0","domain":""},{"id":"60","reid":"4","name":"厦门","sort_order":"2","is_open":"0","domain":""},{"id":"61","reid":"4","name":"漳州","sort_order":"2","is_open":"0","domain":""}]
     */

    private int event;
    private String msg;
    private List<ObjBean> obj;

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

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * id : 53
         * reid : 4
         * name : 福州
         * sort_order : 2
         * is_open : 0
         * domain :
         */

        private String id;
        private String reid;
        private String name;
        private String sort_order;
        private String is_open;
        private String domain;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getReid() {
            return reid;
        }

        public void setReid(String reid) {
            this.reid = reid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSort_order() {
            return sort_order;
        }

        public void setSort_order(String sort_order) {
            this.sort_order = sort_order;
        }

        public String getIs_open() {
            return is_open;
        }

        public void setIs_open(String is_open) {
            this.is_open = is_open;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }
    }
}
