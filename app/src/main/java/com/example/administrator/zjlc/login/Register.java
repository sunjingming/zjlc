package com.example.administrator.zjlc.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import com.example.administrator.zjlc.approve.ApproveName;
import com.example.administrator.zjlc.utils.CountDownTimerUtils;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Timer;
import java.util.TimerTask;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private TextView register_back;
    private TextView register_check;
    private EditText register_phone;
    private EditText register_password;
    private EditText register_pwd_check;
    private EditText register_code;
    private EditText register_invite;
    private Button register_get_code;
    private Button register;
    private TextView register_agreement;
    private LinearLayout activity_register;
    private RegisterCodeBean codeBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        register_get_code.setOnClickListener(this);
        register.setOnClickListener(this);
        register_back.setOnClickListener(this);

        register_phone.addTextChangedListener(textWatcher);
        register_password.addTextChangedListener(textWatcher);
        register_pwd_check.addTextChangedListener(textWatcher);
        register_code.addTextChangedListener(textWatcher);
    }

    private void initView() {
        register_back = (TextView) findViewById(R.id.register_back);
        register_phone = (EditText) findViewById(R.id.register_phone);
        register_password = (EditText) findViewById(R.id.register_password);
        register_pwd_check = (EditText) findViewById(R.id.register_pwd_check);
        register_code = (EditText) findViewById(R.id.register_code);
        register_get_code = (Button) findViewById(R.id.register_get_code);
        register = (Button) findViewById(R.id.register);
        register_agreement = (TextView) findViewById(R.id.register_agreement);
        activity_register = (LinearLayout) findViewById(R.id.activity_register);
        register_check = (TextView) findViewById(R.id.register_phone_right);
        register_invite = (EditText) findViewById(R.id.register_invite_code);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_get_code:
                RequestParams params = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCGet_code);
                params.addBodyParameter("phone", register_phone.getText().toString());
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        String data = result;
                        Log.i("data注册验证码", data);
                        Gson gson = new Gson();
                        codeBean = gson.fromJson(data, RegisterCodeBean.class);
                        if (codeBean.getEvent() == 88) {
                            CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(register_get_code, 60500, 1000);
                            countDownTimerUtils.start();
                        } else {
                            Toast.makeText(Register.this, codeBean.getMsg(), Toast.LENGTH_SHORT).show();
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
            case R.id.register:

                if (register_password.getText().toString().equals(register_pwd_check.getText().toString())) {
                    RequestParams paramsRegister = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCRegister);
                    paramsRegister.addBodyParameter("phone", register_phone.getText().toString());
                    paramsRegister.addBodyParameter("code", register_code.getText().toString());
                    paramsRegister.addBodyParameter("invite_code", register_invite.getText().toString());
                    paramsRegister.addBodyParameter("pwd", register_password.getText().toString());
                    paramsRegister.addBodyParameter("user_name", "");
                    paramsRegister.addBodyParameter("verify_code", codeBean.getData());
                    x.http().post(paramsRegister, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            String data = result;
                            Log.i("data注册", data);
                            Gson gson = new Gson();
                            final RegisterBean bean = gson.fromJson(data, RegisterBean.class);
                            if (bean.getEvent() == 88) {
                                SharedPreferences prence = getSharedPreferences("usetoken", MODE_PRIVATE);
                                SharedPreferences.Editor editor = prence.edit();
                                editor.putString("token", bean.getData());
                                editor.commit();
                                AlertDialog dialog = new AlertDialog.Builder(Register.this).setTitle("消息提示").setMessage("恭喜您注册成功，为了您更好的使用卓金理财，请点击确定完成实名认证和设置交易密码").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent  intent = new Intent(Register.this, ApproveName.class);
                                        intent.putExtra("id","1");
                                        intent.putExtra("token",bean.getData());
                                        startActivity(intent);

                                    }
                                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                }).show();
                            } else {
                                Toast.makeText(Register.this, bean.getMsg(), Toast.LENGTH_SHORT).show();
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
                } else {
                    Toast.makeText(this, "两次输入密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.register_back:
                finish();
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
            if (register_phone.getText().toString().length() == 11) {
                register_get_code.setEnabled(true);
//                RequestParams params  =new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCPhone_check);
//                params.addBodyParameter("phone");
            } else {
                register_get_code.setEnabled(false);
            }

            if (register_phone.getText().toString().length() == 11 && register_password.getText().toString().length() > 5 && register_pwd_check.getText().toString().length() > 5 && register_code.getText().toString().length() > 5) {
                register.setEnabled(true);
            } else {
                register.setEnabled(false);
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
