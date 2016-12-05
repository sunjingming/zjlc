package com.example.administrator.zjlc.approve;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.zjlc.R;

public class Approve extends AppCompatActivity {


    private TextView tv_title;
    private Toolbar toolbar;
    private TextView name_approve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve);
        initView();

        tv_title.setText("认证中心");
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //进入实名认证界面
        name_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Approve.this,ApproveName.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        name_approve = (TextView) findViewById(R.id.name_approve);

    }
}
