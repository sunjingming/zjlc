package com.example.administrator.zjlc.invest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zjlc.R;
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

/*
* 待回款投资界面
*
* */
public class WaitRecover extends Fragment {
    private View view;
    private TextView textEmpty;
    private PullToRefreshListView wait_recover_list;
    private String token;
    private List<WaitRecoverBean.DataBean>listData = new ArrayList<>();
    private WaitRecoverAdapter adapter;
    private int page = 1;
    private int pageCount = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_wait_recover, container, false);
        initView(view);
        SharedPreferences fence = getActivity().getSharedPreferences("usetoken", getActivity().MODE_PRIVATE);
        token = fence.getString("token", "");



        new Handler().postDelayed(new Runnable() {
            public void run() {
                wait_recover_list.setRefreshing();
                    wait_recover_list.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            wait_recover_list.onRefreshComplete();
                        }
                    }, 1000);

            }
        }, 500);

        wait_recover_list.setEmptyView(textEmpty);


        //2实例化适配器
        adapter=new WaitRecoverAdapter(getActivity(),listData);
        //3设置适配器
        wait_recover_list.setAdapter(adapter);

        //4.设置刷新模式[上下拉都有]
        wait_recover_list.setMode(PullToRefreshBase.Mode.BOTH);
        wait_recover_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            /**
             * 下拉刷新
             * 作用:清空原数据，加载新数据
             * @param refreshView
             */
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //清空原数据
                listData.clear();
                loadData();
                page =1;
                if (page == pageCount) {
                    Toast.makeText(getActivity(), "数据已全部加载完毕", Toast.LENGTH_SHORT).show();
                }
                wait_recover_list.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        wait_recover_list.onRefreshComplete();
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
                    Toast.makeText(getActivity(), "数据已全部加载完毕", Toast.LENGTH_SHORT).show();
                    wait_recover_list.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            wait_recover_list.onRefreshComplete();
                        }
                    }, 1000);
                } else {
                    page++;
                    loadData();

                }

            }
        });

        return view;
    }
    private void loadData() {
        RequestParams params = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCInvest_record);
        params.addBodyParameter("token",token);
        params.addBodyParameter("type","1");
        params.addBodyParameter("page", page + "");
        params.addBodyParameter("pagesize", "5");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.i("data待回款",data);
                Gson gson = new Gson();
                WaitRecoverBean bean = gson.fromJson(data,WaitRecoverBean.class);
                listData.addAll(bean.getData());
                adapter.notifyDataSetChanged();
                pageCount = bean.getMaxPage();
                wait_recover_list.onRefreshComplete();
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

    private void initView(View view) {
        textEmpty = (TextView) view.findViewById(R.id.wait_recover_text);
        wait_recover_list = (PullToRefreshListView) view.findViewById(R.id.wait_recover_list);
    }


}
