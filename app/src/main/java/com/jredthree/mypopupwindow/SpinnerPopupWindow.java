package com.jredthree.mypopupwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by jredthree on 2017/2/15.
 */
public class SpinnerPopupWindow {
    private Context mContext;

    private PopupWindow mPopWindow;

    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;

    private RecyclerView.ItemDecoration mItemDecoration;

    private RecyclerView.ItemAnimator mItemAnimato;

    private View view;

    private boolean isFocusable;//获取焦点

    private boolean isOutsideTouchable;//点击外面是否可以消失

    private Drawable mDrawable;

    private int mWidth;

    private int mHeight;

    private RecyclerView.Adapter mAdapter;

    /**
     * 初始化
     * @param b
     */

    private SpinnerPopupWindow(Builder b) {
        buildPopupWindow(b);
    }

    private void buildPopupWindow(Builder b){
        initParams(b);

        view = LayoutInflater.from(mContext).inflate(R.layout.view_spinner,null);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_spinner_list);

        initRecyclerView();

        initPopWindows();
    }

    private void initParams(Builder b){
        this.mContext = b.mContext;
         mLayoutManager = b.mLayoutManager;
         mItemDecoration = b.mItemDecoration;
         mItemAnimato = b.mItemAnimato;
         mAdapter = b.mAdapter;
         isFocusable = b.isFocusable;
         isOutsideTouchable = b.isOutsideTouchable;
         mDrawable = b.mDrawable;
         mWidth = b.mWidth;
         mHeight = b.mHeight;
    }


    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView(){

        if(null == mItemAnimato)
            mItemAnimato = new DefaultItemAnimator();

        mRecyclerView.setItemAnimator(mItemAnimato); //设置动画

        if(null != mItemDecoration)
            mRecyclerView.addItemDecoration(mItemDecoration);//设置分割线

        if(null == mLayoutManager)
            mLayoutManager = new LinearLayoutManager(mContext);

        mRecyclerView.setLayoutManager(mLayoutManager);

        if(null != mAdapter)
            mRecyclerView.setAdapter(mAdapter);
    }



    /**
     * 初始化弹框
     */
    private void initPopWindows(){

        mPopWindow = new PopupWindow(view,mWidth,mHeight);

        mPopWindow.setFocusable(isFocusable);

        mPopWindow.setOutsideTouchable(isOutsideTouchable);

        if (null == mDrawable)
            mDrawable = new BitmapDrawable();

        mPopWindow.setBackgroundDrawable(mDrawable);

    }

    /**
     *
     * 显示Pop在view的右下方
     * */
    public void showPopWindow(View view) {
        int[] point = {0,0};
        view.getLocationOnScreen(point);
        mPopWindow.showAtLocation(view, Gravity.RIGHT | Gravity.TOP, 0, point[1] + view.getHeight());
    }

    /**
     * 自定义显示的位置
     * */
    public void showPopWindow(View parent, int gravity, int x, int y) {
        mPopWindow.showAtLocation(parent, gravity, x, y);
    }

    /**
     * 显示在正下方
     * @param v
     */
    public void showPopWindowCenter(View v){
        int[] point = {0,0};
        v.getLocationOnScreen(point);
        //v距离左边的位置 - 弹框的宽度一半，弹框处于v的左边 超出v的宽度的一半
        mPopWindow.showAtLocation(view,Gravity.TOP| Gravity.LEFT, point[0] - mWidth / 2 + v.getWidth() / 2, point[1] + view.getHeight() + v.getHeight());
    }

    /**
     * 隐藏Pop
     */
    public void dismissPopWindow(){
        if(mPopWindow.isShowing())
            mPopWindow.dismiss();
    }

    public boolean isShowing(){
        return mPopWindow.isShowing();
    }

    public static class Builder {
        private Context mContext;
        private RecyclerView.LayoutManager mLayoutManager;
        private RecyclerView.ItemDecoration mItemDecoration;
        private RecyclerView.ItemAnimator mItemAnimato;
        private RecyclerView.Adapter mAdapter;
        private boolean isFocusable;//获取焦点
        private boolean isOutsideTouchable;//点击外面是否可以消失
        private Drawable mDrawable;
        private int mWidth;
        private int mHeight;

        public Builder(Context context){
            this.mContext = context;
        }

        public SpinnerPopupWindow build(){
            return new SpinnerPopupWindow(this);
        }

        public RecyclerView.LayoutManager getmLayoutManager() {
            return mLayoutManager;
        }

        public Builder setmLayoutManager(RecyclerView.LayoutManager mLayoutManager) {
            this.mLayoutManager = mLayoutManager;
            return this;
        }

        public RecyclerView.ItemDecoration getmItemDecoration() {
            return mItemDecoration;
        }

        public Builder setmItemDecoration(RecyclerView.ItemDecoration mItemDecoration) {
            this.mItemDecoration = mItemDecoration;
            return this;
        }

        public RecyclerView.ItemAnimator getmItemAnimato() {
            return mItemAnimato;
        }

        public Builder setmItemAnimato(RecyclerView.ItemAnimator mItemAnimato) {
            this.mItemAnimato = mItemAnimato;
            return this;
        }

        public RecyclerView.Adapter getmAdapter() {
            return mAdapter;
        }

        public Builder setmAdapter(RecyclerView.Adapter mAdapter) {
            this.mAdapter = mAdapter;
            return this;
        }

        public boolean isFocusable() {
            return isFocusable;
        }

        public Builder setFocusable(boolean focusable) {
            isFocusable = focusable;
            return this;
        }

        public boolean isOutsideTouchable() {
            return isOutsideTouchable;
        }

        public Builder setOutsideTouchable(boolean outsideTouchable) {
            isOutsideTouchable = outsideTouchable;
            return this;
        }

        public Drawable getmDrawable() {
            return mDrawable;
        }

        public Builder setmDrawable(Drawable mDrawable) {
            this.mDrawable = mDrawable;
            return this;
        }

        public int getmWidth() {
            return mWidth;
        }

        public Builder setmWidth(int mWidth) {
            this.mWidth = mWidth;
            return this;
        }

        public int getmHeight() {
            return mHeight;
        }

        public Builder setmHeight(int mHeight) {
            this.mHeight = mHeight;
            return this;
        }

    }
}
