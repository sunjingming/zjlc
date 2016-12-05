package com.example.administrator.zjlc.bank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.zjlc.R;

public class AddCard extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private TextView title, name, bank, area;
    private EditText cardNumber, cardNumberCheck, bankName;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        SharedPreferences preferences = getSharedPreferences("userToken", MODE_APPEND);
        final String token = preferences.getString("token", null);
        initView();
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText("绑定银行卡");

        bank.setOnClickListener(this);
        area.setOnClickListener(this);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) findViewById(R.id.tv_title);
        name = (TextView) findViewById(R.id.bind_householder_name);
        bank = (TextView) findViewById(R.id.bind_choose_bank);
        area = (TextView) findViewById(R.id.area_choose);
        cardNumber = (EditText) findViewById(R.id.bank_card_number);
        cardNumberCheck = (EditText) findViewById(R.id.bank_card_number_checked);
        bankName = (EditText) findViewById(R.id.bank_name);
        submit = (Button) findViewById(R.id.bind_card_submit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bind_choose_bank:
                Intent intent = new Intent(AddCard.this, BankChoose.class);
                startActivity(intent);
                break;
            case R.id.area_choose:
                Intent intent1 = new Intent(AddCard.this, AreaChoose.class);
                startActivity(intent1);
        }
    }


}
