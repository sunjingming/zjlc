package com.example.administrator.zjlc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;

public class MainActivity extends FragmentActivity {
//    ContentFragment contentFragment;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        contentFragment = new ContentFragment(0);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, contentFragment).commit();
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//            ExitDialog(MainActivity.this).show();
//            return true;
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }
//
//    private Dialog ExitDialog(Context context) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("系统信息");
//        builder.setMessage("确定要退出程序吗?");
//        builder.setPositiveButton("确定",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        finish();
//                    }
//                });
//        builder.setNegativeButton("取消",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                    }
//                });
//        return builder.create();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        handler.post(runnable);
//
//    }
//
//    private Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            handler.sendEmptyMessage(1);
//        }
//    };
//
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 1:
//                    Intent intent = getIntent();
//                    final int tagid = intent.getIntExtra("tagid",0);
//                    Log.e("onResume",tagid+"");
//                    contentFragment.setId(tagid);
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, contentFragment).commit();
//            }
//        }
//    };
}
