package com.example.administrator.zjlc.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.zjlc.MainActivity;
import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.utils.CacheUtils;

import org.xutils.common.util.DensityUtil;

import java.util.ArrayList;


public class GuideActivity extends AppCompatActivity {

    private static final String TAG = GuideActivity.class.getSimpleName();
    public static final String START_MAIN = "start_main";
    private ViewPager viewpager_guide;
    /**
     * 1.在布局文件定义ViewPager
     * 2.在代码实例化ViewPager
     * 3.设置适配器
     * 3.0，准备数据
     * 3.1，适配器要继承PagerAdapter
     * 3.2,至少要实现4个方法
     */
    private Button btn_start_main;
    private LinearLayout ll_point_group;//添加灰色的点
    private  int[] ids;
    private ArrayList<ImageView> imageViews ;
    private  ImageView iv_red_point;
    private  int pointwidth ;
    /**
     * 两点间的距离
     */
    private  int margLeft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewpager_guide = (ViewPager) findViewById(R.id.viewpager_guide);
        btn_start_main = (Button) findViewById(R.id.btn_start_main);
        ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);
        iv_red_point = (ImageView) findViewById(R.id.iv_red_point);
//        pointwidth = DensityUtil.dip2px(this,10);
        Log.e(TAG,"pointwidth=="+pointwidth);
        //准备数据id-->ArrayList<ImageView>
        imageViews = new ArrayList<ImageView>();

        ids = new int[]{R.drawable.guide1,R.drawable.guide2,R.drawable.guide3};
        for(int i=0;i<ids.length;i++){

            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(ids[i]);//注意如果是用viewpager的话，要设置背景
            //加入集合中
            imageViews.add(imageView);

            //添加灰色的点
            ImageView iv_normal_point = new ImageView(this);
            //设置灰色的背景
            iv_normal_point.setBackgroundResource(R.drawable.normal_point);
            //把它转化成对应的像素
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pointwidth,pointwidth);//像素px
            if(i !=0){
                params.leftMargin = pointwidth;
            }
            iv_normal_point.setLayoutParams(params);

            ll_point_group.addView(iv_normal_point);
        }

        //设置适配器
        viewpager_guide.setAdapter(new MyPagerAdapter());


        //View显示加载过程中测量onMearsure()-->指定位置和大小onLayout->绘制onDraw();

        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());


        //ViewPager监听页面的改变
        viewpager_guide.setOnPageChangeListener(new MyOnPageChangeListener());

        //设置点击事件
        btn_start_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存一下进入主页面
                CacheUtils.putBoolean(GuideActivity.this,START_MAIN, true);

                //点击进入主页面
                Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }

    class MyOnPageChangeListener implements  ViewPager.OnPageChangeListener{

        /**
         * 页面滚动的时候被回调
         * @param position 当前滑动页面的位置
         * @param positionOffset 屏幕移动的百分比
         * @param positionOffsetPixels 在屏幕上滑动的像素
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.e(TAG,"==position=="+position+",positionOffset=="+positionOffset+",positionOffsetPixels=="+positionOffsetPixels);

            //红点在两点间移动的距离 = 屏幕的移动的百分比* 间距
//            float maxLeft = positionOffset*margLeft;

            //红点在屏幕上移动的坐标 = 起始坐标+ 红点在两点间移动的距离
//            maxLeft = (position*margLeft)+positionOffset*margLeft;
            float   maxLeft = (position+positionOffset)*margLeft;//简写
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(pointwidth,pointwidth);
            params.leftMargin = (int) maxLeft;
            iv_red_point.setLayoutParams(params);

        }

        //当某个页面被选中的时候被回调
        //被选中页面的位置position
        @Override
        public void onPageSelected(int position) {

            if(position==imageViews.size()-1){
                //最后一个页面把按钮显示出来
                btn_start_main.setVisibility(View.VISIBLE);
            }else{
                //隐藏按钮
                btn_start_main.setVisibility(View.GONE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class MyOnGlobalLayoutListener implements  ViewTreeObserver.OnGlobalLayoutListener{

        @Override
        public void onGlobalLayout() {

            iv_red_point.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            //间距=第1个点的距离左边的距离-第0个点的距离左边的距离
            margLeft =  ll_point_group.getChildAt(1).getLeft() -ll_point_group.getChildAt(0).getLeft();
            Log.e(TAG,"onGlobalLayout-----margLeft="+margLeft);
        }
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
//            return view==imageViews.get(Integer.parseInt((String) object));
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView =imageViews.get(position);
//            ImageView imageView = new ImageView(GuideActivity.this);
//            imageView.setBackgroundResource(ids[position]);//注意如果是用viewpager的话，要设置背景

            container.addView(imageView);
//            return position;
            return imageView;
        }
    }

}
