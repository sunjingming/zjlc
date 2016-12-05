package com.example.administrator.zjlc.bank;

import java.util.List;

/**
 * Created by Administrator on 2016/11/19.
 */
public class ProvinceBean {
    /**
     * event : 88
     * msg : success
     * obj : [{"id":"2","reid":"1","name":"北京","sort_order":"1","is_open":"1","domain":"bj"},{"id":"3","reid":"1","name":"安徽","sort_order":"1","is_open":"0","domain":"ah"},{"id":"4","reid":"1","name":"福建","sort_order":"1","is_open":"0","domain":""},{"id":"5","reid":"1","name":"甘肃","sort_order":"1","is_open":"0","domain":""},{"id":"6","reid":"1","name":"广东","sort_order":"1","is_open":"0","domain":""},{"id":"7","reid":"1","name":"广西","sort_order":"1","is_open":"0","domain":""},{"id":"8","reid":"1","name":"贵州","sort_order":"1","is_open":"0","domain":""},{"id":"9","reid":"1","name":"海南","sort_order":"1","is_open":"0","domain":""},{"id":"10","reid":"1","name":"河北","sort_order":"1","is_open":"0","domain":""},{"id":"11","reid":"1","name":"河南","sort_order":"1","is_open":"0","domain":""},{"id":"12","reid":"1","name":"黑龙江","sort_order":"1","is_open":"0","domain":""},{"id":"13","reid":"1","name":"湖北","sort_order":"1","is_open":"0","domain":""},{"id":"14","reid":"1","name":"湖南","sort_order":"1","is_open":"0","domain":""},{"id":"15","reid":"1","name":"吉林","sort_order":"1","is_open":"0","domain":""},{"id":"16","reid":"1","name":"江苏","sort_order":"1","is_open":"0","domain":""},{"id":"17","reid":"1","name":"江西","sort_order":"1","is_open":"0","domain":""},{"id":"18","reid":"1","name":"辽宁","sort_order":"1","is_open":"0","domain":""},{"id":"19","reid":"1","name":"内蒙古","sort_order":"1","is_open":"0","domain":""},{"id":"20","reid":"1","name":"宁夏","sort_order":"1","is_open":"0","domain":""},{"id":"21","reid":"1","name":"青海","sort_order":"1","is_open":"0","domain":""},{"id":"22","reid":"1","name":"山东","sort_order":"1","is_open":"0","domain":""},{"id":"23","reid":"1","name":"山西","sort_order":"1","is_open":"0","domain":""},{"id":"24","reid":"1","name":"陕西","sort_order":"1","is_open":"0","domain":""},{"id":"25","reid":"1","name":"上海","sort_order":"1","is_open":"1","domain":"sh"},{"id":"26","reid":"1","name":"四川","sort_order":"1","is_open":"0","domain":""},{"id":"27","reid":"1","name":"天津","sort_order":"1","is_open":"0","domain":""},{"id":"28","reid":"1","name":"西藏","sort_order":"1","is_open":"0","domain":""},{"id":"29","reid":"1","name":"新疆","sort_order":"1","is_open":"0","domain":""},{"id":"30","reid":"1","name":"云南","sort_order":"1","is_open":"0","domain":""},{"id":"31","reid":"1","name":"浙江","sort_order":"1","is_open":"0","domain":""},{"id":"32","reid":"1","name":"重庆","sort_order":"1","is_open":"0","domain":""},{"id":"33","reid":"1","name":"香港","sort_order":"1","is_open":"0","domain":""},{"id":"34","reid":"1","name":"澳门","sort_order":"1","is_open":"0","domain":""},{"id":"35","reid":"1","name":"台湾","sort_order":"1","is_open":"0","domain":""}]
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
         * id : 2
         * reid : 1
         * name : 北京
         * sort_order : 1
         * is_open : 1
         * domain : bj
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
