package com.example.administrator.zjlc.bank;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zjlc.MainActivity;
import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.ReMainActivity;
import com.example.administrator.zjlc.login.Login;
import com.example.administrator.zjlc.login.Register;
import com.example.administrator.zjlc.login.RegisterCodeBean;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.example.administrator.zjlc.utils.CountDownTimerUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Timer;
import java.util.TimerTask;


public class AddCard extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private TextView title, name, bank, area;
    private EditText cardNumber, cardNumberCheck, bankName, code;
    private Button submit, getCode;
    private String token;
    private String verify;
    String bank_name;
    String cityId;
    String provinceId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        SharedPreferences preferences = getSharedPreferences("usetoken", MODE_APPEND);
        token = preferences.getString("token", null);
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
        cardNumberCheck.addTextChangedListener(textWacther);
        cardNumber.addTextChangedListener(textWacther);
        code.addTextChangedListener(textWacther);

        RequestParams paramsAccount = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCUser_page);
        paramsAccount.addBodyParameter("token", token);
        x.http().post(paramsAccount, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data用户信息", data);
                Gson gson = new Gson();
                UserBean userbean = gson.fromJson(data, UserBean.class);
                name.setText(userbean.getData().getReal_name());
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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biddingCard();
            }
        });


    }

    private void biddingCard() {

        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCAdd_card);
        params.addBodyParameter("bank_name", bank_name);
        params.addBodyParameter("bank_address", bankName.getText().toString());
        params.addBodyParameter("card_no", cardNumber.getText().toString());
        params.addBodyParameter("city", cityId);
        params.addBodyParameter("code", code.getText().toString());
        params.addBodyParameter("province", provinceId);
        params.addBodyParameter("token", token);
        params.addBodyParameter("verify_code", verify);
        x.http().post(params, new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data", data);
                Gson gson = new Gson();
                BindBean bindBean = gson.fromJson(data, BindBean.class);
                if (bindBean.getEvent() == 88) {
                    submit.setEnabled(false);
                    AlertDialog dialog = new AlertDialog.Builder(AddCard.this).setTitle("消息提示").setMessage(bindBean.getMsg()).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            submit.setEnabled(false);
                          finish();
                        }
                    }).show();
                } else {
                    Toast.makeText(AddCard.this, bindBean.getMsg(), Toast.LENGTH_SHORT).show();
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
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) findViewById(R.id.tv_title);
        name = (TextView) findViewById(R.id.bind_user_name);
        bank = (TextView) findViewById(R.id.bind_choose_bank);
        area = (TextView) findViewById(R.id.area_choose);
        cardNumber = (EditText) findViewById(R.id.bank_card_number);
        cardNumberCheck = (EditText) findViewById(R.id.bank_card_number_checked);
        bankName = (EditText) findViewById(R.id.bank_name);
        submit = (Button) findViewById(R.id.bind_card_submit);
        getCode = (Button) findViewById(R.id.bank_get_code);
        code = (EditText) findViewById(R.id.bank_code);
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

    @Override
    protected void onResume() {
        super.onResume();
        handler.post(runnable);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.sendEmptyMessage(1);
        }
    };

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //获取验证码
                    getCode.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final RequestParams params = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCGet_code);
                            params.addBodyParameter("token", token);
                            x.http().post(params, new org.xutils.common.Callback.CommonCallback<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    String data = result;
                                    Log.i("data注册验证码", data);
                                    //    Log.i("data发送参数", params+"");
                                    Gson gson = new Gson();
                                    RegisterCodeBean codeBean = gson.fromJson(data, RegisterCodeBean.class);
                                    codeBean = gson.fromJson(data, RegisterCodeBean.class);
                                    if (codeBean.getEvent() == 88) {
                                        CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(getCode, 60500, 1000);
                                        countDownTimerUtils.start();
                                        verify = codeBean.getData();
                                    } else {
                                        Toast.makeText(AddCard.this, codeBean.getMsg(), Toast.LENGTH_SHORT).show();
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
                        }
                    });
                    //请求用户信息
                    RequestParams paramsAccount = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCUser_page);
                    paramsAccount.addBodyParameter("token", token);
                    x.http().post(paramsAccount, new org.xutils.common.Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            String data = result;
                            Log.i("data用户信息", data);
                            Gson gson = new Gson();
                            UserNamebean msgBean = gson.fromJson(data, UserNamebean.class);
                            name.setText(msgBean.getData().getReal_name());
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

                    SharedPreferences prence = getSharedPreferences("bankName", MODE_PRIVATE);
                    bank_name = prence.getString("bankChoose", "");
                    String cityName = prence.getString("cityName", "");
                    String provinceName = prence.getString("provinceName", "");
                    provinceId = prence.getString("provinceId", "");
                    cityId = prence.getString("cityId", "");

                    bank.setText(bank_name);
                    area.setText( provinceName+ "    " +cityName );

                    //提交绑卡操作
                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (cardNumber.getText().toString().length()<16){
                                Toast.makeText(AddCard.this, "请输入不小16位的银行卡号", Toast.LENGTH_SHORT).show();
                            }else {
                                biddingCard();
                            }

                        }
                    });

            }
        }
    };
    private TextWatcher textWacther = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {


            if (cardNumber.getText().toString().length() > 0 && cardNumberCheck.getText().toString().length() > 0 && code.getText().toString().length() > 0) {
                submit.setEnabled(true);
            } else {
                submit.setEnabled(false);
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
