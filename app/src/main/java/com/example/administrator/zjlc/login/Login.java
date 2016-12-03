package com.example.administrator.zjlc.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zjlc.MainActivity;
import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.example.administrator.zjlc.utils.MD5Encoder;
import com.example.administrator.zjlc.utils.MD5Utils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText login_phone;
    private EditText login_password;
    private LinearLayout activity_login;
    private TextView register;
    private Button login_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        login_submit.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    private void initView() {
        login_phone = (EditText) findViewById(R.id.login_phone);
        login_password = (EditText) findViewById(R.id.login_password);
        activity_login = (LinearLayout) findViewById(R.id.activity_login);
        login_submit = (Button) findViewById(R.id.login_submit);
        register = (TextView) findViewById(R.id.login_register);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_submit:
                final RequestParams params = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.login);
                params.addBodyParameter("user_name",login_phone.getText().toString());
                params.addBodyParameter("pwd", MD5Utils.Md5(login_password.getText().toString()));
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        String data = result;
                        Log.i("data登录",data);
                        Gson gson = new Gson();
                        LoginBean loginBean = gson.fromJson(data,LoginBean.class);
                        if (loginBean.getEvent()==88){
                            SharedPreferences prence = getSharedPreferences("usetoken",MODE_PRIVATE);
                            SharedPreferences.Editor editor = prence.edit();
                            editor.putString("token",loginBean.getData());
                            editor.commit();
                            AlertDialog dialog = new AlertDialog.Builder(Login.this).setTitle("消息提示").setMessage(loginBean.getMsg()).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent =new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            }).show();

                        }else {
                            Toast.makeText(Login.this, loginBean.getMsg(), Toast.LENGTH_SHORT).show();
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
            case R.id.login_register:
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);

        }

    }


    // TODO validate success, do something


}

