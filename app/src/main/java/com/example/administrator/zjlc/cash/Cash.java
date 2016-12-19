package com.example.administrator.zjlc.cash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.bank.UserBean;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class Cash extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_title;
    private Toolbar toolbar;
    private TextView cash_use_money;
    private EditText cash_money;
    private Button cashing;
    private Button cash_record;
    private String token;

    private CashingFeeBean feeBean;
    private int event;
    /*
    * 提现首页
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);
        initView();
        SharedPreferences preferences = getSharedPreferences("usetoken", MODE_APPEND);
        token = preferences.getString("token", null);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("我要提现");

        cash_money.addTextChangedListener(textWatcher);

        //获取用户可用余额
        RequestParams paramss = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCUser_page);
        paramss.addBodyParameter("token", token);
        x.http().post(paramss, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data账户首页", data);
                Gson gson = new Gson();
                UserBean userBean = gson.fromJson(data, UserBean.class);
                UserBean.DataBean datalist = userBean.getData();

                double f = datalist.getBalance_money();
                String f1 = String.format("%.2f", f);
                cash_use_money.setText("¥" + f1);

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
        //获取提现手续费
        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCCashing_fee);
        params.addBodyParameter("token", token);
        params.addBodyParameter("money", cash_money.getText().toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data提现手续费", data);
                Gson gson = new Gson();
                feeBean = gson.fromJson(data, CashingFeeBean.class);
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
        cash_use_money = (TextView) findViewById(R.id.cash_use_money);
        cash_money = (EditText) findViewById(R.id.cash_money);
        cashing = (Button) findViewById(R.id.cashing);
        cash_record = (Button) findViewById(R.id.cash_record);

        cashing.setOnClickListener(this);
        cash_record.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cashing:
                if (feeBean.getEvent() == 88) {
                    Intent intent = new Intent(Cash.this, Cashing.class);
                    intent.putExtra("money", cash_money.getText().toString());
                    intent.putExtra("fee", String.valueOf(feeBean.getData().getFee()));
                    startActivity(intent);
                } else {
                    Toast.makeText(this, feeBean.getMsg(), Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.cash_record:
                Intent intent = new Intent(Cash.this, CashRecord.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (cash_money.getText().toString().length() > 0) {
                cashing.setEnabled(true);
            } else {
                cashing.setEnabled(false);
            }

        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        handler.post(runnabale);
    }

    private Runnable runnabale = new Runnable() {
        @Override
        public void run() {
            handler.sendEmptyMessage(1);
        }
    };
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    SharedPreferences preferences = getSharedPreferences("usetoken", MODE_APPEND);
                    token = preferences.getString("token", null);
                    //获取用户可用余额
                    RequestParams paramss = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCUser_page);
                    paramss.addBodyParameter("token", token);
                    x.http().post(paramss, new org.xutils.common.Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            String data = result;
                            Log.i("data账户首页", data);
                            Gson gson = new Gson();
                            com.example.administrator.zjlc.login.UserBean userBean = gson.fromJson(data, com.example.administrator.zjlc.login.UserBean.class);
                            com.example.administrator.zjlc.login.UserBean.DataBean datalist = userBean.getData();
                            double f = datalist.getBalance_money();
                            String f1 = String.format("%.2f", f);
                            cash_use_money.setText("¥" + f1);
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
    };
}
