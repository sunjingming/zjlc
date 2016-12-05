package com.example.administrator.zjlc.bank;

import java.util.List;

/**
 * Created by Administrator on 2016/11/19.
 */
public class CityBean {

    /**
     * event : 88
     * msg : success
     * data : [{"id":"138","name":"石家庄"},{"id":"139","name":"保定"},{"id":"140","name":"沧州"},{"id":"141","name":"承德"},{"id":"142","name":"邯郸"},{"id":"143","name":"衡水"},{"id":"144","name":"廊坊"},{"id":"145","name":"秦皇岛"},{"id":"146","name":"唐山"},{"id":"147","name":"邢台"},{"id":"148","name":"张家口"}]
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
         * id : 138
         * name : 石家庄
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
