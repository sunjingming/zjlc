package com.example.administrator.zjlc.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.adapter.AdapterJiLu;
import com.example.administrator.zjlc.adapter.AddressSpacesItemDecoration;
import com.example.administrator.zjlc.domain.DetailsBean;
import com.example.administrator.zjlc.domain.SanBiaoGouBean;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DetailsActivity2 extends AppCompatActivity {
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;
    private Button but1;
    private Button but2;
    private TextView tv_pasnt;
    private LinearLayout ll_layout;
    private LinearLayout ll_rec;

    private SwipeRefreshLayout swipeRefreshLayout1;
    private SwipeRefreshLayout swipeRefreshLayout2;

    private RecyclerView recyclerview_rec;
    private AdapterJiLu myRecyclerView;
    private SanBiaoGouBean sanBiaoGouBean;
    private ArrayList<SanBiaoGouBean.DataBean> dataBeanArrayList;

    private DetailsBean detalsBeans;
    private int id;

    //进行数据更新
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if(msg.what == 2) {
                tv1.setText(detalsBeans.getData().getBorrow_name() + "");
                tv2.setText(detalsBeans.getData().getBorrow_status_str() + "");
                tv3.setText(String.valueOf(detalsBeans.getData().getBorrow_money()) + ".00");
                tv4.setText(detalsBeans.getData().getAdd_time() + "");
                tv5.setText(String.valueOf(detalsBeans.getData().getBorrow_times()) + "次");
                tv6.setText(detalsBeans.getData().getBorrow_type());
                Log.e("123",detalsBeans.getData().toString()+"");
                if (detalsBeans.getData().getProgress() == 100) {
                    tv7.setText(new DecimalFormat("00").format(detalsBeans.getData().getProgress()) + "%");
                } else {
                    tv7.setText(new DecimalFormat("0.00").format(detalsBeans.getData().getProgress()) + "%");
                }

                tv8.setText(detalsBeans.getData().getRepayment_type());

                if (detalsBeans.getData().getReward_num() == 100) {
                    tv9.setText(new DecimalFormat("0").format(detalsBeans.getData().getReward_num()) + "%");
                } else {
                    tv9.setText(new DecimalFormat("0.00").format(detalsBeans.getData().getReward_num()) + "%");
                }
            }else if(msg.what == 1){
//            tv9.setText(String.valueOf(detalsBeans.getData().getReward_num())+"%");
                updateSingleView(dataBeanArrayList);
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);
        //初始化组件
        initView();
    }

    private void initView() {

        //注册组件
        tv1 = (TextView) findViewById(R.id.tv_name1);
        tv2 = (TextView) findViewById(R.id.tv_name2);
        tv3 = (TextView) findViewById(R.id.tv_name3);
        tv4 = (TextView) findViewById(R.id.tv_name4);
        tv5 = (TextView) findViewById(R.id.tv_name5);
        tv6 = (TextView) findViewById(R.id.tv_name6);
        tv7 = (TextView) findViewById(R.id.tv_name7);
        tv8 = (TextView) findViewById(R.id.tv_name8);
        tv9 = (TextView) findViewById(R.id.tv_name9);
        tv_pasnt = (TextView) findViewById(R.id.tv_pasnt);
        ll_layout = (LinearLayout) findViewById(R.id.ll_layout);
        ll_rec = (LinearLayout) findViewById(R.id.ll_rec);
        tv_pasnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        but1 = (Button) findViewById(R.id.but1);
        but2 = (Button) findViewById(R.id.but2);
        dataBeanArrayList = new ArrayList<SanBiaoGouBean.DataBean>();
        myRecyclerView = new AdapterJiLu(this,dataBeanArrayList);
        //初始化组件
        recyclerview_rec = (RecyclerView) findViewById(R.id.recyclerview_rec);
        recyclerview_rec.addItemDecoration(new AddressSpacesItemDecoration(20, 20, 20, 20));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayout.VERTICAL);//默认是LinearLayout.VERTICAL
        //设置布局管理器
        recyclerview_rec.setLayoutManager(layoutManager);

        //设置Adapter
        recyclerview_rec.setAdapter(myRecyclerView);
        //设置增加或删除条目的动画
        recyclerview_rec.setItemAnimator(new DefaultItemAnimator());

        swipeRefreshLayout1 = (SwipeRefreshLayout) findViewById(R.id.layout_swipe_refresh1);
        swipeRefreshLayout2 = (SwipeRefreshLayout) findViewById(R.id.layout_swipe_refresh2);
        swipeRefreshLayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dataBeanArrayList=new ArrayList<SanBiaoGouBean.DataBean>();
                initData();
                swipeRefreshLayout1.setRefreshing(false);
            }
        });

        swipeRefreshLayout2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dataBeanArrayList=new ArrayList<SanBiaoGouBean.DataBean>();
                initData();
                swipeRefreshLayout2.setRefreshing(false);
            }
        });
        //设置按钮切换
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                but1.setBackgroundResource(R.drawable.biankuangtv);
                but2.setTextColor(Color.BLACK);
                but1.setTextColor(Color.RED);
                but2.setBackgroundResource(R.color.colorAccent);
                swipeRefreshLayout1.setVisibility(View.VISIBLE);
                swipeRefreshLayout2.setVisibility(View.GONE);
            }
        });
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                but2.setBackgroundResource(R.drawable.biankuangtv);
                but1.setBackgroundResource(R.color.colorAccent);
                but1.setTextColor(Color.BLACK);
                but2.setTextColor(Color.RED);
                swipeRefreshLayout1.setVisibility(View.GONE);
                swipeRefreshLayout2.setVisibility(View.VISIBLE);
            }
        });

        //设置数据
        initData();
    }

    private void initData() {
        Intent intent = getIntent();

        id = intent.getIntExtra("id",0);

        final RequestParams paramsNotice = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCBorrow_detail);
        paramsNotice.addBodyParameter("id", String.valueOf(id));

        x.http().post(paramsNotice, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.e("标", data);
                Gson gson = new Gson();
                detalsBeans = JSON.parseObject(data, DetailsBean.class);
                handler.sendEmptyMessage(2);
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

        RequestParams paramsNotice1 = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCBorrow_invest_list);
        paramsNotice1.addBodyParameter("id", String.valueOf(id));

        x.http().post(paramsNotice1, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.e("标", data);
                Gson gson = new Gson();
                sanBiaoGouBean = JSON.parseObject(data, SanBiaoGouBean.class);

                for (int i=0;i < sanBiaoGouBean.getData().size(); i++){
                    dataBeanArrayList.add(sanBiaoGouBean.getData().get(i));
                }
                if(sanBiaoGouBean.getMaxPage() != 1) {
                    for(int i=1;i<sanBiaoGouBean.getMaxPage();i++){
                        RequestParams paramsNotice1 = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCBorrow_invest_list);
                        paramsNotice1.addBodyParameter("id", String.valueOf(id));
                        paramsNotice1.addBodyParameter("page",(i+1)+"");
                        paramsNotice.addBodyParameter("pagesize","10");
                        x.http().post(paramsNotice1, new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                String data = result;
                                Log.e("标", data);
                                Gson gson = new Gson();
                                sanBiaoGouBean = JSON.parseObject(data, SanBiaoGouBean.class);

                                for (int i=0;i < sanBiaoGouBean.getData().size(); i++){
                                    dataBeanArrayList.add(sanBiaoGouBean.getData().get(i));
                                }
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
                    }
                }
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
    }

    //更新数据
    private void updateSingleView(ArrayList<SanBiaoGouBean.DataBean> singModelArrayList) {
        ((AdapterJiLu)recyclerview_rec.getAdapter()).setDatas(singModelArrayList);
    }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }
}
