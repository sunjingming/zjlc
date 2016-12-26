package com.example.administrator.zjlc.pager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.activity.DetailsActivity;
import com.example.administrator.zjlc.domain.JsonRootBean;
import com.example.administrator.zjlc.domain.TJBBean;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.zjlc.R.drawable.selector_bg_btn_red_round;


/**
 * Created by Administrator on 2016/6/23.
 */
public class HomePager extends Fragment {

    private View view;
    private ViewPager viewpager;
    private ArrayList<View> dots;
    private List<String> jrb;
    private List<View> viewList;// 把需要滑动的页卡添加到这个list中
    private TextView tv_biao;
    private TextView tv_lv;
    private TextView tv_time;
    private TextView tv_ss;
    private TextView tv_jindu;
    private Button iv_lijitouzi;
    private TextView tv_title;
    private Toolbar toolbar;
    private SwipeRefreshLayout layout_swipe_refresh;

    private String id;

    private int oldPosition = 0;// 记录上一次点的位置

    private int currentItem; // 当前页面private int oldPosition = 0;// 记录上一次点的位置

    private ViewPagerAdapter viewPagerAdapter;


    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if(msg.what == 1) {
                //设置
                viewPagerAdapter = new ViewPagerAdapter();
                dots.get(0).setBackgroundResource(R.drawable.dot_focused);
                viewpager.setOnPageChangeListener(new MyPageChangeListener());
                viewpager.setAdapter(viewPagerAdapter);

                iv_lijitouzi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (id != null) {
                            Intent intent = new Intent(getActivity(), DetailsActivity.class);
                            intent.putExtra("id", id);
                            getActivity().startActivity(intent);
                        } else {
                            iv_lijitouzi.setBackgroundResource(R.drawable.selector_bg_btn_red_hui);
                        }
                    }
                });
            }else if(msg.what == 2){
                setViewpagerData();
                setViewText();
            }
            return false;
        }
    });


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.homepager, container, false);
        viewList = new ArrayList<View>();
        initView();
        tv_title.setText("卓金理财");
        return view;


    }


    /**
     * 1.在布局文件定义ViewPager
     * 2.在代码实例化ViewPager
     */
    private void initView() {
        layout_swipe_refresh = (SwipeRefreshLayout) view.findViewById(R.id.layout_swipe_refresh);
        //初始化组件
        tv_biao = (TextView) view.findViewById(R.id.tv_biao);
        tv_lv = (TextView) view.findViewById(R.id.tv_lv);
        tv_jindu = (TextView) view.findViewById(R.id.tv_jindu);
        tv_ss = (TextView) view.findViewById(R.id.tv_ss);
        tv_time = (TextView) view.findViewById(R.id.tv_time);
        iv_lijitouzi = (Button) view.findViewById(R.id.iv_lijitouzi);

        //代码实例化
        viewpager = (ViewPager) view.findViewById(R.id.viewpager_guide);
        dots = new ArrayList<View>();
        dots.add(view.findViewById(R.id.dot_1));
        dots.add(view.findViewById(R.id.dot_2));
        dots.add(view.findViewById(R.id.dot_3));
        dots.add(view.findViewById(R.id.dot_4));
        dots.add(view.findViewById(R.id.dot_5));
        //设置bannner图片
        setViewpagerData();
        //设置推荐标的详情
        setViewText();
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        layout_swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setViewpagerData();
                        setViewText();
                        // TODO Auto-generated method stub
                        layout_swipe_refresh.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    private void setViewText() {
        RequestParams requestparams = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCRecommend_list);
        x.http().post(requestparams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.e("data网站banner", data);
                Gson gson = new Gson();

                tv_biao.setText("暂无推荐项目");
                tv_lv.setText("0.00%");
                tv_time.setText("0天");
                tv_ss.setText("0.00元");
                tv_jindu.setText("0.00%");

                id=null;
                TJBBean tjbBean = gson.fromJson(data, TJBBean.class);
                if(tjbBean.getEvent() == 88){

                    id = tjbBean.getData().getId();
                    //获取到数据部署上去
                    tv_biao.setText(tjbBean.getData().getBorrow_name());
                    tv_lv.setText(tjbBean.getData().getBorrow_interest_rate() + "%");
                    tv_time.setText(tjbBean.getData().getBorrow_duration());
                    tv_ss.setText(tjbBean.getData().getBorrow_money()+"元");
                    tv_jindu.setText(tjbBean.getData().getProgress() + "%");
                    iv_lijitouzi.setBackgroundResource(R.drawable.selector_bg_btn_red_round);
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


    class MyPageChangeListener implements ViewPager.OnPageChangeListener {


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            position = position % 5;
            dots.get(oldPosition).setBackgroundResource(
                    R.drawable.dot_normal);
            dots.get(position)
                    .setBackgroundResource(R.drawable.dot_focused);

            oldPosition = position;
            currentItem = position;
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    //获取网络数据
    public void setViewpagerData() {
        RequestParams paramsNotice = new RequestParams(UrlsUtils.ZJLCstring + UrlsUtils.ZJLCBanner);
        x.http().post(paramsNotice, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.e("data网站banner", data);
                Gson gson = new Gson();
                JsonRootBean noticeBean = gson.fromJson(data, JsonRootBean.class);

                jrb = noticeBean.getData();

                for (int i = 0; i < jrb.size(); i++) {
                    ImageView imageView = new ImageView(getActivity());
                    Glide.with(getActivity()).load(jrb.get(i)).into(imageView);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    viewList.add(imageView);
                }
                handler.sendEmptyMessage(1);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("data网站banner", "onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("data网站banner", "onCancelled");

            }

            @Override
            public void onFinished() {
                Log.i("data网站banner", "onFinished");
            }
        });
    }

    //设置viewpager数据
    private class ViewPagerAdapter extends PagerAdapter {
        private List<View> mListViews;

        public ViewPagerAdapter() {
            super();
            // TODO Auto-generated constructor stub
            // 得到viewpager切换的三个布局，并把它们加入到viewList里面,记得view用打气筒生成，否则容易出现空指针异常
            this.mListViews = viewList;
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }


        // 是否是同一张图片
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup views, int position, Object object) {
            views.removeView(mListViews.get(position));

        }

        @Override
        public Object instantiateItem(ViewGroup views, int position) {
            views.addView(mListViews.get(position));
            return mListViews.get(position);
        }
    }

    @Override
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
