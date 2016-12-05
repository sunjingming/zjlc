package com.example.administrator.zjlc.login;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.utils.CountDownTimerUtils;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.example.administrator.zjlc.utils.MD5Utils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Timer;
import java.util.TimerTask;

public class ForgetPwd extends AppCompatActivity implements View.OnClickListener {

    private ImageView forget_back;
    private EditText forget_phone;
    private EditText forget_code;
    private Button forget_get_code;
    private EditText forget_pwd_password;
    private EditText forget_pwd_check;
    private Button forget_submit;
    private RegisterCodeBean codeBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        initView();

        forget_get_code.setOnClickListener(this);
        forget_submit.setOnClickListener(this);
        forget_back.setOnClickListener(this);

        forget_phone.addTextChangedListener(textWatcher);
        forget_code.addTextChangedListener(textWatcher);
        forget_pwd_password.addTextChangedListener(textWatcher);
    }

    private void initView() {
        forget_back = (ImageView) findViewById(R.id.forget_back);
        forget_phone = (EditText) findViewById(R.id.forget_phone);
        forget_code = (EditText) findViewById(R.id.forget_code);
        forget_get_code = (Button) findViewById(R.id.forget_get_code);
        forget_pwd_password = (EditText) findViewById(R.id.forget_pwd_password);
        forget_pwd_check = (EditText) findViewById(R.id.forget_pwd_check);
        forget_submit = (Button) findViewById(R.id.forget_submit);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forget_back:
                finish();
            case R.id.forget_get_code:
                //获取验证码
                getCode();
                break;
            case R.id.forget_submit:
                //找回密码
                findPwd();
                break;
        }
    }

    //获取验证码
    private void getCode() {
        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCGet_code);
        params.addBodyParameter("find","1");
        params.addBodyParameter("phone",forget_phone.getText().toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Gson gson = new Gson();
                 codeBean = gson.fromJson(data,RegisterCodeBean.class);
                if (codeBean.getEvent()==88){
                    CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(forget_get_code, 60500, 1000);
                    countDownTimerUtils.start();
                }else {
                    Toast.makeText(ForgetPwd.this, codeBean.getMsg(), Toast.LENGTH_SHORT).show();
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

    //提交找回密码
    private void findPwd() {
        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCForget_pwd);
        params.addBodyParameter("phone",forget_phone.getText().toString());
        params.addBodyParameter("pwd", MD5Utils.Md5(forget_pwd_password.getText().toString()));
        params.addBodyParameter("code",forget_code.getText().toString());
        params.addBodyParameter("verify_code",codeBean.getData());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data找回密码",data);
                Gson gson = new Gson();
                ForgetPwdBean bean = gson.fromJson(data,ForgetPwdBean.class);
                if (bean.getEvent()==88){
                    AlertDialog dialog = new AlertDialog.Builder(ForgetPwd.this).setTitle("消息提示").setMessage(bean.getMsg()).setPositiveButton("确定", new DialogInterface.OnClickListener() {
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

    //监听输入框
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (forget_phone.getText().toString().length()==11){
                forget_get_code.setEnabled(true);
            }else {
                forget_get_code.setEnabled(false);
            }

            if (forget_phone.getText().toString().length()==11&&forget_code.getText().toString().length()>5&&forget_pwd_password.getText().toString().length()>5){
                forget_submit.setEnabled(true);
            }else {
                forget_submit.setEnabled(false);
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
