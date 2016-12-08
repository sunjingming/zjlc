package com.example.administrator.zjlc.userExercise;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.administrator.zjlc.R;

import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;


public class ExerciseDetail extends AppCompatActivity {

    private ImageView exercise_share;
    private WebView webView;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);
        initView();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        webView.loadUrl("http://zhuojin.petope.com/M/event/20160924153604873.html");
        exercise_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare(ExerciseDetail.this, null, true);
            }
        });
        
    }

    private void initView() {
        exercise_share = (ImageView) findViewById(R.id.exercise_share);
        webView = (WebView) findViewById(R.id.webView);
        back = (ImageView) findViewById(R.id.exercise_detail_back);
    }

    public static void showShare(Context context, String platformToShare, boolean showContentEdit) {
        OnekeyShare oks = new OnekeyShare();
        oks.setSilent(!showContentEdit);
        if (platformToShare != null) {
            oks.setPlatform(platformToShare);
        }
        //ShareSDK快捷分享提供两个界面第一个是九宫格 CLASSIC  第二个是SKYBLUE
        oks.setTheme(OnekeyShareTheme.CLASSIC);
        // 令编辑页面显示为Dialog模式
        oks.setDialogMode();
        // 在自动授权时可以禁用SSO方式
        oks.disableSSOWhenAuthorize();
        //oks.setAddress("12345678901"); //分享短信的号码和邮件的地址
        oks.setTitle("让红包飞");
        oks.setTitleUrl("http://zhuojin.petope.com/M/pub/registinvite.html?invite=");
        oks.setText("让红包飞起来");
        //oks.setImagePath("/sdcard/test-pic.jpg");  //分享sdcard目录下的图片
        oks.setImageUrl("http://zhuojin.petope.com/Static/Uploads/Events/20160923173543663.gif");
        oks.setUrl("http://keyinfq.petope.com/"); //微信不绕过审核分享链接
        //oks.setFilePath("/sdcard/test-pic.jpg");  //filePath是待分享应用程序的本地路劲，仅在微信（易信）好友和Dropbox中使用，否则可以不提供
        oks.setComment("分享"); //我对这条分享的评论，仅在人人网和QQ空间使用，否则可以不提供
        oks.setSite("ShareSDK");  //QZone分享完之后返回应用时提示框上显示的名称
        oks.setSiteUrl("http://keyinfq.petope.com/");//QZone分享参数
        oks.setVenueName("ShareSDK");
        oks.setVenueDescription("This is a beautiful place!");

        Bitmap logo = BitmapFactory.decodeResource(context.getResources(), R.color.white);
        String label = "";
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {

            }
        };
        oks.setCustomerLogo(logo, label, listener);
        // 启动分享
        oks.show(context);
    }
}
