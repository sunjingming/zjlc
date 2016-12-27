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
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.activity.DetailsActivity;
import com.example.administrator.zjlc.activity.GuideActivity;
import com.example.administrator.zjlc.domain.JsonRootBean;
import com.example.administrator.zjlc.domain.TJBBean;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
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
    //    private ArrayList<View> dots;
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
    private LinearLayout ll_layout1;
    private ImageView iv_red_point;

    private String id;

    private int oldPosition = 0;// 记录上一次点的位置

    private int currentItem = 0; // 当前页面private int oldPosition = 0;// 记录上一次点的位置

    private ViewPagerAdapter viewPagerAdapter;


    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if(msg.what == 1) {
                //View显示加载过程中测量onMearsure()-->指定位置和大小onLayout->绘制onDraw();
                iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());
                //设置
                viewPagerAdapter = new ViewPagerAdapter();

//                dots.get(0).setBackgroundResource(R.drawable.dot_focused);
//                viewpager.setOnPageChangeListener(new MyOnPageChangeListener());
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
    private int pointwidth;
    /**
     * 两点间的距离
     */
    private  int margLeft;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.homepager, container, false);
        viewList = new ArrayList<View>();
        initView();
        pointwidth = DensityUtil.dip2px(10);
        Log.e("tag","pointwidth=="+pointwidth);
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
        ll_layout1 = (LinearLayout) view.findViewById(R.id.ll_layout1);
        iv_red_point = (ImageView) view.findViewById(R.id.iv_red_point);

        //代码实例化
        viewpager = (ViewPager) view.findViewById(R.id.viewpager_guide);
        viewpager.setOnPageChangeListener(new MyOnPageChangeListener());



//        dots = new ArrayList<View>();
//        dots.add(view.findViewById(R.id.dot_1));
//        dots.add(view.findViewById(R.id.dot_2));
//        dots.add(view.findViewById(R.id.dot_3));
//        dots.add(view.findViewById(R.id.dot_4));
//        dots.add(view.findViewById(R.id.dot_5));
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
    private int prePosition;
    class MyOnPageChangeListener implements  ViewPager.OnPageChangeListener{

        /**
         * 页面滚动的时候被回调
         * @param position 当前滑动页面的位置
         * @param positionOffset 屏幕移动的百分比
         * @param positionOffsetPixels 在屏幕上滑动的像素
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.e("asd","==position=="+position+",positionOffset=="+positionOffset+",positionOffsetPixels=="+positionOffsetPixels);

            //红点在两点间移动的距离 = 屏幕的移动的百分比* 间距
//            float maxLeft = positionOffset*margLeft;

            //红点在屏幕上移动的坐标 = 起始坐标+ 红点在两点间移动的距离
//            maxLeft = (position*margLeft)+positionOffset*margLeft;
            float   maxLeft = (position+positionOffset)*margLeft;//简写
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(pointwidth,pointwidth);
            params.leftMargin = (int) maxLeft;
//            iv_red_point.setLayoutParams(params);
        }

        //当某个页面被选中的时候被回调
        //被选中页面的位置position
        @Override
        public void onPageSelected(int position) {
           if(position==jrb.size()+1){    //当切换到最后一个页面时currentPosition设置为第一个位置，小圆点位置为0
                currentItem=1;
                oldPosition=0;
            }else{
                currentItem=position+1;
                oldPosition=position;
            }
            //  把之前的小圆点设置背景为暗红，当前小圆点设置为红色
            ll.get(prePosition).setBackgroundResource(R.drawable.normal_point);
            ll.get(oldPosition).setBackgroundResource(R.drawable.normal_point_red);
            prePosition=oldPosition;
//            if(position==viewList.size()-1){
//                //最后一个页面把按钮显示出来
//                btn_start_main.setVisibility(View.VISIBLE);
//            }else{
//                //隐藏按钮
//                btn_start_main.setVisibility(View.GONE);
//            }

        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    //    class MyPageChangeListener implements ViewPager.OnPageChangeListener {
//
//
//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            position = position % 5;
//            dots.get(oldPosition).setBackgroundResource(
//                    R.drawable.dot_normal);
//            dots.get(position)
//                    .setBackgroundResource(R.drawable.dot_focused);
//
//            oldPosition = position;
//            currentItem = position;
//        }
//
//        @Override
//        public void onPageSelected(int position) {
//
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int state) {
//
//        }
//    }
    ArrayList<ImageView> ll = new ArrayList<ImageView>();
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
                if(jrb!=null){
                    jrb.clear();
                }
                jrb = noticeBean.getData();
                viewList.clear();
                for (int i = 0; i < jrb.size(); i++) {
                    ImageView imageView = new ImageView(getActivity());
                    Glide.with(getActivity()).load(jrb.get(i)).into(imageView);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    viewList.add(imageView);
                }
                ll_layout1.removeAllViews();

                ll.clear();
                for(int i=0;i<jrb.size();i++){
                    //添加灰色的点
                    ImageView iv_normal_point = new ImageView(getActivity());
                    //设置灰色的背景
                    iv_normal_point.setBackgroundResource(R.drawable.normal_point);
                    //把它转化成对应的像素
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pointwidth,pointwidth);//像素px
                    if(i !=0){
                        params.leftMargin = pointwidth;
                    }
                    iv_normal_point.setLayoutParams(params);
                    ll.add(iv_normal_point);
//                    View view = View.inflate(getActivity(),R.layout.views,null);
//                    dots.add(view);
                    ll_layout1.addView(iv_normal_point);
                }

                ll.get(currentItem).setBackgroundResource(R.drawable.normal_point_red);
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
    class MyOnGlobalLayoutListener implements  ViewTreeObserver.OnGlobalLayoutListener{

        @Override
        public void onGlobalLayout() {

            iv_red_point.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            //间距=第1个点的距离左边的距离-第0个点的距离左边的距离
            margLeft =  ll_layout1.getChildAt(1).getLeft() -ll_layout1.getChildAt(0).getLeft();
            Log.e("asd","onGlobalLayout-----margLeft="+margLeft);
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
