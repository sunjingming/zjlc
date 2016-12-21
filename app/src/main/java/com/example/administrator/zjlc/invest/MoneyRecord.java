package com.example.administrator.zjlc.invest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.cash.CashRecord;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.example.administrator.zjlc.userMessage.UserMail;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class MoneyRecord extends AppCompatActivity {

    private TextView tv_title;
    private Toolbar toolbar;
    private PullToRefreshListView money_record_list;
    private List<MoneyRecordBean.DataBean> listData = new ArrayList<MoneyRecordBean.DataBean>();
    private MoneyRecordAdapter adapter;
    private String token;
    private int page = 1;
    private int pageCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_record);
        initView();
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("资金记录");
        SharedPreferences fence = getSharedPreferences("usetoken", MODE_PRIVATE);
        token = fence.getString("token", "");
        loadData();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                loadData();
                money_record_list.setRefreshing();
                money_record_list.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        money_record_list.onRefreshComplete();
                    }
                }, 1000);

            }
        }, 500);


        //2实例化适配器
        adapter = new MoneyRecordAdapter(MoneyRecord.this, listData);
        //3设置适配器
        money_record_list.setAdapter(adapter);

        //4.设置刷新模式[上下拉都有]
        money_record_list.setMode(PullToRefreshBase.Mode.BOTH);
        money_record_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            /**
             * 下拉刷新
             * 作用:清空原数据，加载新数据
             * @param refreshView
             */
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //清空原数据
                listData.clear();
                //加载新数据
                loadData();
                page =1;
                if (page == pageCount) {
                    Toast.makeText(MoneyRecord.this, "数据已全部加载完毕", Toast.LENGTH_SHORT).show();
                }
                money_record_list.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        money_record_list.onRefreshComplete();
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
                if (page == pageCount) {
                    Toast.makeText(MoneyRecord.this, "数据已全部加载完毕", Toast.LENGTH_SHORT).show();
                    money_record_list.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            money_record_list.onRefreshComplete();
                        }
                    }, 1000);
                } else {
                    page++;
                    loadData();

                }
            }
        });

    }

    private void loadData() {
        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCMoney_record);
        params.addBodyParameter("token", token);
        params.addBodyParameter("page", page + "");
        params.addBodyParameter("pagesize", "5");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data资金记录", data);
                Gson gson = new Gson();
                MoneyRecordBean bean = gson.fromJson(data, MoneyRecordBean.class);
                pageCount = bean.getMaxPage();
                listData.addAll(bean.getData());
                adapter.notifyDataSetChanged();
                money_record_list.onRefreshComplete();


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
        tv_title = (TextView) findViewById(R.id.tv_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        money_record_list = (PullToRefreshListView) findViewById(R.id.money_record_list);
    }
}
