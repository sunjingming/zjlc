package com.example.administrator.zjlc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.ReMainActivity;
import com.example.administrator.zjlc.utils.CacheUtils;

public class SplashActivity extends Activity {
    RelativeLayout rl_splash_rootview;
    boolean is_start_main ;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Intent intent = null;
            if (is_start_main) {
                //直接跳转到主页面
                intent = new Intent(SplashActivity.this, ReMainActivity.class);
            } else {
                //进入引导页面
                intent = new Intent(SplashActivity.this, GuideActivity.class);
            }
            startActivity(intent);
            finish();
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.e("123","启动");
        rl_splash_rootview = (RelativeLayout) findViewById(R.id.rl_splash_rootview);
        is_start_main = CacheUtils.getBoolean(SplashActivity.this, GuideActivity.START_MAIN);

        if(is_start_main) {
            rl_splash_rootview.setBackgroundResource(R.drawable.screen);
            handler.sendEmptyMessageDelayed(1,1000);
        }else{
            handler.sendEmptyMessageDelayed(1,1);
        }

    }
}
