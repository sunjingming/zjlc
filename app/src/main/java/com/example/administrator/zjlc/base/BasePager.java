package com.example.administrator.zjlc.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;

import com.example.administrator.zjlc.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


/**
 * 作用：HomePager，NewsCenterPager,SmartServicePager,GovaffairPager,SettingPager
   都要继承BasePager
    要在BasePager里直接实现标题栏部分
 */
public class BasePager {

     //IOC反向注入

    @ViewInject(R.id.fl_basepager_content)
    public FrameLayout fl_basepager_content;


    /**
     * 上下文
     */
    public Activity mActivity;

    /**
     * 代表本页面
     */
    public View rootView;

    public BasePager(Activity activity){
        this.mActivity= activity;
        rootView = initVeiw();
    }

    /**
     * 初始化公共部分-标题栏
     * 该方法建议用于初始化视图
     * @return
     */
    private View initVeiw() {
        View view = View.inflate(mActivity, R.layout.basepager, null);
        x.view().inject(this,view);
        return view;
    }
    /**
     * 当子页面，需要请求或者加载数据的时候，重新该方法
     */
    public void initData(){


    }
}
