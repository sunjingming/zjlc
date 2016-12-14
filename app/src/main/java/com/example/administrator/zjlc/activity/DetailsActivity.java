package com.example.administrator.zjlc.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.domain.DetailsBean;
import com.example.administrator.zjlc.login.UserBean;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.example.administrator.zjlc.view.MyScrollView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


public class DetailsActivity extends Activity {

    private TextView tv_lilv;
    private TextView tv_times;
    private TextView tv_timess;
    private TextView tv_menoy;
    private TextView tv_yue;
    private EditText et_mm;
    private EditText et_je;
    private Button lijigou;
    private TextView tv_xiangqing;
    private TextView tv_pasnt;
    private int id;
    private DetailsBean noticeBean;
    private MyScrollView sv_container;
    private String nametitle;
    private TextView tvjineeee;


    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            tv_lilv.setText(String.valueOf(noticeBean.getData().getBorrow_interest_rate())+"%");
            tv_times.setText(String.valueOf(noticeBean.getData().getCollect_day())+"天");
            tv_timess.setText(noticeBean.getData().getBorrow_duration());
            tv_menoy.setText(String.valueOf(noticeBean.getData().getBorrow_money())+"元");
            tvjineeee.setText("最小投资金额"+noticeBean.getData().getBorrow_min()+",最大投资金额"+noticeBean.getData().getBorrow_max());
            lijigou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = et_mm.getText().toString();
                    String s1 = et_je.getText().toString();
                    requeseDate1(s,s1);
                }
            });
            return false;
        }
    });

    private void requeseDate1(final String s, final String s1) {
        RequestParams paramsNotice = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCCoupon);

        paramsNotice.addBodyParameter("borrow_id", String.valueOf(id));
        paramsNotice.addBodyParameter("money",s);
        paramsNotice.addBodyParameter("token",token);
        x.http().post(paramsNotice, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.e("标", data);
                Gson gson = new Gson();
                Toast.makeText(DetailsActivity.this,data,Toast.LENGTH_SHORT).show();

                requeseDate(s,s1);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("标", "onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("标","onCancelled");

            }

            @Override
            public void onFinished() {
                Log.i("标", "onFinished");
            }
        });
    }

    private void requeseDate(String s,String s1) {
        RequestParams paramsNotice = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCInvest_money);

        paramsNotice.addBodyParameter("borrow_id", String.valueOf(id));
        paramsNotice.addBodyParameter("money",s);
        paramsNotice.addBodyParameter("pin",s1);
        paramsNotice.addBodyParameter("token",token);
        x.http().post(paramsNotice, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.e("标", data);
                Gson gson = new Gson();
                Toast.makeText(DetailsActivity.this,data,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("标", "onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("标","onCancelled");

            }

            @Override
            public void onFinished() {
                Log.i("标", "onFinished");
            }
        });
    }

    private String token;

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initView();
    }
    //初始化视图
    private void initView() {
        //初始化组件
        sv_container = (MyScrollView) findViewById(R.id.sv_container);
        tv_lilv = (TextView) findViewById(R.id.tv_lilv);
        tv_times = (TextView) findViewById(R.id.tv_times);
        tv_timess = (TextView) findViewById(R.id.tv_timess);
        tv_menoy = (TextView) findViewById(R.id.tv_menoy);
        tv_yue = (TextView) findViewById(R.id.tv_yue);
        tv_xiangqing = (TextView) findViewById(R.id.tv_xiangqing);
        et_je = (EditText) findViewById(R.id.et_je);
        et_mm = (EditText) findViewById(R.id.et_mm);
        lijigou = (Button) findViewById(R.id.lijigou);
        tv_pasnt = (TextView) findViewById(R.id.tv_pasnt);
        tvjineeee = (TextView) findViewById(R.id.tvjineeee);

        tv_pasnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SharedPreferences fence = getSharedPreferences("usetoken", MODE_PRIVATE);
        token = fence.getString("token", "");
        initData();
    }

    //获取数据填充数据
    private void initData() {
        Intent intent = getIntent();
        id = Integer.parseInt(intent.getStringExtra("id"));

        initDatas();
    }

    private void initDatas() {



        Log.e("asd", String.valueOf(id));
        RequestParams paramsNotice = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCBorrow_detail);

        paramsNotice.addBodyParameter("id", String.valueOf(id));
        x.http().post(paramsNotice, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.e("标", data);
                Gson gson = new Gson();
                noticeBean = gson.fromJson(data, DetailsBean.class);
                nametitle = noticeBean.getData().getBorrow_name();
                handler.sendEmptyMessage(1);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("标", "onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("标","onCancelled");

            }

            @Override
            public void onFinished() {
                Log.i("标", "onFinished");
            }
        });

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
                tv_yue.setText("您的余额：" + String.valueOf(datalist.getBalance_money())+"(元)");

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


    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;
    //设置滑动

    Intent intent;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //继承了Activity的onTouchEvent方法，直接监听点击事件
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            x1 = event.getX();
            y1 = event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            //当手指离开的时候
            x2 = event.getX();
            y2 = event.getY();
            if(y1 - y2 > 100) {
                Toast.makeText(DetailsActivity.this, "加载更多", Toast.LENGTH_SHORT).show();
                intent = new Intent(DetailsActivity.this,DetailsActivity2.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
    //上滑加载
}
