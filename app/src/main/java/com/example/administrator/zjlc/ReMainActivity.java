package com.example.administrator.zjlc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.zjlc.pager.Account;
import com.example.administrator.zjlc.pager.Find;
import com.example.administrator.zjlc.pager.HomePager;
import com.example.administrator.zjlc.view.NoScrollViewPager;

public class ReMainActivity extends FragmentActivity implements View.OnClickListener {

    private NoScrollViewPager vp_content_fragment;
    private RadioButton rb_home;
    private RadioButton rb_govaffair;
    private RadioButton rb_newscenter;
    private RadioGroup rg_bottom_tab;
    private Fragment homeFragment, findFragment, accountFragment, currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_main);
        initView();
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");

        rb_home.setOnClickListener(this);
        rb_govaffair.setOnClickListener(this);
        rb_newscenter.setOnClickListener(this);
        rg_bottom_tab.setOnClickListener(this);

        initTab();
    }

    private void initTab() {
        if (homeFragment == null) {
            homeFragment = new HomePager();
        }

        if (!homeFragment.isAdded()) {
            // 提交事务
            getSupportFragmentManager().beginTransaction().add(R.id.content_layout, homeFragment).commit();
            // 记录当前Fragment
            currentFragment = homeFragment;

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_home:
                if (homeFragment == null) {
                    homeFragment = new HomePager();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), homeFragment);
                break;
            case R.id.rb_govaffair:
                if (findFragment == null) {
                    findFragment = new Find();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), findFragment);
                break;
            case R.id.rb_newscenter:
                if (accountFragment == null) {
                    accountFragment = new Account();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), accountFragment);

        }
    }

    private void initView() {
        vp_content_fragment = (NoScrollViewPager) findViewById(R.id.vp_content_fragment);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_govaffair = (RadioButton) findViewById(R.id.rb_govaffair);
        rb_newscenter = (RadioButton) findViewById(R.id.rb_newscenter);
        rg_bottom_tab = (RadioGroup) findViewById(R.id.rg_bottom_tab);
    }


    private void addOrShowFragment(FragmentTransaction transaction,
                                   Fragment fragment) {
        if (currentFragment == fragment)
            return;

        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment)
                    .add(R.id.content_layout, fragment).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }

        currentFragment = fragment;
    }
}




















