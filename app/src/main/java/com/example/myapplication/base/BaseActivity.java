package com.example.myapplication.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            initViews();
        }
        mContext = this;
        initPresenter();
        initDatas();
    }

    /**
     * 获取布局Id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initViews();

    /**
     * 初始化Presenter
     */
    protected void initPresenter() {

    }

    /**
     * 初始化数据
     */
    protected abstract void initDatas();

}
