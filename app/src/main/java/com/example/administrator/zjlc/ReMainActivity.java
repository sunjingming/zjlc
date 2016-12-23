package com.example.administrator.zjlc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.zjlc.pager.Account;
import com.example.administrator.zjlc.pager.Find;
import com.example.administrator.zjlc.pager.HomePager;

public class ReMainActivity extends FragmentActivity implements View.OnClickListener {


    private FragmentManager fragmentManager;
    private Fragment homeFragment, findFragment, accountFragment, currentFragment;
    private RelativeLayout homeLayout, assitantLayout, accountLayout;
    // 底部标签图片
    private ImageView homeImg, assitantImg, accountImg;
    // 底部标签的文本
    private TextView homeTv, assitantTv, accountTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_main);
        initView();

        initTab();
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");

        accountFragment = new Account();

        fragmentManager = getSupportFragmentManager();
        if ("1".equals(id)){
            if (accountFragment == null) {
                accountFragment = new Account();
            }
            addOrShowFragment(getSupportFragmentManager().beginTransaction(), accountFragment);
            // 设置底部tab变化
            homeImg.setImageResource(R.drawable.tabbar_home);
            homeTv.setTextColor(getResources().getColor(R.color.gray15));

            assitantImg.setImageResource(R.drawable.tabbar_financial);
            assitantTv.setTextColor(getResources().getColor(R.color.gray15));

            accountImg.setImageResource(R.drawable.tabbar_person_select);
            accountTv.setTextColor(getResources().getColor(R.color.red));
        }

        homeLayout.setOnClickListener(this);
        assitantLayout.setOnClickListener(this);
        accountLayout.setOnClickListener(this);
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
            // 设置图片文本的变化
            homeImg.setImageResource(R.drawable.tabbar_home_sel);
            homeTv.setTextColor(getResources().getColor(R.color.red));
            assitantImg.setImageResource(R.drawable.tabbar_financial);
            assitantTv.setTextColor(getResources().getColor(R.color.gray15));

            accountImg.setImageResource(R.drawable.tabbar_person);
            accountTv.setTextColor(getResources().getColor(R.color.gray15));
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_home:
                if (homeFragment == null) {
                    homeFragment = new HomePager();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), homeFragment);
                // 设置底部tab变化
                homeImg.setImageResource(R.drawable.tabbar_home_sel);
                homeTv.setTextColor(getResources().getColor(R.color.red));

                assitantImg.setImageResource(R.drawable.tabbar_financial);
                assitantTv.setTextColor(getResources().getColor(R.color.gray15));

                accountImg.setImageResource(R.drawable.tabbar_person);
                accountTv.setTextColor(getResources().getColor(R.color.gray15));

                break;
            case R.id.rl_assitant:
                if (findFragment == null) {
                    findFragment = new Find();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), findFragment);
                // 设置底部tab变化
                homeImg.setImageResource(R.drawable.tabbar_home);
                homeTv.setTextColor(getResources().getColor(R.color.gray15));

                assitantImg.setImageResource(R.drawable.tabbar_financial_select);
                assitantTv.setTextColor(getResources().getColor(R.color.red));

                accountImg.setImageResource(R.drawable.tabbar_person);
                accountTv.setTextColor(getResources().getColor(R.color.gray15));

                break;
            case R.id.rl_account:
                if (accountFragment == null) {
                    accountFragment = new Account();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), accountFragment);
                // 设置底部tab变化
                homeImg.setImageResource(R.drawable.tabbar_home);
                homeTv.setTextColor(getResources().getColor(R.color.gray15));

                assitantImg.setImageResource(R.drawable.tabbar_financial);
                assitantTv.setTextColor(getResources().getColor(R.color.gray15));

                accountImg.setImageResource(R.drawable.tabbar_person_select);
                accountTv.setTextColor(getResources().getColor(R.color.red));


        }
    }

    private void initView() {
        homeLayout = (RelativeLayout) findViewById(R.id.rl_home);
        assitantLayout = (RelativeLayout) findViewById(R.id.rl_assitant);
        accountLayout = (RelativeLayout) findViewById(R.id.rl_account);

        homeLayout.setOnClickListener(this);
        assitantLayout.setOnClickListener(this);
        accountLayout.setOnClickListener(this);

        homeImg = (ImageView) findViewById(R.id.iv_home);
        assitantImg = (ImageView) findViewById(R.id.iv_assitant);
        accountImg = (ImageView) findViewById(R.id.iv_account);

        homeTv = (TextView) findViewById(R.id.tv_home);
        assitantTv = (TextView) findViewById(R.id.tv_assitant);
        accountTv = (TextView) findViewById(R.id.tv_account);
    }


    private void addOrShowFragment(FragmentTransaction transaction, Fragment fragment) {
        if (currentFragment == fragment)
            return;
        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment).add(R.id.content_layout, fragment).commit();
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




















