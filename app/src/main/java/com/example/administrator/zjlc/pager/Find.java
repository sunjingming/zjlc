package com.example.administrator.zjlc.pager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.activity.DetailsActivity;
import com.example.administrator.zjlc.activity.ZQZRActivity;
import com.example.administrator.zjlc.adapter.AddressSpacesItemDecoration;
import com.example.administrator.zjlc.adapter.EndLessOnScrollListener;
import com.example.administrator.zjlc.adapter.MyRecyclerView;
import com.example.administrator.zjlc.adapter.MyRecyclerView2;
import com.example.administrator.zjlc.domain.SanBiaobean;
import com.example.administrator.zjlc.domain.ZQZLbean;
import com.example.administrator.zjlc.login.Login;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/23.
 */
public class Find extends Fragment {
    private RecyclerView recyclerView,recyclerView1;
    private MyRecyclerView myRecyclerView;
    private MyRecyclerView2 myRecyclerView2;
    private ArrayList<ZQZLbean.DataBean> zqzLbeen;
    private ArrayList<SanBiaobean.DataBean> dataBeanArrayList;
    private ZQZLbean zqzLbeans;
    private SanBiaobean sanBiaobean;
    private View view;
    private Button but1;
    private Button but2;
    private SwipeRefreshLayout mRefreshLayout,mRefreshLayout1;
    private LinearLayout ll_llsys,ll_llsys1;
    private int lastVisibleItem;
    private int lastVisibleItem1;

    private Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    updateSingleView(dataBeanArrayList);
                    updateSingleView1(zqzLbeen);
                    break;
                case 2:
                    dataBeanArrayList.clear();
                    zqzLbeen.clear();
                    setData();

                    updateSingleView(dataBeanArrayList);
                    updateSingleView1(zqzLbeen);

                    recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener(){
                        @Override
                        public void onScrollStateChanged(RecyclerView recyclerView, final int newState) {
                            super.onScrollStateChanged(recyclerView, newState);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (newState ==RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 ==myRecyclerView.getItemCount()) {
                                        if(sanBiaobean.getCurrentPage() >= sanBiaobean.getMaxPage()){
                                            myRecyclerView.changeMoreStatus(MyRecyclerView.NO_MORE_DATA);
                                        }
                                        loadMoreData();
                                    }
                                }
                            },1000);
                        }
                        @Override
                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView,dx, dy);
                            lastVisibleItem =layoutManager.findLastVisibleItemPosition();
                        }
                    });
                    recyclerView1.setOnScrollListener(new RecyclerView.OnScrollListener(){
                        @Override
                        public void onScrollStateChanged(RecyclerView recyclerView, final int newState) {
                            super.onScrollStateChanged(recyclerView, newState);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (newState ==RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem1 + 1 ==myRecyclerView2.getItemCount()) {
                                        Log.e("asd",zqzLbeans.getCurrentPage()+""+zqzLbeans.getMaxPage());
                                        if(zqzLbeans.getCurrentPage() >= zqzLbeans.getMaxPage()){
                                            myRecyclerView2.changeMoreStatus(MyRecyclerView2.NO_MORE_DATA);
                                        }
                                        Log.e("asd","zhege");
                                        loadMoreData2();
                                    }
                                }
                            },1000);
                        }
                        @Override
                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView,dx, dy);
                            lastVisibleItem1 =layoutManager1.findLastVisibleItemPosition();
                        }
                    });
                    break;

            }

            return false;
        }
    });
    private TextView tv_title;
    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mepager, container, false);
        initView();
        tv_title.setText("理财");


        return view;

    }
    LinearLayoutManager layoutManager;
    LinearLayoutManager layoutManager1;
    /**
     * 3.2,至少要实现4个方法
     */
    private void initView() {
        //实现上拉刷新
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.layout_swipe_refresh);
        mRefreshLayout1 = (SwipeRefreshLayout) view.findViewById(R.id.layout_swipe_refresh1);
        //初始化列表
        recyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);
        recyclerView1 = (RecyclerView) view.findViewById(R.id.id_recyclerview1);

        ll_llsys = (LinearLayout) view.findViewById(R.id.ll_llsys);
        ll_llsys1 = (LinearLayout) view.findViewById(R.id.ll_llsys1);

        dataBeanArrayList = new ArrayList<SanBiaobean.DataBean>();
        zqzLbeen = new ArrayList<ZQZLbean.DataBean>();
        but1 = (Button) view.findViewById(R.id.but1);
        but2 = (Button) view.findViewById(R.id.but2);
        but1.setBackgroundResource(R.drawable.yuanshi);
        but1.setTextColor(Color.WHITE);

//        setData();
//        dataBeanArrayList.clear();
//        zqzLbeen.clear();
//        setData();
//        Log.e("数据初始化","数据初始化");
        myRecyclerView = new MyRecyclerView(getActivity(), dataBeanArrayList);
        myRecyclerView2 = new MyRecyclerView2(getActivity(), zqzLbeen);

        recyclerView.getParent().requestDisallowInterceptTouchEvent(false);
        recyclerView1.getParent().requestDisallowInterceptTouchEvent(false);

        recyclerView.addItemDecoration(new AddressSpacesItemDecoration(20, 20, 20, 20));
        recyclerView1.addItemDecoration(new AddressSpacesItemDecoration(20, 20, 20, 20));
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayout.VERTICAL);//默认是LinearLayout.VERTICAL
        layoutManager1 = new LinearLayoutManager(getActivity());
        layoutManager1.setOrientation(LinearLayout.VERTICAL);//默认是LinearLayout.VERTICAL
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        recyclerView1.setLayoutManager(layoutManager1);
        //设置Adapter
        recyclerView.setAdapter(myRecyclerView);
        recyclerView1.setAdapter(myRecyclerView2);
        myRecyclerView.setOnItemClickListener(new MyRecyclerView.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, SanBiaobean.DataBean data) {
                SharedPreferences prence = getActivity().getSharedPreferences("usetoken", getActivity().MODE_PRIVATE);
                String token = prence.getString("token", "");
                Log.e("cuo", token);
                if (token.equals("")) {
                    Intent intent = new Intent(getActivity(), Login.class);
                    getActivity().startActivity(intent);
                } else {

                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    intent.putExtra("id", data.getId() + "");
                    getActivity().startActivity(intent);
                }


            }
        });
        handler.post(runnable);
        //下拉刷新
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dataBeanArrayList.clear();
                        setData();
                        updateSingleView(dataBeanArrayList);
                        // TODO Auto-generated method stub
                        mRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
//                //上拉加载更多
//                recyclerView.addOnScrollListener(new EndLessOnScrollListener(layoutManager) {
//                    @Override
//                    public void onLoadMore(int currentPage) {
//                        loadMoreData();
//                    }
//                });
            }
        });
        //下拉刷新
        mRefreshLayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        zqzLbeen.clear();
                        setData();
                        updateSingleView1(zqzLbeen);
                        // TODO Auto-generated method stub
                        mRefreshLayout1.setRefreshing(false);
//                        recyclerView1.addOnScrollListener(new EndLessOnScrollListener(layoutManager) {
//                            @Override
//                            public void onLoadMore(int currentPage) {
//                                loadMoreData2();
//                            }
//                        });
                    }
                }, 1000);
            }
        });
        //上拉加载更多
//        recyclerView.addOnScrollListener(new EndLessOnScrollListener(layoutManager) {
//            @Override
//            public void onLoadMore(int currentPage) {
//                loadMoreData();
//            }
//        });
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //设置按钮切换
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                but2.setBackgroundResource(R.drawable.rightyuan);
                but2.setTextColor(Color.RED);
                but1.setBackgroundResource(R.drawable.yuanshi);
                but1.setTextColor(Color.WHITE);

                ll_llsys.setVisibility(View.VISIBLE);
                ll_llsys1.setVisibility(View.GONE);
                //设置Adapter
                recyclerView.setAdapter(myRecyclerView);
                myRecyclerView.setOnItemClickListener(new MyRecyclerView.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, SanBiaobean.DataBean data) {
                        SharedPreferences prence = getActivity().getSharedPreferences("usetoken", getActivity().MODE_PRIVATE);
                        String token = prence.getString("token", "");
                        Log.e("cuo", token);
                        if (token.equals("")) {
                            Intent intent = new Intent(getActivity(), Login.class);
                            getActivity().startActivity(intent);
                        } else {

                            Intent intent = new Intent(getActivity(), DetailsActivity.class);
                            intent.putExtra("id", data.getId() + "");
                            getActivity().startActivity(intent);
                        }
                    }
                });

//                recyclerView1.addOnScrollListener(new EndLessOnScrollListener(layoutManager) {
//                    @Override
//                    public void onLoadMore(int currentPage) {
//                        loadMoreData();
//                    }
//                });
            }
        });
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                but1.setBackgroundResource(R.drawable.yuanjiao);
                but1.setTextColor(Color.RED);
                but2.setBackgroundResource(R.drawable.yuanrightshi);
                but2.setTextColor(Color.WHITE);

                ll_llsys1.setVisibility(View.VISIBLE);
                ll_llsys.setVisibility(View.GONE);
                //设置Adapter
                recyclerView1.setAdapter(myRecyclerView2);

//                recyclerView1.addOnScrollListener(new EndLessOnScrollListener(layoutManager) {
//                    @Override
//                    public void onLoadMore(int currentPage) {
//                        loadMoreData2();
//                    }
//                });
                myRecyclerView2.setOnItemClickListener(new MyRecyclerView2.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, ZQZLbean.DataBean data) {
                        SharedPreferences prence = getActivity().getSharedPreferences("usetoken", getActivity().MODE_PRIVATE);
                        String token = prence.getString("token", "");
                        Log.e("cuo", token);
                        if (token.equals("")) {
                            Intent intent = new Intent(getActivity(), Login.class);
                            getActivity().startActivity(intent);
                        } else {
                            Intent intent = new Intent(getActivity(), ZQZRActivity.class);
                            intent.putExtra("id", data.getDebt_id());
                            getActivity().startActivity(intent);
                        }
                    }
                });

            }
        });

        tv_title = (TextView) view.findViewById(R.id.tv_title);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
    }

    private void setData() {
        sanBiaobean = new SanBiaobean();
        RequestParams paramsNotice = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCBorrow_list);
        x.http().post(paramsNotice, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.e("标", data);
                Gson gson = new Gson();

                dataBeanArrayList.clear();
                sanBiaobean = JSON.parseObject(data, SanBiaobean.class);
                if(sanBiaobean.getEvent()!=0) {
                    for (int i = 0; i < sanBiaobean.getData().size(); i++) {
                        Log.e("标", String.valueOf(sanBiaobean.getData().get(i)));
                        dataBeanArrayList.add(sanBiaobean.getData().get(i));
                    }
                    myRecyclerView.notifyDataSetChanged();
//                    handler.sendEmptyMessage(1);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("标", "onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("标", "onCancelled");

            }

            @Override
            public void onFinished() {
                Log.i("标", "onFinished");
            }
        });
        zqzLbeans = new ZQZLbean();
        RequestParams paramsNotice2 = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCDebt_list);
        paramsNotice2.addBodyParameter("page","1");
        paramsNotice2.addBodyParameter("pagesize","6");
        x.http().post(paramsNotice2, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data1 = result;
                Log.e("标", data1);
                Gson gson = new Gson();
                zqzLbeen.clear();
                zqzLbeans = JSON.parseObject(data1, ZQZLbean.class);
                if (zqzLbeans.getEvent()!=0) {
                    for (int i = 0; i < zqzLbeans.getData().size(); i++) {
                        Log.e("标", String.valueOf(zqzLbeans.getData().get(i)));
                        zqzLbeen.add(zqzLbeans.getData().get(i));
                    }
                    myRecyclerView2.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("标", "onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("标", "onCancelled");

            }

            @Override
            public void onFinished() {
                Log.i("标", "onFinished");

            }
        });
//上拉刷新
//        recyclerView.addOnScrollListener(new EndLessOnScrollListener(layoutManager) {
//            @Override
//            public void onLoadMore(int currentPage) {
//                loadMoreData();
//            }
//        });
//        recyclerView1.addOnScrollListener(new EndLessOnScrollListener(layoutManager) {
//            @Override
//            public void onLoadMore(int currentPage) {
//                loadMoreData2();
//            }
//        });


    }

    //散标更新数据
    private void updateSingleView(ArrayList<SanBiaobean.DataBean> singModelArrayListn) {
        if (singModelArrayListn != null && singModelArrayListn.size() != 0 ) {
            ((MyRecyclerView) recyclerView.getAdapter()).setDatas(singModelArrayListn);
        }
    }
    //债权更新数据

    private void updateSingleView1( ArrayList<ZQZLbean.DataBean> sin) {
        if (sin != null && sin.size() != 0 || sin != null && sin.size() != 0) {
            ((MyRecyclerView2) recyclerView1.getAdapter()).setDatas(sin);
        }
    }
    //上拉刷新
    //每次上拉加载的时候，给RecyclerView的后面添加了10条数据数据
    //散标列表刷新

    private void loadMoreData(){

        RequestParams paramsNotice = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCBorrow_list);
        paramsNotice.addBodyParameter("page", String.valueOf(sanBiaobean.getCurrentPage()+1));
        paramsNotice.addBodyParameter("pagesize", String.valueOf(6));
        x.http().post(paramsNotice, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.e("标", data);
                Gson gson = new Gson();

                sanBiaobean = JSON.parseObject(data, SanBiaobean.class);

                for (int i = 0; i < sanBiaobean.getData().size(); i++) {
                    Log.e("标", String.valueOf(sanBiaobean.getData().get(i)));
                    dataBeanArrayList.add(sanBiaobean.getData().get(i));
                }

                myRecyclerView.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("标", "onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("标", "onCancelled");

            }

            @Override
            public void onFinished() {
                Log.i("标", "onFinished");
            }
        });
    }

    private void loadMoreData2(){

        RequestParams paramsNotice2 = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCDebt_list);

        paramsNotice2.addBodyParameter("page", String.valueOf(zqzLbeans.getCurrentPage()+1));
        paramsNotice2.addBodyParameter("pagesize", String.valueOf(6));
        x.http().post(paramsNotice2, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data1 = result;
                Log.e("标", data1);
                Gson gson = new Gson();

                zqzLbeans = JSON.parseObject(data1, ZQZLbean.class);

                for (int i = 0; i < zqzLbeans.getData().size(); i++) {
                    Log.e("标", String.valueOf(zqzLbeans.getData().get(i)));
                    zqzLbeen.add(zqzLbeans.getData().get(i));
                }

                myRecyclerView2.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("标", "onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("标", "onCancelled");

            }

            @Override
            public void onFinished() {
                Log.i("标", "onFinished");
            }
        });
    }

    //    @Override
    public void onResume() {
        super.onResume();

        handler.post(runnable);
    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.sendEmptyMessage(2);
        }
    };
}
