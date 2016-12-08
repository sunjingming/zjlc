package com.example.administrator.zjlc.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class AddressSpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int top;
    private int bottom;
    private int left;
    private int right;

    public AddressSpacesItemDecoration(int top, int bottom, int left, int right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = this.left;
        outRect.right = this.right;
        outRect.bottom = this.bottom;

        if(parent.getChildLayoutPosition(view) == 0){
            outRect.top = 0;
            outRect.right = 0;
            outRect.left = 0;
            outRect.bottom = bottom;
        }else if(parent.getAdapter().getItemCount() - 1 == parent.getChildLayoutPosition(view)){
            outRect.top = 0;
            outRect.right = 0;
            outRect.left = 0;
            outRect.bottom = bottom;
        }else{
            outRect.top = 0;
            outRect.right = 0;
            outRect.left = 0;
        }
    }
}
