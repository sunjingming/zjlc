package com.example.administrator.zjlc.bank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.login.Register;
import com.example.administrator.zjlc.login.RegisterCodeBean;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.example.administrator.zjlc.utils.CountDownTimerUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


public class AddCard extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private TextView title, name, bank, area;
    private EditText cardNumber, cardNumberCheck, bankName;
    private Button submit,getCode;
    private String token;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        SharedPreferences preferences = getSharedPreferences("usetoken", MODE_APPEND);
        token = preferences.getString("token", null);
        initView();
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText("绑定银行卡");

        bank.setOnClickListener(this);
        area.setOnClickListener(this);


        getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RequestParams params = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCGet_code);
                params.addBodyParameter("phone", "15266061570");
                params.addBodyParameter("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW0iOiIxNTI2NjA2MTU3MCIsInBobyI6IjE1MjY2MDYxNTcwIiwiaWRzIjoiNDAyMSIsInZhbHAiOjE0fQ.GahO-8XLgHGSbcgDIV5BPBTQl8AAz55rJvMVSx2-FI4");
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        String data = result;
                        Log.i("data注册验证码", data);
                        Log.i("data发送参数", params+"");
                        Gson gson = new Gson();
                        RegisterCodeBean codeBean = gson.fromJson(data,RegisterCodeBean.class);
                        codeBean = gson.fromJson(data, RegisterCodeBean.class);
                        if (codeBean.getEvent() == 88) {
                            CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(getCode, 60500, 1000);
                            countDownTimerUtils.start();
                        } else {
                            Toast.makeText(AddCard.this, codeBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
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
        });
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) findViewById(R.id.tv_title);
        name = (TextView) findViewById(R.id.bind_householder_name);
        bank = (TextView) findViewById(R.id.bind_choose_bank);
        area = (TextView) findViewById(R.id.area_choose);
        cardNumber = (EditText) findViewById(R.id.bank_card_number);
        cardNumberCheck = (EditText) findViewById(R.id.bank_card_number_checked);
        bankName = (EditText) findViewById(R.id.bank_name);
        submit = (Button) findViewById(R.id.bind_card_submit);
        getCode = (Button) findViewById(R.id.bank_get_code);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bind_choose_bank:
                Intent intent = new Intent(AddCard.this, BankChoose.class);
                startActivity(intent);
                break;
            case R.id.area_choose:
                Intent intent1 = new Intent(AddCard.this, AreaChoose.class);
                startActivity(intent1);
        }
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

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:

                    //获取验证码

                    //请求用户信息
//                    RequestParams paramsAccount = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCUser_page);
//                    paramsAccount.addBodyParameter("token", token);
//                    x.http().post(paramsAccount, new org.xutils.common.Callback.CommonCallback<String>() {
//                        @Override
//                        public void onSuccess(String result) {
//                            String data = result;
//                            Log.i("data用户信息", data);
//                            Gson gson = new Gson();
//                            UserBean userbean = gson.fromJson(data,UserBean.class);
//                            phone = userbean.getData().getUser_name();
//                        }
//
//                        @Override
//                        public void onError(Throwable ex, boolean isOnCallback) {
//
//                        }
//
//                        @Override
//                        public void onCancelled(CancelledException cex) {
//
//                        }
//
//                        @Override
//                        public void onFinished() {
//
//                        }
//                    });

                    SharedPreferences prence = getSharedPreferences("bankName", MODE_PRIVATE);
                    String bankName = prence.getString("bankChoose", "");
                    String cityName = prence.getString("cityName", "");
                    String provinceName = prence.getString("provinceName", "");
                    bank.setText(bankName);
                    area.setText(cityName + "    " + provinceName);

            }
        }
    };

}
