package com.example.administrator.zjlc.invest;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.login.RegisterCodeBean;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.example.administrator.zjlc.userMessage.InvestBorrowBean;
import com.example.administrator.zjlc.utils.CountDownTimerUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Timer;
import java.util.TimerTask;

public class InvestBorrow extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_title;
    private TextView code;
    private Toolbar toolbar;
    private EditText borrow_money;
    private EditText borrow_name;
    private EditText borrow_phone;
    private EditText borrow_address;
    private EditText borrow_introduce;
    private Button borrow_submit;
    private Spinner borrow_style;
    private Spinner borrow_area;
    private Spinner borrow_time;
    private EditText borrow_code;
    private Button get_image_code;
    private String style;
    private String moblie;
    private RegisterCodeBean codeBean;
    private String verify;
    private int timing;
    private int cityId;
    private String token;
    String[] str = new String[]{"个人贷款", "企业贷款"};
    String[] city = new String[]{"昭通", "玉溪", "西双版纳", "文山", "曲靖", "临沧", "红河", "迪庆", "德宏", "大理", "楚雄", "保山", "丽江", "普洱", "怒江", "昆明"};
    String[] time = new String[]{"一个月", "二个月", "三个月", "四个月", "五个月", "六个月", "七个月", "八个月", "九个月", "十个月", "十一个月", "十二个月"};
    private ImageView drop_style_down;
    private ImageView drop_province_down;
    private ImageView drop_time_down;


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

        SharedPreferences fence = getSharedPreferences("usetoken", MODE_PRIVATE);
        token = fence.getString("token", "");
        moblie = fence.getString("phone", "");
        //edittext设置监听事件
        borrow_money.addTextChangedListener(textWatcher);
        borrow_name.addTextChangedListener(textWatcher);
        borrow_phone.addTextChangedListener(textWatcher);
        borrow_address.addTextChangedListener(textWatcher);
        borrow_introduce.addTextChangedListener(textWatcher);
        borrow_code.addTextChangedListener(textWatcher);


        borrow_submit.setOnClickListener(this);
        drop_province_down.setOnClickListener(this);
        drop_style_down.setOnClickListener(this);
        drop_time_down.setOnClickListener(this);

        ArrayAdapter aaa = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, str);
        borrow_style.setAdapter(aaa);

        ArrayAdapter sss = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, time);
        borrow_time.setAdapter(sss);

        ArrayAdapter ddd = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, city);
        borrow_area.setAdapter(ddd);

        borrow_style.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                tv.setTextColor(getResources().getColor(R.color.black));
                tv.setBackgroundColor(Color.WHITE);
                tv.setGravity(Gravity.CENTER);
                style = tv.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        borrow_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                tv.setTextColor(getResources().getColor(R.color.black));
                tv.setGravity(Gravity.CENTER);
                if ("昭通".equals(tv.getText().toString())) {
                    cityId = 382;
                } else if ("玉溪".equals(tv.getText().toString())) {
                    cityId = 381;
                } else if ("西双版纳".equals(tv.getText().toString())) {
                    cityId = 380;
                } else if ("文山".equals(tv.getText().toString())) {
                    cityId = 379;
                } else if ("曲靖".equals(tv.getText().toString())) {
                    cityId = 378;
                } else if ("临沧".equals(tv.getText().toString())) {
                    cityId = 377;
                } else if ("红河".equals(tv.getText().toString())) {
                    cityId = 376;
                } else if ("迪庆".equals(tv.getText().toString())) {
                    cityId = 375;
                } else if ("德宏".equals(tv.getText().toString())) {
                    cityId = 374;
                } else if ("大理".equals(tv.getText().toString())) {
                    cityId = 373;
                } else if ("楚雄".equals(tv.getText().toString())) {
                    cityId = 372;
                } else if ("保山".equals(tv.getText().toString())) {
                    cityId = 371;
                } else if ("丽江".equals(tv.getText().toString())) {
                    cityId = 370;
                } else if ("怒山".equals(tv.getText().toString())) {
                    cityId = 368;
                } else if ("普洱".equals(tv.getText().toString())) {
                    cityId = 369;
                } else if ("昆明".equals(tv.getText().toString())) {
                    cityId = 367;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        borrow_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                tv.setTextColor(getResources().getColor(R.color.black));
                tv.setGravity(Gravity.CENTER);
                if ("一个月".equals(tv.getText().toString())) {
                    timing = 1;
                } else if ("二个月".equals(tv.getText().toString())) {
                    timing = 2;
                } else if ("三个月".equals(tv.getText().toString())) {
                    timing = 3;
                } else if ("四个月".equals(tv.getText().toString())) {
                    timing = 4;
                } else if ("五个月".equals(tv.getText().toString())) {
                    timing = 5;
                } else if ("六个月".equals(tv.getText().toString())) {
                    timing = 6;
                } else if ("七个月".equals(tv.getText().toString())) {
                    timing = 7;
                } else if ("八个月".equals(tv.getText().toString())) {
                    timing = 8;
                } else if ("九个月".equals(tv.getText().toString())) {
                    timing = 9;
                } else if ("十个月".equals(tv.getText().toString())) {
                    timing = 10;
                } else if ("十一个月".equals(tv.getText().toString())) {
                    timing = 11;
                } else if ("十二个月".equals(tv.getText().toString())) {
                    timing = 12;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        get_image_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCGet_code);
                params.addBodyParameter("token", token);
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        String data = result;
                        Log.i("data注册验证码", data);
                        Gson gson = new Gson();
                        codeBean = gson.fromJson(data, RegisterCodeBean.class);
                        if (codeBean.getEvent() == 88) {
                            CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(get_image_code, 60500, 1000);
                            countDownTimerUtils.start();
                            verify = codeBean.getData();
                        } else {
                            Toast.makeText(InvestBorrow.this, codeBean.getMsg(), Toast.LENGTH_SHORT).show();
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

        borrow_style = (Spinner) findViewById(R.id.borrow_style);
        borrow_area = (Spinner) findViewById(R.id.borrow_area);
        borrow_time = (Spinner) findViewById(R.id.borrow_time);

        borrow_code = (EditText) findViewById(R.id.borrow_code);
        get_image_code = (Button) findViewById(R.id.get_image_code);
        drop_style_down = (ImageView) findViewById(R.id.drop_style_down);
        drop_province_down = (ImageView) findViewById(R.id.drop_province_down);
        drop_time_down = (ImageView) findViewById(R.id.drop_time_down);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.borrow_submit:
                loadData();
                break;
            case R.id.drop_style_down:
                borrow_style.performClick();
                break;
            case R.id.drop_province_down:
                borrow_area.performClick();
                break;
            case R.id.drop_time_down:
                borrow_time.performClick();
                break;
        }
    }

    private void loadData() {
        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCBorrow);
        params.addBodyParameter("intention", borrow_address.getText().toString());
        params.addBodyParameter("money", borrow_money.getText().toString());
        params.addBodyParameter("name", borrow_name.getText().toString());
        params.addBodyParameter("duration", timing + "");
        params.addBodyParameter("contact", borrow_phone.getText().toString());
        params.addBodyParameter("city", cityId + "");
        params.addBodyParameter("classify", style);
        params.addBodyParameter("other", borrow_introduce.getText().toString());
        params.addBodyParameter("code", borrow_code.getText().toString());
        params.addBodyParameter("province", "30");
        params.addBodyParameter("token", token);
        params.addBodyParameter("verify_code", verify);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data借款申请", data);
                Gson gson = new Gson();
                InvestBorrowBean bean = gson.fromJson(data,InvestBorrowBean.class);
                if (bean.getEvent()==88){
                    AlertDialog dialog = new AlertDialog.Builder(InvestBorrow.this).setTitle("消息提示").setMessage(bean.getMsg()).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();
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

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (borrow_money.getText().toString().length() > 0 && borrow_name.getText().toString().length() > 0 && borrow_phone.getText().toString().length() > 0 && borrow_address.getText().toString().length() > 0 && borrow_introduce.getText().toString().length() > 0 && borrow_code.getText().toString().length() > 0) {
                borrow_submit.setEnabled(true);
            } else {
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
