package com.example.administrator.zjlc.approve;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.example.administrator.zjlc.userMessage.TradePwdSetting;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Timer;
import java.util.TimerTask;

public class ApproveName extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_title;
    private Toolbar toolbar;
    private EditText approve_name;
    private EditText approve_number;
    private Button approve_name_submit;
    private LinearLayout activity_approve_name;
    private String token;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_name);
        initView();
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("实名认证");

        SharedPreferences fence = getSharedPreferences("usetoken",MODE_PRIVATE);
        token = fence.getString("token",null);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        approve_name.addTextChangedListener(textWatcher);
        approve_number.addTextChangedListener(textWatcher);
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        approve_name = (EditText) findViewById(R.id.approve_name);
        approve_number = (EditText) findViewById(R.id.approve_number);
        approve_name_submit = (Button) findViewById(R.id.approve_name_submit);
        activity_approve_name = (LinearLayout) findViewById(R.id.activity_approve_name);

        approve_name_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.approve_name_submit:
                //进行实名认证
                RequestParams params = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCApprove_name);
                params.addBodyParameter("card_no",approve_number.getText().toString());
                params.addBodyParameter("real_name",approve_name.getText().toString());
                params.addBodyParameter("token",token);
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        String data = result;
                        Log.i("data实名认证",data);
                        Gson gson = new Gson();
                        ApproveBean bean = gson.fromJson(data,ApproveBean.class);
                        if (bean.getEvent()==88){
                            AlertDialog dilog = new AlertDialog.Builder(ApproveName.this).setTitle("消息提示").setMessage(bean.getMsg()).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if ("1".equals(id)){
                                        Intent intent = new Intent(ApproveName.this, TradePwdSetting.class);
                                        intent.putExtra("id","1");
                                        startActivity(intent);
                                    }else {
                                        finish();
                                    }
                                }
                            }).show();

                            getHomeAcvtivity();
                        }
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
                break;
        }
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
            if (approve_name.getText().toString().length()>0&&approve_number.getText().toString().length()>17){
                approve_name_submit.setEnabled(true);
            }else {
                approve_name_submit.setEnabled(false);
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
