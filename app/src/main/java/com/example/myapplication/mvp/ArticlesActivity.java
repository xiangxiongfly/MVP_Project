package com.example.myapplication.mvp;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseMvpActivity;
import com.example.myapplication.base.BaseResponse;
import com.example.myapplication.bean.ArticlesBean;

import java.util.ArrayList;

public class ArticlesActivity extends BaseMvpActivity<ArticlesPresenter> implements ArticlesContract.IArticlesView {

    private Button btn_get_articles;
    private TextView tv_articles;
    private ProgressDialog progressDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_articles;
    }

    @Override
    protected void initViews() {
        btn_get_articles = findViewById(R.id.btn_get_articles);
        tv_articles = findViewById(R.id.tv_articles);

        btn_get_articles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                getPresenter().getArticles();
            }
        });
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected ArticlesPresenter createPresenter() {
        return new ArticlesPresenter();
    }

    @Override
    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage("加载中");
        }
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }

    @Override
    public void articlesSuccess(BaseResponse<ArrayList<ArticlesBean>> data) {
        hideLoading();
        tv_articles.setText(data.toString());
    }

    @Override
    public void articlesError(String error) {
        hideLoading();
        tv_articles.setText(error);
    }
}
