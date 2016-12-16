package com.example.administrator.zjlc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
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
    private FragmentManager fragmentManager;
    private RadioGroup rg_bottom_tab;
    private Fragment homeFragment, findFragment, accountFragment, currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_main);
        initView();

        initTab();
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");

        rb_home.setOnClickListener(this);
        rb_govaffair.setOnClickListener(this);
        rb_newscenter.setOnClickListener(this);
        rg_bottom_tab.setOnClickListener(this);

        accountFragment = new Account();

        fragmentManager = getSupportFragmentManager();
        if ("1".equals(id)){
            if (accountFragment == null) {
                accountFragment = new Account();
            }
            addOrShowFragment(getSupportFragmentManager().beginTransaction(), accountFragment);
        }
    }

    private void initTab() {
        if (homeFragment == null) {
            homeFragment = new HomePager();
        }

        if (!homeFragment.isAdded()) {
            // 提交事务
            getSupportFragmentManager().beginTransaction().add(R.id.content_layout, homeFragment).commit();
            // 记录当前Fragment
            Drawable img_off;
            Resources res = getResources();
            img_off = res.getDrawable(R.drawable.tabbar_home_sel);
            img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
            currentFragment = homeFragment;
            rb_home.setTextColor(getResources().getColor(R.color.red));
            rb_home .setCompoundDrawables(null,img_off,null,null);
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
                    Drawable img_off;
                    Resources res = getResources();
                    img_off = res.getDrawable(R.drawable.tabbar_home);
                    img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
                    currentFragment = homeFragment;
                    rb_home.setTextColor(Color.GRAY);
                    rb_home .setCompoundDrawables(null,img_off,null,null);
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), findFragment);
                break;
            case R.id.rb_newscenter:
                if (accountFragment == null) {
                    accountFragment = new Account();
                    Drawable img_off;
                    Resources res = getResources();
                    img_off = res.getDrawable(R.drawable.tabbar_home);
                    img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
                    currentFragment = homeFragment;
                    rb_home.setTextColor(Color.GRAY);
                    rb_home .setCompoundDrawables(null,img_off,null,null);
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


    private void addOrShowFragment(FragmentTransaction transaction, Fragment fragment) {
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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            ExitDialog(ReMainActivity.this).show();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private Dialog ExitDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("系统信息");
        builder.setMessage("确定要退出程序吗?");
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        finish();
                    }
                });
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
        return builder.create();
    }
}




















