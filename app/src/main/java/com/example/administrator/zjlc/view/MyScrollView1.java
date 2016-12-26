package com.example.administrator.zjlc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by MrGuo on 2016/7/5.
 */
public class MyScrollView1 extends ScrollView{
    private OnScrollBottomListener _listener;
    private Context mContext;
    private static int mMaxOverDistance = 50;
    private OnScrollListener onScrollListener;
    public MyScrollView1(Context context) {
        super(context);
        mContext=context;
        initView();
    }
    public void unRegisterOnScrollViewScrollToBottom() {
        _listener = null;
    }
    public MyScrollView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        initView();
    }

    public MyScrollView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        initView();
    }
    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int newHeightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, newHeightSpec);
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
    private int _calCount;
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener!=null) onScrollListener.onScroll(t);
        View view = this.getChildAt(0);
        if (this.getHeight() + this.getScrollY() == view.getHeight()) {
            _calCount++;
            if (_calCount == 1) {
                if (_listener != null) {
                    _listener.srollToBottom();
                }
            }
        } else {
            _calCount = 0;
        }
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    public interface OnScrollListener{
        void onScroll(int y);
    }

    public interface OnScrollBottomListener {
        void srollToBottom();
    }
    public void registerOnScrollViewScrollToBottom(OnScrollBottomListener l) {
        _listener = l;
    }

}
