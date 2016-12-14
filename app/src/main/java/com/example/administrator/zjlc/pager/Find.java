package com.example.administrator.zjlc.pager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
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
import com.example.administrator.zjlc.activity.DetailsActivity;
import com.example.administrator.zjlc.activity.ZQZRActivity;
import com.example.administrator.zjlc.adapter.AddressSpacesItemDecoration;
import com.example.administrator.zjlc.adapter.MyRecyclerView;
import com.example.administrator.zjlc.adapter.MyRecyclerView2;
import com.example.administrator.zjlc.base.BasePager;
import com.example.administrator.zjlc.domain.SanBiaoGouBean;
import com.example.administrator.zjlc.domain.SanBiaobean;
import com.example.administrator.zjlc.domain.ZQZLbean;
import com.example.administrator.zjlc.login.Login;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.example.administrator.zjlc.view.MyScrollView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/23.
 */
public class Find extends BasePager {
    private RecyclerView recyclerView;
    private MyRecyclerView myRecyclerView;
    private MyRecyclerView2 myRecyclerView2;
    private ArrayList<ZQZLbean.DataBean> zqzLbeen;
    private ArrayList<SanBiaobean.DataBean> dataBeanArrayList;
    private ZQZLbean zqzLbeans;
    private SanBiaobean sanBiaobean;
    private View view;
    private Button but1;
    private Button but2;



    private Handler handler = new Handler(new Handler.Callback(){

        @Override
        public boolean handleMessage(Message msg) {
            updateSingleView(dataBeanArrayList,zqzLbeen);
            return false;
        }
    });

    public Find(Activity activity) {
        super(activity);
    }
    @Override
    public void initData() {
        super.initData();
        System.out.println("我的数据被初始化了...");
        //设置标题
        view = View.inflate(mActivity, R.layout.mepager, null);
        initView();
        fl_basepager_content.removeAllViews();
        fl_basepager_content.addView(view);
    }

    /**
     * 3.2,至少要实现4个方法
     */
    private void initView() {
        //初始化列表
        recyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);
        dataBeanArrayList = new ArrayList<SanBiaobean.DataBean>();
        zqzLbeen = new ArrayList<ZQZLbean.DataBean>();
        but1 = (Button) view.findViewById(R.id.but1);
        but2 = (Button) view.findViewById(R.id.but2);
        but1.setBackgroundResource(R.drawable.yuanshi);
        but1.setTextColor(Color.WHITE);

        tv_pasnt.setText("理财");
        setData();



        myRecyclerView= new MyRecyclerView(mActivity , dataBeanArrayList );
        myRecyclerView2= new MyRecyclerView2(mActivity , zqzLbeen);

        recyclerView.getParent().requestDisallowInterceptTouchEvent(false);


        recyclerView.addItemDecoration(new AddressSpacesItemDecoration(20, 20, 20, 20));
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayout.VERTICAL);//默认是LinearLayout.VERTICAL
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        //设置Adapter
        recyclerView.setAdapter(myRecyclerView);
        myRecyclerView.setOnItemClickListener(new MyRecyclerView.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, SanBiaobean.DataBean data) {
                SharedPreferences prence = mActivity.getSharedPreferences("usetoken", mActivity.MODE_PRIVATE);
                String token = prence.getString("token","");
                Log.e("cuo",token);
                if(token.equals("")){
                    Intent intent = new Intent(mActivity , Login.class);
                    mActivity.startActivity(intent);
                }else{

                    Intent intent = new Intent(mActivity , DetailsActivity.class);
                    intent.putExtra("id",data.getId()+"");
                    mActivity.startActivity(intent);
                }
            }
        });
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


                //设置Adapter
                recyclerView.setAdapter(myRecyclerView);
                myRecyclerView.setOnItemClickListener(new MyRecyclerView.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, SanBiaobean.DataBean data) {
                        SharedPreferences prence = mActivity.getSharedPreferences("usetoken", mActivity.MODE_PRIVATE);
                        String token = prence.getString("token","");
                        Log.e("cuo",token);
                        if(token.equals("")){
                            Intent intent = new Intent(mActivity , Login.class);
                            mActivity.startActivity(intent);
                        }else{

                            Intent intent = new Intent(mActivity , DetailsActivity.class);
                            intent.putExtra("id",data.getId()+"");
                            mActivity.startActivity(intent);
                        }
                    }
                });
            }
        });
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                but1.setBackgroundResource(R.drawable.yuanjiao);
                but1.setTextColor(Color.RED);
                but2.setBackgroundResource(R.drawable.yuanrightshi);
                but2.setTextColor(Color.WHITE);
                //设置Adapter
                recyclerView.setAdapter(myRecyclerView2);
                myRecyclerView2.setOnItemClickListener(new MyRecyclerView2.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, ZQZLbean.DataBean data) {
                        SharedPreferences prence = mActivity.getSharedPreferences("usetoken", mActivity.MODE_PRIVATE);
                        String token = prence.getString("token","");
                        Log.e("cuo",token);
                        if(token.equals("")){
                            Intent intent = new Intent(mActivity , Login.class);
                            mActivity.startActivity(intent);
                        }else{
                            Intent intent = new Intent(mActivity , ZQZRActivity.class);
                            intent.putExtra("id",data.getDebt_id());
                            mActivity.startActivity(intent);
                        }
                    }
                });
            }
        });

    }

    private void setData() {
        RequestParams paramsNotice = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCBorrow_list);
        x.http().post(paramsNotice, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.e("标", data);
                Gson gson = new Gson();
                sanBiaobean = gson.fromJson(data, SanBiaobean.class);

                for(int i = 0; i < sanBiaobean.getData().size() ; i++){
                    Log.e("标", String.valueOf(sanBiaobean.getData().get(i)));
                    dataBeanArrayList.add(sanBiaobean.getData().get(i));
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

        RequestParams paramsNotice2 = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCDebt_list);
        x.http().post(paramsNotice2, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data1 = result;
                Log.e("标", data1);
                Gson gson = new Gson();

                zqzLbeans = JSON.parseObject(data1, ZQZLbean.class);

                for(int i = 0; i < zqzLbeans.getData().size() ; i++){
                    Log.e("标", String.valueOf(zqzLbeans.getData().get(i)));
                    zqzLbeen.add(zqzLbeans.getData().get(i));
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
                Log.i("标", "onFinished");
                handler.sendEmptyMessage(1);
            }
        });

    }

    //更新数据
    private void updateSingleView(ArrayList<SanBiaobean.DataBean> singModelArrayList,ArrayList<ZQZLbean.DataBean> sin) {
        if(singModelArrayList != null && singModelArrayList.size() != 0 || sin != null && sin.size() != 0){
            ((MyRecyclerView)recyclerView.getAdapter()).setDatas(singModelArrayList);
        }
    }

}
