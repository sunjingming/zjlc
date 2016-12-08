package com.example.administrator.zjlc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ScrollView;

/**
 * Created by MrGuo on 2016/7/5.
 */
public class MyScrollView extends ScrollView{

    private Context mContext;
    private static int mMaxOverDistance = 50;
    private OnScrollListener onScrollListener;
    public MyScrollView(Context context) {
        super(context);
        mContext=context;
        initView();
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        initView();
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        initView();
    }


    private void initView() {
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        float density = metrics.density;
//        mMaxOverDistance = (int) (density * mMaxOverDistance);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, mMaxOverDistance, isTouchEvent);
    }

   public void setOnScrollListener(OnScrollListener onScrollListener){
       this.onScrollListener=onScrollListener;
   }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener!=null) onScrollListener.onScroll(t);
    }

    public interface OnScrollListener{
        public void onScroll(int y);
    }
}
