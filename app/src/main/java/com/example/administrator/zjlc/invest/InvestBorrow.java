package com.example.administrator.zjlc.invest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Timer;
import java.util.TimerTask;

public class InvestBorrow extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_title;
    private Toolbar toolbar;
    private EditText borrow_money;
    private EditText borrow_name;
    private EditText borrow_phone;
    private EditText borrow_address;
    private EditText borrow_introduce;
    private Button borrow_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_borrow);
        initView();
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("我要借款");

        //edittext设置监听事件
        borrow_money.addTextChangedListener(textWatcher);
        borrow_name.addTextChangedListener(textWatcher);
        borrow_phone.addTextChangedListener(textWatcher);
        borrow_address.addTextChangedListener(textWatcher);
        borrow_introduce.addTextChangedListener(textWatcher);
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        borrow_money = (EditText) findViewById(R.id.borrow_money);
        borrow_name = (EditText) findViewById(R.id.borrow_name);
        borrow_phone = (EditText) findViewById(R.id.borrow_phone);
        borrow_address = (EditText) findViewById(R.id.borrow_address);
        borrow_introduce = (EditText) findViewById(R.id.borrow_introduce);
        borrow_submit = (Button) findViewById(R.id.borrow_submit);

        borrow_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.borrow_submit:
                loadData();
                break;
        }
    }

    private void loadData() {
        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCBorrow);
        params.addBodyParameter("address",borrow_address.getText().toString());
        params.addBodyParameter("amount",borrow_money.getText().toString());
        params.addBodyParameter("name",borrow_name.getText().toString());
        params.addBodyParameter("phone",borrow_phone.getText().toString());
        params.addBodyParameter("remark",borrow_introduce.getText().toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data借款申请",data);
                Gson gson  = new Gson();
                BorrowBean bean = gson.fromJson(data,BorrowBean.class);
                if (bean.getEvent()==88){
                    AlertDialog dialog = new AlertDialog.Builder(InvestBorrow.this).setTitle("消息提示").setMessage(bean.getMsg()).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();
                }
                getHomeAcvtivity();
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

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (borrow_money.getText().toString().length()>0&&borrow_name.getText().toString().length()>0&&borrow_phone.getText().toString().length()>0&&borrow_address.getText().toString().length()>0&&borrow_introduce.getText().toString().length()>0){
                borrow_submit.setEnabled(true);
            }else {
                borrow_submit.setEnabled(false);
            }

        }
    };
    private void getHomeAcvtivity() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();
            }
        };
        timer.schedule(task, 1500);
    }

}
