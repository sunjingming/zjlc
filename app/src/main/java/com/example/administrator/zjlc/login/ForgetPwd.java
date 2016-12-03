package com.example.administrator.zjlc.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.urls.UrlsUtils;

import org.xutils.http.RequestParams;

public class ForgetPwd extends AppCompatActivity implements View.OnClickListener {

    private ImageView forget_back;
    private EditText forget_phone;
    private EditText forget_code;
    private Button forget_get_code;
    private EditText forget_pwd_password;
    private EditText forget_pwd_check;
    private Button forget_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        initView();

        forget_get_code.setOnClickListener(this);
        forget_submit.setOnClickListener(this);
        forget_back.setOnClickListener(this);
        forget_phone.addTextChangedListener(textWatcher);
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
                getCode();
                break;
            case R.id.forget_submit:

                break;
        }
    }

    private void getCode() {
        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCGet_code);

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
            if (forget_phone.getText().toString().length()==11){
                forget_get_code.setEnabled(true);
            }else {
                forget_get_code.setEnabled(false);
            }
        }
    };


}
