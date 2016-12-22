package com.example.administrator.zjlc.userMessage;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.login.LoginPwd;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class UserPwdSafe extends AppCompatActivity {

    private TextView tv_title;
    private Toolbar toolbar;
    private TextView login_password_safe;
    private TextView trade_password_safe;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pwd_safe);
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
        tv_title.setText("密码安全");

        //判断是否设置了交易密码
        trade_password_safe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams paramsTrade = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCTrade_whether);
                paramsTrade.addBodyParameter("token",token);
                x.http().post(paramsTrade, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        String data = result;
                        Log.i("data交易密码",data);
                        Gson gson = new Gson();
                        TradePwdWhetherBean tradeBean = gson.fromJson(data,TradePwdWhetherBean.class);
                        if (tradeBean.getEvent()==88){
                            Intent intent = new Intent(UserPwdSafe.this,TradePwdSetting.class);
                            startActivity(intent);
                        }else {
                            AlertDialog dialog = new AlertDialog.Builder(UserPwdSafe.this).setTitle("消息提示").setMessage("您尚未设置交易密码，请先设置交易密码").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(UserPwdSafe.this,TradePwdSetting.class);
                                    intent.putExtra("id","1");
                                    startActivity(intent);
                                }
                            }).setNegativeButton("取消",null).show();
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
        login_password_safe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserPwdSafe.this,LoginPwd.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        login_password_safe = (TextView) findViewById(R.id.login_password_safe);
        trade_password_safe = (TextView) findViewById(R.id.trade_password_safe);
    }
}
