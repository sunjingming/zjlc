package com.example.administrator.zjlc.invest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.bank.ApproveJuadgeBean;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class Invest extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_title;
    private Toolbar toolbar;
    private TextView user_borrow;
    private TextView user_invest_record;
    private TextView user_money_record;
    private String token;
    private int event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest);
        initView();
        SharedPreferences fence = getSharedPreferences("usetoken", MODE_PRIVATE);
        token = fence.getString("token", "");

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("我的理财");
        user_borrow.setOnClickListener(this);
        user_invest_record.setOnClickListener(this);
        user_money_record.setOnClickListener(this);


        //判断是否进行实名认证
        RequestParams paramms = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCApprove_juadge);
        paramms.addBodyParameter("token", token);
        x.http().post(paramms, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data是否实名", data);
                Gson gson = new Gson();
                ApproveJuadgeBean juadgeBean = gson.fromJson(data, ApproveJuadgeBean.class);
                event = juadgeBean.getEvent();

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
        user_borrow = (TextView) findViewById(R.id.user_borrow);
        user_invest_record = (TextView) findViewById(R.id.user_invest_record);
        user_money_record = (TextView) findViewById(R.id.user_money_record);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_borrow:
                if (event==88){
                    Intent intent = new Intent(Invest.this,InvestBorrow.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "尚未通过实名认证，暂不能使用我要借款功能", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.user_invest_record:
                Intent intentInvest = new Intent(Invest.this,InvestRecord.class);
                startActivity(intentInvest);
                break;
            //进入资金记录界面
            case R.id.user_money_record:
                Intent intentRecord = new Intent(Invest.this,MoneyRecord.class);
                startActivity(intentRecord);
                break;
        }
    }
}
