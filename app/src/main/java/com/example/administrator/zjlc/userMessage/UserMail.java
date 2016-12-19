package com.example.administrator.zjlc.userMessage;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.bank.ProvinceAdapter;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class UserMail extends AppCompatActivity {

    private TextView tv_title;
    private Toolbar toolbar;
    private int page = 1;
    private int pageCount = 1;
    private PullToRefreshListView listView;
    private List<UserMailBean.DataBean> beanList = new ArrayList<>();
    private UserMailAdapter adapter;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_mail);
        initView();

        SharedPreferences fence = getSharedPreferences("usetoken", MODE_PRIVATE);
        token = fence.getString("token", "");

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title.setText("站内信");

        new Handler().postDelayed(new Runnable(){
            public void run(){
                listView.setRefreshing();
                loadData();
            }
        },500);

        //2实例化适配器
        adapter = new UserMailAdapter(UserMail.this, beanList);
        //3设置适配器
        listView.setAdapter(adapter);
        //2实例化适配器
        adapter = new UserMailAdapter(UserMail.this, beanList);
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
                page = 1;
                //加载新数据
                loadData();
            }

            /**
             * 上拉刷新
             * 作用：添加下一页数据
             * @param refreshView
             */
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (page==pageCount){
                    Toast.makeText(UserMail.this, "数据已全部加载完毕", Toast.LENGTH_SHORT).show();
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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int i, long id) {
                TextView mailId = (TextView) view.findViewById(R.id.user_mail_status);
                    RequestParams params = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCInner_status);
                    params.addBodyParameter("token",token);
                    params.addBodyParameter("id",mailId.getText().toString());
                    x.http().post(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            String data = result;
                            Log.i("data站内信状态",data);
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
//
                AlertDialog dialog = new AlertDialog.Builder(UserMail.this).setTitle("消息提示").setMessage(beanList.get(i).getMsg()).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView title = (TextView) view.findViewById(R.id.user_mail_title);
                        title.setTextColor(Color.GRAY);

                    }
                }).show();
            }
        });

    }

    private void loadData() {
        //获取站内信
        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCMail);
        params.addBodyParameter("token", token);
        params.addBodyParameter("page",page+"");
        params.addBodyParameter("pagesize","3");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data站内信", data);
                Gson gson = new Gson();
                UserMailBean mailbean = gson.fromJson(data, UserMailBean.class);
                pageCount = mailbean.getMaxPage();
                beanList.addAll(mailbean.getData());
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
        tv_title = (TextView) findViewById(R.id.tv_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (PullToRefreshListView) findViewById(R.id.user_mail);
    }
}
