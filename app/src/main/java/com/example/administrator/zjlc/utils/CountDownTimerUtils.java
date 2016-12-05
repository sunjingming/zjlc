package com.example.administrator.zjlc.utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;

/**
 * Created by Administrator on 2016/10/27.
 */
public class CountDownTimerUtils extends CountDownTimer {
    private Button mButton;

    /**
     * @param textView          The TextView
     *
     *
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receiver
     *                          {@link #onTick(long)} callbacks.
     */
    public CountDownTimerUtils(Button textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mButton = textView;
    }
    @Override
    public void onTick(long millisUntilFinished) {
        mButton.setEnabled(false);
        mButton.setText(millisUntilFinished/1000+"秒后重新发送");
        mButton.setWidth(600);
        /**
         * 超链接 URLSpan
         * 文字背景颜色 BackgroundColorSpan
         * 文字颜色 ForegroundColorSpan
         * 字体大小 AbsoluteSizeSpan
         * 粗体、斜体 StyleSpan
         * 删除线 StrikethroughSpan
         * 下划线 UnderlineSpan
         * 图片 ImageSpan
         */

        SpannableString spannableString = new SpannableString(mButton.getText().toString());
        ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
        /**
         * public void setSpan(Object what, int start, int end, int flags) {
         * 主要是start跟end，start是起始位置,无论中英文，都算一个。
         * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
         */
        mButton.setText(spannableString);
    }

    @Override
    public void onFinish() {
        mButton.setText("重新获取验证码");
        mButton.setEnabled(true);//重新获得点击
        mButton.setWidth(600);
    }
}
