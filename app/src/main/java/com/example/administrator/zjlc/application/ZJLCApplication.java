package com.example.administrator.zjlc.application;

import android.app.Activity;
import android.app.Application;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by Administrator on 2016/11/30.
 */

public class ZJLCApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);//是否输出Debug日志
        ShareSDK.initSDK(this);
    }
}
