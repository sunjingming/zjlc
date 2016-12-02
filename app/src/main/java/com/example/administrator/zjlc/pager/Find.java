package com.example.administrator.zjlc.pager;

import android.app.Activity;
import android.view.View;
import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.base.BasePager;



/**
 * Created by Administrator on 2016/6/23.
 */
public class Find extends BasePager {

    private View view;

    public Find(Activity activity) {
        super(activity);
    }
    @Override
    public void initData() {
        super.initData();
        System.out.println("我的数据被初始化了...");
        //设置标题
        view = View.inflate(mActivity, R.layout.mepager, null);
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
