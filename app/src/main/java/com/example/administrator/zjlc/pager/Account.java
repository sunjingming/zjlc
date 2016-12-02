package com.example.administrator.zjlc.pager;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.text.LoginFilter;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.zjlc.MainActivity;
import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.base.BasePager;
import com.example.administrator.zjlc.login.Login;


/**
 * Created by Administrator on 2016/6/23.
 */
public class Account extends BasePager implements View.OnClickListener{

    private View view;
    private TextView login;

    public Account(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {super.initData();

        System.out.println("理财数据被初始化了...");
        //设置标题
        view = View.inflate(mActivity, R.layout.licaipager, null);
        initView();

        login.setOnClickListener(this);

        fl_basepager_content.addView(view);
    }

    /**
     *
     */
    private void initView() {
        login = (TextView) view.findViewById(R.id.user_login);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_login:
                Intent intent = new Intent(mActivity, Login.class);
                mActivity.startActivity(intent);
        }
    }
}
