package com.meixun.videosearch.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class LinearItemDecoration extends RecyclerView.ItemDecoration {

    private int right;
    private int bottom;

    public LinearItemDecoration(int left) {
        this(left,0);
    }

    public LinearItemDecoration(int right, int bottom) {
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.right = right;
        outRect.bottom = bottom;
    }
}