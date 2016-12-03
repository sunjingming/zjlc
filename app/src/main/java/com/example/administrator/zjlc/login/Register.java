package com.example.administrator.zjlc.login;

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
import com.example.administrator.zjlc.urls.CountDownTimerUtils;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.http.body.StringBody;
import org.xutils.x;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private TextView register_back;
    private EditText register_phone;
    private EditText register_password;
    private EditText register_pwd_check;
    private EditText register_code;
    private Button register_get_code;
    private Button register;
    private TextView register_agreement;
    private LinearLayout activity_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        register_get_code.setOnClickListener(this);
        register.setOnClickListener(this);

        register_phone.addTextChangedListener(textWatcher);
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


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_get_code:
                RequestParams params = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCGet_code);
                params.addBodyParameter("phone",register_phone.getText().toString());
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        String data = result;
                        Log.i("data注册验证码",data);
                        Gson gson = new Gson();
                        RegisterBean bean = gson.fromJson(data,RegisterBean.class);
                        if (bean.getEvent()==88){
                            CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(register_get_code,60500,1000);
                            countDownTimerUtils.start();
                        }else {
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

                break;
            case R.id.register:

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
            if (register_phone.getText().toString().length()==11){
                register_get_code.setEnabled(true);
            }else {
                register_get_code.setEnabled(false);
            }
        }
    };

}
