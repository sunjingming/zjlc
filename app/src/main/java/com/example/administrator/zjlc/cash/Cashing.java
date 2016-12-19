package com.example.administrator.zjlc.cash;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.bank.UserBean;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.example.administrator.zjlc.utils.MD5Utils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Timer;
import java.util.TimerTask;

public class Cashing extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_title;
    private Toolbar toolbar;
    private TextView cashing_money;
    private TextView cashing_fee;
    private EditText cashing_password;
    private Button cashing_submit;
    private String token;
    private String money;
    private String fee;
    private TextView cashing_use_money;

    /*
    * 进行提现界面
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashing);

        initView();

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("提现");

        SharedPreferences preferences = getSharedPreferences("usetoken", MODE_APPEND);
        token = preferences.getString("token", null);

        Intent intent = getIntent();
        money = intent.getStringExtra("money");
        fee = intent.getStringExtra("fee");
        cashing_money.setText(money + "  元");
        cashing_fee.setText(fee + "  元");

        //获取用户可用余额
        RequestParams paramss = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCUser_page);
        paramss.addBodyParameter("token", token);
        x.http().post(paramss, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data账户首页", data);
                Gson gson = new Gson();
                com.example.administrator.zjlc.login.UserBean userBean = gson.fromJson(data, com.example.administrator.zjlc.login.UserBean.class);
                com.example.administrator.zjlc.login.UserBean.DataBean datalist = userBean.getData();
                double f = datalist.getBalance_money();
                String f1 = String.format("%.2f", f);
                cashing_use_money.setText("¥" + f1);
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
        cashing_money = (TextView) findViewById(R.id.cashing_money);
        cashing_fee = (TextView) findViewById(R.id.cashing_fee);
        cashing_password = (EditText) findViewById(R.id.cashing_password);
        cashing_submit = (Button) findViewById(R.id.cashing_submit);

        cashing_submit.setOnClickListener(this);
        cashing_use_money = (TextView) findViewById(R.id.cashing_use_money);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cashing_submit:
                if (cashing_password.getText().toString().length() < 1) {
                    Toast.makeText(this, "请输入交易密码", Toast.LENGTH_SHORT).show();
                } else {
                    RequestParams params = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCCashing_apply);
                    params.addBodyParameter("token", token);
                    params.addBodyParameter("money", money);
                    params.addBodyParameter("pin_pass", MD5Utils.Md5(cashing_password.getText().toString()));
                    params.addBodyParameter("fee", fee);
                    x.http().post(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            String data = result;
                            Log.i("data提现申请", data);
                            Gson gson = new Gson();
                            CashingBean bean = gson.fromJson(data, CashingBean.class);
                            if (bean.getEvent() == 88) {
                                AlertDialog dialog = new AlertDialog.Builder(Cashing.this).setTitle("消息提示").setMessage(bean.getMsg()).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                }).show();
                                getHomeAcvtivity();
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
                break;
        }
    }

    private void getHomeAcvtivity() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();
            }
        };
        timer.schedule(task, 1500);
    }


}
