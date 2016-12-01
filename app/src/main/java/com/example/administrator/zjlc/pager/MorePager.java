package com.example.administrator.zjlc.pager;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.base.BasePager;


/**
 * Created by Administrator on 2016/6/23.
 */
public class MorePager extends BasePager {

    private View view;
    private ViewPager viewpager;

    public MorePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        super.initData();
        System.out.println("更多数据被初始化了...");
        //设置标题
        view = View.inflate(mActivity, R.layout.morepager, null);
        initView();
        fl_basepager_content.addView(view);
    }

    /**
     *er
     * 3.2,至少要实现4个方法
     */
    private void initView() {


    }

}
