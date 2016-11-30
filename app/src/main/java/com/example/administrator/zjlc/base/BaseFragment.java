package com.example.administrator.zjlc.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作用：Fragment的基类，LeftMenuFragment和ContentFragment都要继承它
 */
public abstract class BaseFragment extends Fragment {


    protected Activity mActivity;

    protected View rootView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();//MainActivity
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = initView();
        return rootView;
    }

    /**
     * 让孩子强制实现该方法，实现特定的效果
     * @return
     */
    public abstract View initView() ;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 当孩子需要初始化数据的时候，重新该方法
     */
    public void initData() {

    }
}
