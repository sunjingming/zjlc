package com.example.administrator.zjlc.invest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.zjlc.R;

public class MoneyMatter extends AppCompatActivity {

    private TextView tv_title;
    private Toolbar toolbar;
    private TextView user_red_packet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_matter);
        initView();
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("我的福利");

        user_red_packet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入特权金页面
                Intent intent = new Intent(MoneyMatter.this,RedPacket.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        user_red_packet = (TextView) findViewById(R.id.user_red_packet);
    }
}
