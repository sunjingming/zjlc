package com.example.administrator.zjlc.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

import com.example.administrator.zjlc.MainActivity;
import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.ReMainActivity;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.example.administrator.zjlc.userMessage.ChangeBean;
import com.example.administrator.zjlc.userMessage.TradePwdSetting;
import com.example.administrator.zjlc.utils.MD5Utils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Timer;
import java.util.TimerTask;

public class RegisterTradePwd extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_title;
    private Toolbar toolbar;
    private EditText setting_pwd;
    private EditText setting_check_pwd;
    private String token;
    private Button register_pwd_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_trade_pwd);
        initView();
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("设置交易密码");
        setting_pwd.addTextChangedListener(textWatcher);
        setting_check_pwd.addTextChangedListener(textWatcher);
        register_pwd_submit.setOnClickListener(this);
        SharedPreferences fence = getSharedPreferences("usetoken",MODE_PRIVATE);
        token = fence.getString("token",null);

    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setting_pwd = (EditText) findViewById(R.id.setting_pwd);
        setting_check_pwd = (EditText) findViewById(R.id.setting_check_pwd);
        register_pwd_submit = (Button) findViewById(R.id.register_pwd_submit);
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
            if (setting_check_pwd.getText().toString().length() > 5 && setting_pwd.getText().toString().length() > 5) {
                register_pwd_submit.setEnabled(true);
            } else {
                register_pwd_submit.setEnabled(false);
            }
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_pwd_submit:
                if (setting_pwd.getText().toString().equals(setting_check_pwd.getText().toString())){
                    RequestParams params = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCTrade_setting);
                    params.addBodyParameter("pin_pass_new", MD5Utils.Md5(setting_check_pwd.getText().toString()));
                    params.addBodyParameter("token", token);
                    params.addBodyParameter("type", "1");
                    x.http().post(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            String data = result;
                            Log.i("data设置支付", data);
                            Gson gson = new Gson();
                            ChangeBean change = gson.fromJson(data, ChangeBean.class);
                            if (change.getEvent() == 88) {
                                AlertDialog dialog = new AlertDialog.Builder(RegisterTradePwd.this).setTitle("消息提示").setMessage("交易密码设置成功" + "\n" + "对话框将在三秒后消失").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(RegisterTradePwd.this, ReMainActivity.class);
                                        intent.putExtra("id","1");
                                        startActivity(intent);
                                    }
                                }).show();
                                getHomeAcvtivity();
                            } else {
                                Toast.makeText(RegisterTradePwd.this, change.getMsg(), Toast.LENGTH_SHORT).show();
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
                }else {
                    Toast.makeText(this, "两此输入密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    private void getHomeAcvtivity() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(RegisterTradePwd.this, ReMainActivity.class);
                intent.putExtra("id","1");
                startActivity(intent);

            }
        };
        timer.schedule(task, 3000);
    }

}
