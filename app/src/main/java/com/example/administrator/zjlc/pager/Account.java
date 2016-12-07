package com.example.administrator.zjlc.pager;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.approve.Approve;
import com.example.administrator.zjlc.bank.AddCard;
import com.example.administrator.zjlc.bank.ApproveJuadgeBean;
import com.example.administrator.zjlc.bank.BankJuadgeBean;
import com.example.administrator.zjlc.bank.CardMsg;
import com.example.administrator.zjlc.base.BasePager;
import com.example.administrator.zjlc.cash.Cash;
import com.example.administrator.zjlc.login.Login;
import com.example.administrator.zjlc.login.UserBean;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.example.administrator.zjlc.userExercise.UserExercise;
import com.example.administrator.zjlc.userMessage.UserMessage;
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
    private TextView recharge;
    private TextView freeze;
    private TextView approve;
    private TextView invite;
    private TextView exercise;
    private TextView cash;
    private TextView bank;
    private String token;
    private ImageView head;
    private int event;
    private int eventBank;

    public Account(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        super.initData();

        System.out.println("理财数据被初始化了...");

        SharedPreferences fence = mActivity.getSharedPreferences("usetoken", mActivity.MODE_PRIVATE);
        token = fence.getString("token", "");


        //设置标题
        view = View.inflate(mActivity, R.layout.licaipager, null);
        initView();
        getData();

        if (token.equals("")){
            login.setText("登陆");
        }else {
            login.setText("退出");
        }

        login.setOnClickListener(this);
        approve.setOnClickListener(this);
        bank.setOnClickListener(this);
        head.setOnClickListener(this);
        cash.setOnClickListener(this);
        recharge.setOnClickListener(this);
        exercise.setOnClickListener(this);



        fl_basepager_content.addView(view);

        //判断是否进行实名认证
        RequestParams paramms  = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCApprove_juadge);
        paramms.addBodyParameter("token",token);
        x.http().post(paramms, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data是否实名",data);
                Gson gson = new Gson();
                ApproveJuadgeBean juadgeBean = gson.fromJson(data,ApproveJuadgeBean.class);
                event = juadgeBean.getEvent();

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
        //判断是否绑定银行卡
        RequestParams params  = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCBank_juadge);
        params.addBodyParameter("token",token);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data是否绑卡",data);
                Gson gson = new Gson();
                BankJuadgeBean juadgeBean  = gson.fromJson(data,BankJuadgeBean.class);
                eventBank = juadgeBean.getEvent();
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
                invite.setText(datalist.getInvite_code());
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
        invite = (TextView) view.findViewById(R.id.user_invite);
        cash = (TextView) view.findViewById(R.id.cash);
        recharge = (TextView) view.findViewById(R.id.recharge);
        exercise = (TextView) view.findViewById(R.id.user_activity);

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
                if (event!=88){
                    Toast.makeText(mActivity,"尚未通过实名认证，不能进行银行卡能相关工作" , Toast.LENGTH_SHORT).show();
                }else if (eventBank!=88){
                    AlertDialog dialog = new AlertDialog.Builder(mActivity).setTitle("消息提示").setMessage("您尚未绑定银行卡，是否前去绑卡").setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intentAddCard = new Intent(mActivity,AddCard.class);
                            mActivity.startActivity(intentAddCard);
                        }
                    }).setNegativeButton("否",null).show();

                }else {
                    Intent inten  = new Intent(mActivity,CardMsg.class);
                    mActivity.startActivity(inten);
                }
                break;
            case R.id.user_head:
                Intent intentUser = new Intent(mActivity,UserMessage.class);
                mActivity.startActivity(intentUser);
                break;
            case R.id.cash:
                Intent intentCash = new Intent(mActivity,Cash.class);
                mActivity.startActivity(intentCash);
                break;
            case R.id.recharge:
                Toast.makeText(mActivity, "暂未开放充值功能", Toast.LENGTH_SHORT).show();
                break;
            //进入我的活动页面
            case R.id.user_activity:
                Intent intentExerise = new Intent(mActivity,UserExercise.class);
                mActivity.startActivity(intentExerise);
        }
    }

}
