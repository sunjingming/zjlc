package com.example.administrator.zjlc.bank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.login.*;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class CardMsg extends AppCompatActivity {

    private TextView tv_title;
    private Toolbar toolbar;
    private TextView bank_short;
    private TextView bank_msg_name;
    private TextView bnak_msg_number;
    private Button change_bank;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_msg);
        initView();

        SharedPreferences fence = getSharedPreferences("usetoken",MODE_PRIVATE);
        token = fence.getString("token",null);

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("我的银行卡");

        loadData();
        loadMore();
        change_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardMsg.this,AddCard.class);
                startActivity(intent);
            }
        });
    }

    private void loadMore() {

        RequestParams param = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCUser_page);
        param.addBodyParameter("token", token);
        x.http().post(param, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data账户首页", data);
                Gson gson = new Gson();
                MsgBean msgBean = gson.fromJson(data,MsgBean.class);
                bank_short.setText("户主姓名："+msgBean.getData().getReal_name());
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

    private void loadData() {
        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCBank_msg);
        params.addBodyParameter("token",token);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data银行卡信息",data);
                Gson gson = new Gson();
                CardMsgBean msgBean = gson.fromJson(data,CardMsgBean.class);
                bank_msg_name.setText("银行名称:  "+ msgBean.getData().getBank_name());
                String cadr = msgBean.getData().getBank_num();
                String S  = cadr.substring(0,4)+"******"+cadr.substring(15,cadr.length());
                bnak_msg_number.setText("卡号:  "+S);
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

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bank_short = (TextView) findViewById(R.id.bank_short);
        bank_msg_name = (TextView) findViewById(R.id.bank_msg_name);
        bnak_msg_number = (TextView) findViewById(R.id.bnak_msg_number);
        change_bank = (Button) findViewById(R.id.change_bank);

    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.post(runnable);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.sendEmptyMessage(1);
        }
    };
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    loadData();
                    loadMore();
            }
        }
    };
}
