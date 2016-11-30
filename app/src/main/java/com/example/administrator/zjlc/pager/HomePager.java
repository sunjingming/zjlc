package com.example.administrator.zjlc.pager;

import android.app.Activity;
import android.view.View;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.base.BasePager;


/**
 * Created by Administrator on 2016/6/23.
 */
public class HomePager extends BasePager {

    private View view;

    public HomePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        super.initData();
        System.out.println("首页数据被初始化了...");
        //设置标题
        view = View.inflate(mActivity, R.layout.homepager, null);
        initView();
        fl_basepager_content.addView(view);
    }

    /**
     * 1.在布局文件定义ViewPager
     * 2.在代码实例化ViewPager
     * 3.设置适配器
     * 3.0，准备数据
     * 3.1，适配器要继承PagerAdapter
     * 3.2,至少要实现4个方法
     */
    private void initView() {

    }


}
