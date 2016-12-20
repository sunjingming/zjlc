package com.example.administrator.zjlc.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.domain.ZQXQBean;
import com.example.administrator.zjlc.login.RegisterCodeBean;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class ZQZRActivity extends AppCompatActivity {
    private int id;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;
    private TextView tv10;
    private TextView tv11;
    private TextView tv12;
    private TextView tv13;
    private TextView tv14;
    private TextView tv15;
    private TextView tv_pasnt;

    private Button lijigou;

    private ZQXQBean noticeBean;
    private String token;
    private String pin;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            //更新页面
            tv1.setText(noticeBean.getData().getDebt_name()+"");
            tv2.setText(noticeBean.getData().getMoney()+"");
            tv3.setText(noticeBean.getData().getPeriod()+"");
            tv4.setText(noticeBean.getData().getTotal_period()+"");
            switch (noticeBean.getData().getStatus()) {
                case 1:
                    tv5.setText("还款中");
                    break;
                case 2:
                    tv5.setText("可转让");
                    break;
                case 3:
                    tv5.setText("流标");
                    break;
                case 4:
                    tv5.setText("完成");
                    break;

            }
            tv6.setText(noticeBean.getData().getRate()+"%");
            tv7.setText(noticeBean.getData().getTransfer_price()+".00");
            tv8.setText(noticeBean.getData().getValid()+"");
            tv9.setText(noticeBean.getData().getBorrow_user()+"");
            tv10.setText(noticeBean.getData().getInvest_user()+"");
            tv11.setText(noticeBean.getData().getCredits()+"");
            tv12.setText(noticeBean.getData().getLevel()+"");
            if(noticeBean.getData().getEmail_status() == 1){
                tv13.setText("已认证");
            }else{
                tv13.setText("未认证");
            }
            if(noticeBean.getData().getId_status() == 1){
                tv14.setText("已认证");
            }else{
                tv14.setText("未认证");
            }
            if(noticeBean.getData().getPhone_status() == 1){
                tv15.setText("已认证");
            }else{
                tv15.setText("未认证");
            }
//            tv13.setText(noticeBean.getData().getEmail_status()+"");
//            tv14.setText(noticeBean.getData().getId_status()+"");
//            tv15.setText(noticeBean.getData().getPhone_status()+"");
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zqzr);

        SharedPreferences fence = getSharedPreferences("usetoken", MODE_PRIVATE);
        token = fence.getString("token", "");
        initView();
    }

    private void initView() {

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);
        tv7 = (TextView) findViewById(R.id.tv7);
        tv8 = (TextView) findViewById(R.id.tv8);
        tv9 = (TextView) findViewById(R.id.tv9);
        tv10 = (TextView) findViewById(R.id.tv10);
        tv11 = (TextView) findViewById(R.id.tv11);
        tv12 = (TextView) findViewById(R.id.tv12);
        tv13 = (TextView) findViewById(R.id.tv13);
        tv14 = (TextView) findViewById(R.id.tv14);
        tv15 = (TextView) findViewById(R.id.tv15);
        tv_pasnt = (TextView) findViewById(R.id.tv_pasnt);
        tv_pasnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lijigou = (Button) findViewById(R.id.lijigou);

        lijigou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(ZQZRActivity.this);
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                new android.app.AlertDialog.Builder(ZQZRActivity.this).setMessage("请输入交易密码").
                        setView(editText).setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = editText.getText().toString();
                        pin = text;
                        setDataset();
                    }
                }).setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                        .show();

            }
        });
        inData();
    }

    private void setDataset() {
        RequestParams paramsNotice = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCDebt_invest_money);

        paramsNotice.addBodyParameter("invest_id", String.valueOf(noticeBean.getData().getInvest_id()));
        paramsNotice.addBodyParameter("pin", pin);
        paramsNotice.addBodyParameter("token", token);
        x.http().post(paramsNotice, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.e("标", data);
                Gson gson = new Gson();
                RegisterCodeBean codeBean = gson.fromJson(data, RegisterCodeBean.class);
                codeBean = gson.fromJson(data, RegisterCodeBean.class);

                Toast.makeText(ZQZRActivity.this,codeBean.getMsg(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("标", "onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("标","onCancelled");

            }

            @Override
            public void onFinished() {
                Log.i("标", "onFinished");
            }
        });
    }

    private void inData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);

        RequestParams paramsNotice = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCDebt_detail);

        paramsNotice.addBodyParameter("debt_id", String.valueOf(id));
        x.http().post(paramsNotice, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.e("标", data);
//                Gson gson = new Gson();
                noticeBean = JSON.parseObject(data, ZQXQBean.class);
                handler.sendEmptyMessage(1);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("标", "onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("标","onCancelled");

            }

            @Override
            public void onFinished() {
                Log.i("标", "onFinished");
            }
        });

    }

}
