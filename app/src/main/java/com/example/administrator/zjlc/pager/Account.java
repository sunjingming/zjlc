package com.example.administrator.zjlc.pager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.approve.Approve;
import com.example.administrator.zjlc.base.BasePager;
import com.example.administrator.zjlc.login.Login;
import com.example.administrator.zjlc.login.UserBean;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


/**
 * Created by Administrator on 2016/6/23.
 */
public class Account extends BasePager implements View.OnClickListener {

    private View view;
    private TextView login;
    private TextView asset;
    private TextView interest;
    private TextView balance;
    private TextView freeze;
    private TextView approve;
    private TextView bank;
    private String token;
    private ImageView head;

    public Account(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        super.initData();

        System.out.println("理财数据被初始化了...");

        SharedPreferences fence = mActivity.getSharedPreferences("usetoken", mActivity.MODE_PRIVATE);
        token = fence.getString("token", null);
       // Toast.makeText(mActivity, token, Toast.LENGTH_SHORT).show();
        //设置标题
        view = View.inflate(mActivity, R.layout.licaipager, null);
        initView();
        getData();

        if (token.length() > 2) {
            login.setText("退出");
        }else {
            login.setText("未登录");
        }


        login.setOnClickListener(this);
        approve.setOnClickListener(this);
        bank.setOnClickListener(this);


        fl_basepager_content.addView(view);
    }

    private void getData() {
        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCUser_page);
        params.addBodyParameter("token", token);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data账户首页", data);
                Gson gson = new Gson();
                UserBean userBean = gson.fromJson(data, UserBean.class);
                UserBean.DataBean datalist = userBean.getData();
                asset.setText("¥" + String.valueOf(datalist.getAll_money()));
                balance.setText("¥" + String.valueOf(datalist.getBalance_money()));
                interest.setText("¥" + String.valueOf(datalist.getCollect_interest()));
                freeze.setText("¥" + String.valueOf(datalist.getFreeze_money()));
                Glide.with(mActivity).load(datalist.getHeader_img()).into(head);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     *
     */
    private void initView() {
        login = (TextView) view.findViewById(R.id.user_login);
        asset = (TextView) view.findViewById(R.id.user_asset);
        balance = (TextView) view.findViewById(R.id.user_balance);
        interest = (TextView) view.findViewById(R.id.user_interest);
        freeze = (TextView) view.findViewById(R.id.user_freeze);
        head = (ImageView) view.findViewById(R.id.user_head);
        approve = (TextView) view.findViewById(R.id.user_approve);
        bank = (TextView) view.findViewById(R.id.user_bank);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_login:
                Intent intent = new Intent(mActivity, Login.class);
                mActivity.startActivity(intent);
                break;
            case R.id.user_approve:
                Intent intentApprove = new Intent(mActivity,Approve.class);
                mActivity.startActivity(intentApprove);
                break;
            case R.id.user_bank:
                bankJudge();
        }
    }

    private void bankJudge() {
        RequestParams paramms  = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCApprove_juadge);
        paramms.addBodyParameter("token",token);
        x.http().post(paramms, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data是否实名",data);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
