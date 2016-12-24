package com.example.administrator.zjlc.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.domain.DetailsBean;
import com.example.administrator.zjlc.invest.RedPacketBean;
import com.example.administrator.zjlc.login.RegisterCodeBean;
import com.example.administrator.zjlc.login.UserBean;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.example.administrator.zjlc.utils.MD5Utils;
import com.example.administrator.zjlc.view.MyScrollView;
import com.example.administrator.zjlc.view.MyScrollView1;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;


public class DetailsActivity extends Activity implements MyScrollView.OnScrollListener {

    private TextView tv_lilv;
    private TextView tv_times;
    private TextView tv_timess;
    private TextView tv_menoy;
    private TextView tv_yue;
    private EditText et_mm;
    private EditText et_je;
    private Button lijigou;
    private TextView tv_xiangqing;
    private TextView tv_pasnt;
    private int id;
    private DetailsBean noticeBean;
    private MyScrollView1 tv_scr;
    private TextView tvjineeee;
    private LinearLayout tequanjine;
    private TextView tequanjin;
    private LinearLayout ll_passs;
    private SwipeRefreshLayout swipeRefreshLayout3;


    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            tv_lilv.setText(String.valueOf(noticeBean.getData().getBorrow_interest_rate())+"%");
            tv_times.setText(String.valueOf(noticeBean.getData().getCollect_day())+"天");
            tv_timess.setText(noticeBean.getData().getBorrow_duration());
            tv_menoy.setText(String.valueOf(noticeBean.getData().getHas_borrow())+".00元");
            if(noticeBean.getData().getBorrow_max() == 0){
                tvjineeee.setText("最小投资金额:"+noticeBean.getData().getBorrow_min()+".00,最大投资金额:无限制");
            }else{
                tvjineeee.setText("最小投资金额:"+noticeBean.getData().getBorrow_min()+".00,最大投资金额:"+noticeBean.getData().getBorrow_max()+".00");
            }

            if(noticeBean.getData().getBorrow_status() == 4){
                lijigou.setText("标满，复审中");
            }else if(noticeBean.getData().getBorrow_status() == 2){
                lijigou.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s1 = et_mm.getText().toString();
                        String s = et_je.getText().toString();
                        et_mm.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        if (s.equals("")) {
                            new AlertDialog.Builder(DetailsActivity.this).setMessage("请输入投资金额").show();
                        }else if(s1.equals("") ){
                            new AlertDialog.Builder(DetailsActivity.this).setMessage("请输入交易密码").show();
                        } else {
                            requeseDate(s, s1,s11);
                        }
                    }
                });
            }if(noticeBean.getData().getBorrow_status() == 6){
                lijigou.setText("复审通过，还款中");
            }if(noticeBean.getData().getBorrow_status() == 7){
                lijigou.setText("完成");
            }if(noticeBean.getData().getBorrow_status() == 8){
                lijigou.setText("已逾期");
            }
            tv_scr.registerOnScrollViewScrollToBottom(new MyScrollView1.OnScrollBottomListener() {
                @Override
                public void srollToBottom() {
                    Intent intent;
                    intent = new Intent(DetailsActivity.this, DetailsActivity2.class);
                    if(id != 0){
                        intent.putExtra("id", id);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) ;
                        startActivity(intent);
                    }
                }
            });
//            tv_scr.setOnScrollToBottomLintener(new MyScrollView.OnScrollToBottomListener() {
//                @Override
//                public void onScrollBottomListener(boolean isBottom) {
//                    if(isBottom) {
////                        intent = new Intent(DetailsActivity.this, DetailsActivity2.class);
////                        intent.putExtra("id", id);
////                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) ;
////                        startActivity(intent);
//                        Log.e("底部","1 、");
//                    }
//                }
//            });
            return false;
        }
    });

    private RedPacketBean packetBean;
    private ArrayList<RedPacketBean.DataBean> dataBeanArrayList;
    private ListView lv;
    private String[] msg;
    private RedPacketBean.DataBean s11;
    private void requeseDate1(final String s) {
        dataBeanArrayList = new ArrayList<RedPacketBean.DataBean>();
        final RequestParams paramsNotice = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCCoupon);

        Log.e("特权金请求",id+"-"+s+"-"+token);
        paramsNotice.addBodyParameter("borrow_id", String.valueOf(id));
        paramsNotice.addBodyParameter("money",s);
        paramsNotice.addBodyParameter("token",token);
        x.http().post(paramsNotice, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.e("标11", data);
                Gson gson = new Gson();

                packetBean = gson.fromJson(data,RedPacketBean.class);
                msg = new String[packetBean.getData().size()];
                final boolean[] bol = new boolean[packetBean.getData().size()];
                Log.e("标11", String.valueOf(packetBean.getData().size()));
                for (int i = 0; i < packetBean.getData().size(); i++) {
                    dataBeanArrayList.add(packetBean.getData().get(i));
                    msg[i] = packetBean.getData().get(i).getMoney() + "元";
                    bol[i] = false;
                    Log.e("sad", msg[i]);
                }
//                tequanjine.setVisibility(View.VISIBLE);
                tequanjin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(bol.length>1) {
                            new AlertDialog.Builder(DetailsActivity.this)
                                    .setTitle("选择特权金")
                                    .setMultiChoiceItems(msg, bol, new DialogInterface.OnMultiChoiceClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                            s11 = dataBeanArrayList.get(which);
                                            dialog.dismiss();
                                        }
                                    })
                                    .setNegativeButton("取消", null)
                                    .show();
                        }else{
                            Toast.makeText(DetailsActivity.this,"无可用特权金",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("标", "onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("标","onCancelled");

            }

            @Override
            public void onFinished() {
                Log.i("标", "onFinished");
            }
        });
    }

    private void requeseDate(final String s, final String s1, final RedPacketBean.DataBean s2) {
        final RequestParams paramsNotice = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCInvest_money);

        final EditText editText = new EditText(DetailsActivity.this);
        editText.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        paramsNotice.addBodyParameter("borrow_id", String.valueOf(id));
        if(noticeBean.getData().getHas_pass() == 1){
            new AlertDialog.Builder(DetailsActivity.this)
                    .setTitle("请输入定向密码")
                    .setView(editText)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String s3 = editText.getText().toString();
                            Log.e("assss", MD5Utils.Md5(s3));
                            paramsNotice.addBodyParameter("borrow_pass",s3);
                            if(s2!=null) {
                                Log.e("11", s2.getId());
                                paramsNotice.addBodyParameter("coupon_id", s2.getId());
                            }else{
                                paramsNotice.addBodyParameter("coupon_id","");
                            }
                            paramsNotice.addBodyParameter("money",s);

                            paramsNotice.addBodyParameter("pin", MD5Utils.Md5(s1));
                            paramsNotice.addBodyParameter("token",token);
                            x.http().post(paramsNotice, new Callback.CommonCallback<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    String data = result;
                                    Log.e("标321", data);

                                    Gson gson = new Gson();
                                    RegisterCodeBean codeBean = gson.fromJson(data, RegisterCodeBean.class);
                                    codeBean = gson.fromJson(data, RegisterCodeBean.class);
                                    Toast.makeText(DetailsActivity.this,codeBean.getMsg(),Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onError(Throwable ex, boolean isOnCallback) {
                                    Log.i("标", "onError");
                                }

                                @Override
                                public void onCancelled(CancelledException cex) {
                                    Log.e("标","onCancelled");
                                }

                                @Override
                                public void onFinished() {
                                    Log.i("标123", "onFinished");
                                }
                            });
                        }
                    })
                    .show();
        }else{
            paramsNotice.addBodyParameter("borrow_pass","");
            if(s2!=null) {
                Log.e("11", s2.getId());
                paramsNotice.addBodyParameter("coupon_id", s2.getId());
            }else{
                paramsNotice.addBodyParameter("coupon_id","");
            }
            paramsNotice.addBodyParameter("money",s);

            paramsNotice.addBodyParameter("pin", MD5Utils.Md5(s1));
            paramsNotice.addBodyParameter("token",token);
            x.http().post(paramsNotice, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    String data = result;
                    Log.e("标321", data);

                    Gson gson = new Gson();
                    RegisterCodeBean codeBean = JSON.parseObject(data, RegisterCodeBean.class);
                    codeBean = JSON.parseObject(data, RegisterCodeBean.class);


                    if("投资成功".equals(codeBean.getMsg())){
                        et_je.setText("");
                        et_mm.setText("");
                        initDatas();
                        Toast.makeText(DetailsActivity.this,"投资成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        if("投标密码错误".equals(codeBean.getMsg())){
                            Toast.makeText(DetailsActivity.this,"定向标密码",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(DetailsActivity.this,codeBean.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.i("标", "onError");
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    Log.e("标","onCancelled");
                }

                @Override
                public void onFinished() {
                    Log.i("标123", "onFinished");
                }
            });
        }

    }

    private String token;

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initView();
    }
    //初始化视图
    private void initView() {
        //初始化组件
        tv_scr = (MyScrollView1) findViewById(R.id.tv_scr);
        tv_lilv = (TextView) findViewById(R.id.tv_lilv);
        tv_times = (TextView) findViewById(R.id.tv_times);
        tv_timess = (TextView) findViewById(R.id.tv_timess);
        tv_menoy = (TextView) findViewById(R.id.tv_menoy);
        tv_yue = (TextView) findViewById(R.id.tv_yue);
        tv_xiangqing = (TextView) findViewById(R.id.tv_xiangqing);
        et_je = (EditText) findViewById(R.id.et_je);
        et_mm = (EditText) findViewById(R.id.et_mm);
        lijigou = (Button) findViewById(R.id.lijigou);
        tv_pasnt = (TextView) findViewById(R.id.tv_pasnt);
        tvjineeee = (TextView) findViewById(R.id.tvjineeee);
        tequanjine = (LinearLayout) findViewById(R.id.tequanjine);
        tequanjin = (TextView) findViewById(R.id.tequanjin);
        ll_passs = (LinearLayout) findViewById(R.id.ll_passs);

        swipeRefreshLayout3 = (SwipeRefreshLayout) findViewById(R.id.layout_swipe_refresh3);
        swipeRefreshLayout3.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initDatas();
                swipeRefreshLayout3.setRefreshing(false);
            }
        });
        //测量屏幕
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int height = metric.heightPixels;   // 屏幕高度（像素）

        ll_passs.setMinimumHeight(height+1);
        tv_pasnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SharedPreferences fence = getSharedPreferences("usetoken", MODE_PRIVATE);
        token = fence.getString("token", "");
        initData();
    }

    //获取数据填充数据
    private void initData() {
        Intent intent = getIntent();
        id = Integer.parseInt(intent.getStringExtra("id"));

        initDatas();
        et_je.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = et_je.getText().toString();
//                if(noticeBean.getData().getBorrow_bid() == 1) {
                    requeseDate1(s1);
//                }
            }
        });
    }

    private void initDatas() {
        Log.e("asd", String.valueOf(id));


        RequestParams paramsNotice = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCBorrow_detail);

        paramsNotice.addBodyParameter("id", String.valueOf(id));
        x.http().post(paramsNotice, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.e("标", data);
                Gson gson = new Gson();
                noticeBean = JSON.parseObject(data, DetailsBean.class);
                handler.sendEmptyMessage(1);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("标", "onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("标","onCancelled");

            }

            @Override
            public void onFinished() {
                Log.i("标", "onFinished");
            }
        });

        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCUser_page);
        params.addBodyParameter("token", token);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data账户首页", data);
                Gson gson = new Gson();
                UserBean userBean = gson.fromJson(data, UserBean.class);
                UserBean.DataBean datalist = userBean.getData();
                tv_yue.setText("您的余额：" + String.valueOf(datalist.getBalance_money())+"(元)");
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

    @Override
    public void onScroll(int y) {

    }

}
