package com.example.administrator.zjlc.fragment;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.administrator.zjlc.R;
import com.example.administrator.zjlc.base.BaseFragment;
import com.example.administrator.zjlc.base.BasePager;
import com.example.administrator.zjlc.pager.HomePager;
import com.example.administrator.zjlc.pager.Account;
import com.example.administrator.zjlc.pager.Find;
import com.example.administrator.zjlc.pager.MorePager;
import com.example.administrator.zjlc.view.NoScrollViewPager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;


/**
 * 作用：正文Fragment
 */
public class ContentFragment extends BaseFragment {

    @ViewInject(R.id.vp_content_fragment)
    private NoScrollViewPager vp_content_fragment;

    @ViewInject(R.id.rg_bottom_tab)
    private RadioGroup rg_bottom_tab;

    /**
     * 各个页面的集合
     */
    private ArrayList<BasePager> basePagers;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.content_fragment, null);
        x.view().inject(this,view);//把ContentFragment 注入到xUtils
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        rg_bottom_tab.check(R.id.rb_home);//设置默认选中首页

        //准备ViewPager的数据
        basePagers = new ArrayList<BasePager>();
        basePagers.add(new HomePager(mActivity));//添加首页
        basePagers.add(new Account(mActivity));//
        basePagers.add(new Find(mActivity));//
        basePagers.add(new MorePager(mActivity));//


        //设置ViewPager的适配器
        vp_content_fragment.setAdapter(new MyPagerAdapter());

        //设置RadioGroup选中状态的监听
        rg_bottom_tab.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

        //监听ViewPager页面的改变
        vp_content_fragment.setOnPageChangeListener(new MyOnPageChangeListener());
        //初始化某个页面的数据
        basePagers.get(0).initData();//默认初始首页的数据
    }

    /**
     * 得到首页信息
     * @return
     */
    public HomePager getHomePager() {
        return (HomePager) basePagers.get(0);
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            //初始化某个页面的数据
            basePagers.get(position).initData();

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    class MyOnCheckedChangeListener implements  RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            Intent i;
            switch (checkedId){
                case R.id.rb_home://首页
                    vp_content_fragment.setCurrentItem(0,false);
                    break;
                case R.id.rb_newscenter://理财
                    vp_content_fragment.setCurrentItem(1,false);
                    break;
                case R.id.rb_govaffair://我的
                    vp_content_fragment.setCurrentItem(2,false);
                    break;
                case R.id.rb_setting://更多
                    vp_content_fragment.setCurrentItem(3,false);
                    break;
            }

        }
    }


    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return basePagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view ==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            return super.instantiateItem(container, position);
            BasePager basePager = basePagers.get(position);//
            View rootView = basePager.rootView;
//            basePager.initData();//屏蔽预先加载
            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
