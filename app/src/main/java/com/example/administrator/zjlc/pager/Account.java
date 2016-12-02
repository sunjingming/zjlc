package com.example.administrator.zjlc.pager;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.base.BasePager;


/**
 * Created by Administrator on 2016/6/23.
 */
public class Account extends BasePager {

    private View view;

    public Account(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {super.initData();

        System.out.println("理财数据被初始化了...");
        //设置标题
        view = View.inflate(mActivity, R.layout.licaipager, null);
        initView();
        fl_basepager_content.addView(view);
    }

    /**
     *
     */
    private void initView() {

    }

}
