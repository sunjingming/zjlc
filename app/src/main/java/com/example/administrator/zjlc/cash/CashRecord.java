package com.example.administrator.zjlc.cash;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.service.carrier.CarrierService;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.invest.RedPacket;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.example.administrator.zjlc.userMessage.UserMail;
import com.example.administrator.zjlc.userMessage.UserMailAdapter;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class CashRecord extends AppCompatActivity {

    private TextView tv_title;
    private Toolbar toolbar;
    private TextView empty;
    private PullToRefreshListView listView;
    private String token;
    private int page = 1;
    private int pageCount = 1;
    private List<CashRecordBean.DataBean> beanList = new ArrayList<>();
    private CashRecordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_record);

        initView();

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("提现记录");


        SharedPreferences preferences = getSharedPreferences("usetoken", MODE_APPEND);
        token = preferences.getString("token", null);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                listView.setRefreshing();
                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.onRefreshComplete();
                        }
                    }, 1000);


            }
        }, 500);

        listView.setEmptyView(empty);

        //2实例化适配器
        adapter = new CashRecordAdapter(CashRecord.this, beanList);
        //3设置适配器
        listView.setAdapter(adapter);
        //4.设置刷新模式[上下拉都有]
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            /**
             * 下拉刷新
             * 作用:清空原数据，加载新数据
             * @param refreshView
             */
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //清空原数据
                beanList.clear();
                //加载新数据
                loadData();
                page =1;
                if (page == pageCount) {
                    Toast.makeText(CashRecord.this, "数据已全部加载完毕", Toast.LENGTH_SHORT).show();
                }
                listView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listView.onRefreshComplete();
                    }
                }, 1000);
            }

            /**
             * 上拉刷新
             * 作用：添加下一页数据
             * @param refreshView
             */
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (page==pageCount){
                    Toast.makeText(CashRecord.this, "数据已全部加载完毕", Toast.LENGTH_SHORT).show();
                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listView.onRefreshComplete();
                        }
                    }, 1000);
                }else {
                    page++;
                    loadData();

                }
            }
        });

    }

    private void loadData() {
        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCCashing_record);
        params.addBodyParameter("token",token);
        params.addBodyParameter("page",page+"");
        params.addBodyParameter("pagesize","5");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data体现记录",data);
                Gson gson  = new Gson();
                CashRecordBean recordBean = gson.fromJson(data,CashRecordBean.class);
                pageCount = recordBean.getMaxPage();
                beanList.addAll(recordBean.getData());
                adapter.notifyDataSetChanged();
                //5刷新完成,隐藏刷新进度条
                listView.onRefreshComplete();


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
        empty = (TextView) findViewById(R.id.cash_record_empty);
        tv_title = (TextView) findViewById(R.id.tv_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (PullToRefreshListView) findViewById(R.id.cash_record_list);
    }
}
