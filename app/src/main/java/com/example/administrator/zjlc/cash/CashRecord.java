package com.example.administrator.zjlc.cash;

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

public class CashRecord extends AppCompatActivity {

    private TextView tv_title;
    private Toolbar toolbar;
    private ListView cash_record_list;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_record);

        initView();

        SharedPreferences preferences = getSharedPreferences("usetoken", MODE_APPEND);
        token = preferences.getString("token", null);

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("提现记录");
        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCCashing_record);
        params.addBodyParameter("token",token);
        params.addBodyParameter("page","");
        params.addBodyParameter("pagesize","");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data体现记录",data);
                Gson gson  = new Gson();
                CashRecordBean recordBean = gson.fromJson(data,CashRecordBean.class);
                List<CashRecordBean.DataBean> obj = recordBean.getData();
                CashRecordAdapter adapter = new CashRecordAdapter(CashRecord.this,obj);
                cash_record_list.setAdapter(adapter);


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
        cash_record_list = (ListView) findViewById(R.id.cash_record_list);
    }
}
