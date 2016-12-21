package com.example.administrator.zjlc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.RelativeLayout;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.ReMainActivity;
import com.example.administrator.zjlc.utils.CacheUtils;

public class SplashActivity extends Activity {
    RelativeLayout rl_splash_rootview;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            boolean is_start_main = CacheUtils.getBoolean(SplashActivity.this, GuideActivity.START_MAIN);
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
            overridePendingTransition(0, 0);
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rl_splash_rootview = (RelativeLayout) findViewById(R.id.rl_splash_rootview);
        boolean is_start_main = CacheUtils.getBoolean(SplashActivity.this, GuideActivity.START_MAIN);
//        AnimationSet set = new AnimationSet(false);
        //三个动画：缩放动画，渐变动画，旋转动画

        if(is_start_main) {
            rl_splash_rootview.setBackgroundResource(R.drawable.screen);
        }
        handler.sendEmptyMessageDelayed(1,1000);
//        rl_splash_rootview.startAnimation(set);
        //监听动画播放完成-进入引导页面
//        set.setAnimationListener(new MyAnimationListener());

    }

//    class MyAnimationListener implements Animation.AnimationListener {
//
//        //当动画开始的时候被回调
//        @Override
//        public void onAnimationStart(Animation animation) {
//
//        }
//
//        //当动画结束的时候被回调
//        @Override
//        public void onAnimationEnd(Animation animation) {
//
//            boolean is_start_main = CacheUtils.getBoolean(SplashActivity.this, GuideActivity.START_MAIN);
//            Intent intent = null;
//            if (is_start_main) {
//                //直接跳转到主页面
//                intent = new Intent(SplashActivity.this, ReMainActivity.class);
//            } else {
//                //进入引导页面
//                intent = new Intent(SplashActivity.this, GuideActivity.class);
//            }
//            startActivity(intent);
//            finish();
//        }
//
//        //当动画重复播放的时候被回调
//        @Override
//        public void onAnimationRepeat(Animation animation) {
//
//        }
//    }

}
