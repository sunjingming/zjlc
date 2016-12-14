package com.example.administrator.zjlc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.administrator.zjlc.MainActivity;
import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.utils.CacheUtils;

public class SplashActivity extends Activity {
    RelativeLayout rl_splash_rootview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rl_splash_rootview = (RelativeLayout) findViewById(R.id.rl_splash_rootview);
        boolean is_start_main = CacheUtils.getBoolean(SplashActivity.this, GuideActivity.START_MAIN);
        AnimationSet set = new AnimationSet(false);
        //三个动画：缩放动画，渐变动画，旋转动画

//        if(is_start_main) {
//            rl_splash_rootview.setBackgroundResource(R.drawable.screen);
//            ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
//            scaleAnimation.setDuration(1000);//动画持续时间
//            scaleAnimation.setFillAfter(true);//停留在动画播放完成后的状态
//
//
//            AlphaAnimation aa = new AlphaAnimation(0f, 1f);
//            aa.setDuration(1000);
//            aa.setFillAfter(true);
//
//
//            set.addAnimation(aa);
//            set.addAnimation(scaleAnimation);
//        }
        rl_splash_rootview.startAnimation(set);
        //监听动画播放完成-进入引导页面
        set.setAnimationListener(new MyAnimationListener());

    }

    class MyAnimationListener implements Animation.AnimationListener {

        //当动画开始的时候被回调
        @Override
        public void onAnimationStart(Animation animation) {

        }

        //当动画结束的时候被回调
        @Override
        public void onAnimationEnd(Animation animation) {

            boolean is_start_main = CacheUtils.getBoolean(SplashActivity.this, GuideActivity.START_MAIN);
            Intent intent = null;
            if (is_start_main) {
                //直接跳转到主页面
                intent = new Intent(SplashActivity.this, MainActivity.class);
            } else {
                //进入引导页面
                intent = new Intent(SplashActivity.this, GuideActivity.class);
            }
            startActivity(intent);
            finish();
        }

        //当动画重复播放的时候被回调
        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

}
