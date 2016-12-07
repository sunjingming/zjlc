package com.example.administrator.zjlc.userMessage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.login.UserBean;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class UserDetail extends AppCompatActivity {

    private TextView tv_title;
    private Toolbar toolbar;
    private ImageView user_detail_image;
    private TextView user_detail_phone;
    private TextView user_detail_approve;
    private TextView user_detail_credit;
    private TextView user_detail_invest;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        initView();

        SharedPreferences fence = getSharedPreferences("usetoken",MODE_PRIVATE);
        token = fence.getString("token", "");

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("个人信息");
        //获取账户详细信息
        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCUser_page);
        params.addBodyParameter("token", token);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data账户首页", data);
                Gson gson = new Gson();
                UserBean userBean = gson.fromJson(data, UserBean.class);
                UserBean.DataBean datalist = userBean.getData();
                Glide.with(UserDetail.this).load(datalist.getHeader_img()).into(user_detail_image);
                user_detail_phone.setText(datalist.getUser_phone());
                final int id_status = datalist.getId_status();
                if (id_status==1){
                    user_detail_approve.setText("已认证");
                }else {
                    user_detail_approve.setText("未认证");
                }
                user_detail_credit.setText(datalist.getCredit_lvl());
                user_detail_invest.setText(datalist.getInvest_lvl());



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
        user_detail_image = (ImageView) findViewById(R.id.user_detail_image);
        user_detail_phone = (TextView) findViewById(R.id.user_detail_phone);
        user_detail_approve = (TextView) findViewById(R.id.user_detail_approve);
        user_detail_credit = (TextView) findViewById(R.id.user_detail_credit);
        user_detail_invest = (TextView) findViewById(R.id.user_detail_invest);
    }
}
