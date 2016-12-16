package com.example.administrator.zjlc.userMessage;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.zjlc.MainActivity;
import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.ReMainActivity;
import com.example.administrator.zjlc.login.UserBean;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.example.administrator.zjlc.utils.CircleImageView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Timer;
import java.util.TimerTask;

public class UserMessage extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_title;
    private Toolbar toolbar;
    private CircleImageView user_message_head;
    private TextView user_name;
    private TextView user_phone;
    private TextView user_message_detail;
    private TextView user_password_safe;
    private TextView user_custom;
    private TextView user_about;
    private TextView user_exist;
    private String token;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor = null;
    private TextView two_code;
    private String url;
    private String urlTtile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message);
        initView();
        preferences = getSharedPreferences("usetoken", MODE_APPEND);
        token = preferences.getString("token", null);
        //toobar设置
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("我的");

        user_about.setOnClickListener(this);
        user_message_detail.setOnClickListener(this);
        user_exist.setOnClickListener(this);
        user_password_safe.setOnClickListener(this);
        two_code.setOnClickListener(this);
        loadData();
        loadMore();

    }

    private void loadMore() {
        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCMore);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data更多", data);
                Gson gson = new Gson();
                MoreBean moreBean = gson.fromJson(data, MoreBean.class);
                user_custom.setText(moreBean.getData().getKefu_time());
                url = moreBean.getData().getErweima();
                urlTtile = moreBean.getData().getMore_img();
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

    private void loadData() {
        //获取用户名，手机号及头像
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
                Glide.with(UserMessage.this).load(datalist.getHeader_img()).into(user_message_head);
                user_name.setText(datalist.getUser_name());
                user_phone.setText(datalist.getUser_phone());
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
        user_message_head = (CircleImageView) findViewById(R.id.user_message_head);
        user_name = (TextView) findViewById(R.id.user_name);
        user_phone = (TextView) findViewById(R.id.user_phone);
        user_message_detail = (TextView) findViewById(R.id.user_message_detail);
        user_password_safe = (TextView) findViewById(R.id.user_password_safe);
        user_custom = (TextView) findViewById(R.id.user_custom);
        user_about = (TextView) findViewById(R.id.user_about);
        user_exist = (TextView) findViewById(R.id.user_exist);
        two_code = (TextView) findViewById(R.id.two_code);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_message_detail:
                Intent intent = new Intent(UserMessage.this, UserDetail.class);
                startActivity(intent);
                break;
            case R.id.user_password_safe:
                Intent intentPwd = new Intent(UserMessage.this, UserPwdSafe.class);
                startActivity(intentPwd);
                break;
            case R.id.user_about:
                Intent intentAbout = new Intent(UserMessage.this, UserAbout.class);
                startActivity(intentAbout);
                break;
            case R.id.user_exist:
                AlertDialog dialog = new AlertDialog.Builder(UserMessage.this).setTitle("消息提示").setMessage("您确定要退出吗").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        user_name.setText("");
                        user_phone.setText("");
                        editor = preferences.edit();
                        editor.clear();
                        editor.commit();
                        Intent intent = new Intent(UserMessage.this, ReMainActivity.class);
                        intent.putExtra("id","1");
                        startActivity(intent);
                    }
                }).setNegativeButton("取消", null).show();
                break;
            case R.id.two_code:
                showPopwindow();
                break;

        }
    }

    private void showPopwindow() {
        View parent = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        View popView = View.inflate(UserMessage.this, R.layout.camera_pop_menu, null);
        Button btnCancel = (Button) popView.findViewById(R.id.btn_camera_pop_cancel);
        ImageView imageView = (ImageView) popView.findViewById(R.id.two_code_image);
        ImageView imageViewTitle = (ImageView) popView.findViewById(R.id.two_code_title);

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        final PopupWindow popWindow = new PopupWindow(popView,width,height);
        popWindow.setAnimationStyle(R.style.AnimBottom);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);// ����������������ʧ

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.btn_camera_pop_cancel:
                        popWindow.dismiss();
                        break;
                }

            }
        };

        Glide.with(UserMessage.this).load(url).into(imageView);
        Glide.with(UserMessage.this).load(urlTtile).into(imageViewTitle);
        btnCancel.setOnClickListener(listener);

        ColorDrawable dw = new ColorDrawable(0x30000000);
        popWindow.setBackgroundDrawable(dw);
        popWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

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
