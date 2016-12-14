package com.example.administrator.zjlc.invest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.zjlc.R;

public class Invest extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_title;
    private Toolbar toolbar;
    private TextView user_borrow;
    private TextView user_invest_record;
    private TextView user_money_record;
    private String token;


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
                Intent intent = new Intent(Invest.this,InvestBorrow.class);
                startActivity(intent);
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
