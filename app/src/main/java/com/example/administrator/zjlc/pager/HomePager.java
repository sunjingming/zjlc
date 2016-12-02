package com.example.administrator.zjlc.pager;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.base.BasePager;
import com.example.administrator.zjlc.domain.JsonRootBean;
import com.example.administrator.zjlc.domain.TJBBean;
import com.example.administrator.zjlc.urls.UrlsUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/6/23.
 */
public class HomePager extends BasePager {

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
    private ImageView iv_lijitouzi;

    private int oldPosition = 0;// 记录上一次点的位置

    private int currentItem; // 当前页面private int oldPosition = 0;// 记录上一次点的位置

    private ViewPagerAdapter viewPagerAdapter;

    public HomePager(Activity activity) {
        super(activity);
    }
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
                //设置
                viewPagerAdapter = new ViewPagerAdapter();
                dots.get(0).setBackgroundResource(R.drawable.dot_focused);
                viewpager.setOnPageChangeListener(new MyPageChangeListener());
                viewpager.setAdapter(viewPagerAdapter);
            return false;
        }
    });
    @Override
    public void initData() {
        super.initData();
        System.out.println("首页数据被初始化了...");
        //设置标题
        view = View.inflate(mActivity, R.layout.homepager, null);
        viewList = new ArrayList<View>();
        initView();
        fl_basepager_content.addView(view);
    }

    /**
     * 1.在布局文件定义ViewPager
     * 2.在代码实例化ViewPager
     */
    private void initView() {
        //初始化组件
        tv_biao = (TextView) view.findViewById(R.id.tv_biao);
        tv_lv = (TextView) view.findViewById(R.id.tv_lv);
        tv_jindu = (TextView) view.findViewById(R.id.tv_jindu);
        tv_ss = (TextView) view.findViewById(R.id.tv_jindu);
        tv_time = (TextView) view.findViewById(R.id.tv_time);
        iv_lijitouzi = (ImageView) view.findViewById(R.id.iv_lijitouzi);

        //代码实例化
        viewpager = (ViewPager) view.findViewById(R.id.viewpager_guide);
        dots = new ArrayList<View>();
        dots.add(view.findViewById(R.id.dot_1));
        dots.add(view.findViewById(R.id.dot_2));
        //设置bannner图片
        setViewpagerData();
        //设置推荐标的详情
        setViewText();
        }

    private void setViewText() {
        RequestParams requestparams = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCRecommend_list);
        x.http().post(requestparams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.e("data网站banner", data);
                Gson gson = new Gson();
                TJBBean tjbBean = gson.fromJson(data, TJBBean.class);
                //获取到数据部署上去
                tv_biao.setText(tjbBean.getData().getBorrow_name());
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
    public void setViewpagerData(){
        RequestParams paramsNotice = new RequestParams(UrlsUtils.ZJLCstring+UrlsUtils.ZJLCBanner);
        x.http().post(paramsNotice, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String data = result;
                Log.e("data网站banner", data);
                Gson gson = new Gson();
                JsonRootBean noticeBean = gson.fromJson(data, JsonRootBean.class);

                jrb = noticeBean.getData();

                for (int i = 0; i < jrb.size(); i++) {
                    ImageView imageView = new ImageView(mActivity);

                    Glide.with(mActivity).load(jrb.get(i)).into(imageView);
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
                Log.e("data网站banner","onCancelled");

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
            ((ViewPager) views).removeView(mListViews.get(position));

        }

        @Override
        public Object instantiateItem(ViewGroup views, int position) {
            ((ViewPager) views).addView(mListViews.get(position));
            return mListViews.get(position);
        }
    }
}
