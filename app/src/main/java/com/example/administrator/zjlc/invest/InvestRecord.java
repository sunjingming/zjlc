package com.example.administrator.zjlc.invest;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.zjlc.R;

import java.util.ArrayList;
import java.util.List;

public class InvestRecord extends AppCompatActivity implements TabLayout.OnTabSelectedListener{
    private Toolbar toolbar;
    private TextView title;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyViewPagerAdapter viewPagerAdapter;
    private String[] titles=new String[]{"\t\t待回款\t\t","\t\t已完成\t\t"};
    private List<Fragment> fragments=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_record);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) findViewById(R.id.tv_title);
        title.setText("详情");
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tabLayout = ((TabLayout) findViewById(R.id.home_tabLayoutId));
        viewPager = ((ViewPager) findViewById(R.id.home_viewPagerId));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //循环注入标签
        for (String tab:titles){
            tabLayout.addTab(tabLayout.newTab().setText(tab));
        }
        //设置TabLayout点击事件
        tabLayout.setOnTabSelectedListener(this);

        fragments.add(new WaitRecover());
        fragments.add(new Complete());

        viewPagerAdapter=new MyViewPagerAdapter(getSupportFragmentManager(),titles,fragments);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
