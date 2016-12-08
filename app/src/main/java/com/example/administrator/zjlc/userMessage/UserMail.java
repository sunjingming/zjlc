package com.example.administrator.zjlc.userMessage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

public class UserMail extends AppCompatActivity {

    private TextView tv_title;
    private Toolbar toolbar;
    private ListView user_mail;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_mail);
        initView();

        SharedPreferences fence = getSharedPreferences("usetoken", MODE_PRIVATE);
        token = fence.getString("token", "");

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("站内信");

        //获取站内信
        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCMail);
        params.addBodyParameter("token",token);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data站内信",data);
                Gson gson = new Gson();
                UserMailBean mailbean = gson.fromJson(data,UserMailBean.class);
                final List<UserMailBean.DataBean> data1 = mailbean.getData();
                UserMailAdapter adapter = new UserMailAdapter(UserMail.this,data1);
                user_mail.setAdapter(adapter);

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
        user_mail = (ListView) findViewById(R.id.user_mail);
    }
}
