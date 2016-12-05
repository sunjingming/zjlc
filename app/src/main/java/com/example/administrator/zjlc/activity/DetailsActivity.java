package com.example.administrator.zjlc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.domain.DetailsBean;
import com.example.administrator.zjlc.domain.TJBBean;
import com.example.administrator.zjlc.urls.UrlsUtils;
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


    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            tv_lilv.setText(String.valueOf(noticeBean.getData().getBorrow_interest_rate()));
            tv_times.setText(String.valueOf(noticeBean.getData().getCollect_day()));
            tv_timess.setText(noticeBean.getData().getBorrow_duration());
            tv_menoy.setText(String.valueOf(noticeBean.getData().getBorrow_money()));
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initView();
    }
    //初始化视图
    private void initView() {
        //初始化组件
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



        tv_pasnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initData();
    }

    //获取数据填充数据
    private void initData() {
        Intent intent = getIntent();
        id = Integer.parseInt(intent.getStringExtra("id"));

        Log.e("asd", String.valueOf(id));
        RequestParams paramsNotice = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCBorrow_detail);
        paramsNotice.addBodyParameter("id",id);
        x.http().post(paramsNotice, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.e("标", data);
                Gson gson = new Gson();
                noticeBean = gson.fromJson(data, DetailsBean.class);

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

    }


}
