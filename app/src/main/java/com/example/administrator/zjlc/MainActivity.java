package com.example.administrator.zjlc;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.administrator.zjlc.fragment.ContentFragment;

public class MainActivity extends FragmentActivity {

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, new ContentFragment()).commit();
    }
}
