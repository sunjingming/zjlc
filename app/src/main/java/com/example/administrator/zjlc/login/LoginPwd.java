package com.example.administrator.zjlc.login;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.example.administrator.zjlc.userMessage.ChangeBean;
import com.example.administrator.zjlc.userMessage.TradePwdSetting;
import com.example.administrator.zjlc.utils.CountDownTimerUtils;
import com.example.administrator.zjlc.utils.MD5Utils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Timer;
import java.util.TimerTask;

public class LoginPwd extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_title;
    private Toolbar toolbar;
    private Button change;
    private LinearLayout old_layout;
    private LinearLayout code_layout;
    private Button change_submit;
    private String style;
    private Button trade_pwd_get_code;
    private String token;
    private String id;
    private EditText old_trade_pwwd;
    private EditText trade_code;
    private EditText new_trade_pwd;
    private EditText check_trade_pwd;
    private String verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pwd);
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
        tv_title.setText("登录密码");

        old_trade_pwwd.addTextChangedListener(textWacther);
        new_trade_pwd.addTextChangedListener(textWacther);
        check_trade_pwd.addTextChangedListener(textWacther);
    }
    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        change = (Button) findViewById(R.id.pwd_change_style);
        old_layout = (LinearLayout) findViewById(R.id.old_layout);
        code_layout = (LinearLayout) findViewById(R.id.code_layout);
        change_submit = (Button) findViewById(R.id.change_submit);

        change.setOnClickListener(this);
        change_submit.setOnClickListener(this);
        trade_pwd_get_code = (Button) findViewById(R.id.trade_pwd_get_code);
        trade_pwd_get_code.setOnClickListener(this);
        old_trade_pwwd = (EditText) findViewById(R.id.old_trade_pwwd);
        old_trade_pwwd.setOnClickListener(this);
        trade_code = (EditText) findViewById(R.id.trade_code);
        trade_code.setOnClickListener(this);
        new_trade_pwd = (EditText) findViewById(R.id.new_trade_pwd);
        new_trade_pwd.setOnClickListener(this);
        check_trade_pwd = (EditText) findViewById(R.id.check_trade_pwd);
        check_trade_pwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pwd_change_style:
                style = change.getText().toString();
                if (style.equals("使用手机号修改")) {
                    old_layout.setVisibility(View.GONE);
                    code_layout.setVisibility(View.VISIBLE);
                    change.setText("使用原密码修改");
                } else if (change.getText().toString().equals("使用原密码修改")) {
                    old_layout.setVisibility(View.VISIBLE);
                    code_layout.setVisibility(View.GONE);
                    change.setText("使用手机号修改");
                }
                break;
            case R.id.change_submit:
               if (change.getText().toString().equals("使用原密码修改")) {
                    if (trade_code.getText().toString().length()<1){
                        Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    }else if (check_trade_pwd.getText().toString().equals(new_trade_pwd.getText().toString())){
                        codeData();
                    }else {
                        Toast.makeText(this, "两次密码输入不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } else if (change.getText().toString().equals("使用手机号修改")) {
                    if (old_trade_pwwd.getText().toString().length()<1){
                        Toast.makeText(this, "请输入原密码", Toast.LENGTH_SHORT).show();
                    }else if (new_trade_pwd.getText().toString().equals(check_trade_pwd.getText().toString())){
                        oldData();
                    }else {
                        Toast.makeText(this, "两次密码输入不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    }

                }


                break;
            case R.id.trade_pwd_get_code:
                final RequestParams params = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCGet_code);
                params.addBodyParameter("token", token);
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        String data = result;
                        Log.i("data注册验证码", data);
                        //    Log.i("data发送参数", params+"");
                        Gson gson = new Gson();
                        RegisterCodeBean codeBean = gson.fromJson(data, RegisterCodeBean.class);
                        codeBean = gson.fromJson(data, RegisterCodeBean.class);
                        if (codeBean.getEvent() == 88) {
                            CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(trade_pwd_get_code, 60500, 1000);
                            countDownTimerUtils.start();
                            verify = codeBean.getData();
                        } else {
                            Toast.makeText(LoginPwd.this, codeBean.getMsg(), Toast.LENGTH_SHORT).show();
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
                break;
        }
    }

    private void codeData() {
        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCForget_pwd);
        params.addBodyParameter("pwd", MD5Utils.Md5(new_trade_pwd.getText().toString()));
        params.addBodyParameter("token",token);
        params.addBodyParameter("code",trade_code.getText().toString());
        params.addBodyParameter("verify_code",verify);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data设置支付",data);
                Gson gson = new Gson();
                ChangeBean change = gson.fromJson(data,ChangeBean.class);
                if (change.getEvent()==88){
                    AlertDialog dialog = new AlertDialog.Builder(LoginPwd.this).setTitle("消息提示").setMessage(change.getMsg()).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();
                    getHomeAcvtivity();
                }else {
                    Toast.makeText(LoginPwd.this, change.getMsg(), Toast.LENGTH_SHORT).show();
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

    private void oldData() {
        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCOld_setting);
        params.addBodyParameter("old_pwd", MD5Utils.Md5(old_trade_pwwd.getText().toString()));
        params.addBodyParameter("pwd",MD5Utils.Md5(new_trade_pwd.getText().toString()));
        params.addBodyParameter("token",token);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data设置支付",data);
                Gson gson = new Gson();
                ChangeBean change = gson.fromJson(data,ChangeBean.class);
                if (change.getEvent()==88){
                    AlertDialog dialog = new AlertDialog.Builder(LoginPwd.this).setTitle("消息提示").setMessage(change.getMsg()).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();
                    getHomeAcvtivity();
                }else {
                    Toast.makeText(LoginPwd.this, change.getMsg(), Toast.LENGTH_SHORT).show();
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


    private TextWatcher textWacther = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {


            if (new_trade_pwd.getText().toString().length()>5&&check_trade_pwd.getText().toString().length()>5){
                change_submit.setEnabled(true);
            }else {
                change_submit.setEnabled(false);
            }




        }
    };
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
