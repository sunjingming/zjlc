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
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class RedPacket extends AppCompatActivity {
    private TextView tv_title;
    private Toolbar toolbar;
    private PullToRefreshListView red_packet_list;
    private String token;
    private RedPacketAdapter adapter;
    private int page=1;
    private int pageCount=1;
    private List<RedPacketBean.DataBean>listData  = new ArrayList<>();
    //特权金页面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_packet);
        initView();

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("我的特权金");

        SharedPreferences fence = getSharedPreferences("usetoken", MODE_PRIVATE);
        token = fence.getString("token", "");

        new Handler().postDelayed(new Runnable() {
            public void run() {
                loadData();
                red_packet_list.setRefreshing();
                red_packet_list.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        red_packet_list.onRefreshComplete();
                    }
                }, 1000);

            }
        }, 500);

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //2实例化适配器
        adapter=new RedPacketAdapter(RedPacket.this,listData);
        //3设置适配器
        red_packet_list.setAdapter(adapter);

        //4.设置刷新模式[上下拉都有]
        red_packet_list.setMode(PullToRefreshBase.Mode.BOTH);
        red_packet_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
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
                    Toast.makeText(RedPacket.this, "数据已全部加载完毕", Toast.LENGTH_SHORT).show();
                }
                red_packet_list.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        red_packet_list.onRefreshComplete();
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
                    Toast.makeText(RedPacket.this, "数据已全部加载完毕", Toast.LENGTH_SHORT).show();
                    red_packet_list.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            red_packet_list.onRefreshComplete();
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

        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCRed_packet);
        params.addBodyParameter("token",token);
        params.addBodyParameter("page",page+"");
        params.addBodyParameter("pagesize","5");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data特权金",data);
                Gson gson = new Gson();
                RedPacketBean bean  = gson.fromJson(data,RedPacketBean.class);
                pageCount = bean.getMaxPage();
                listData.addAll(bean.getData());
                adapter.notifyDataSetChanged();
                red_packet_list.onRefreshComplete();
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
        red_packet_list = (PullToRefreshListView) findViewById(R.id.red_packet_list);
    }
}
