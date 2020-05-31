package com.example.myapplication.mvp;

import com.example.myapplication.base.BaseObserver;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.base.BaseResponse;
import com.example.myapplication.bean.ArticlesBean;

import java.util.ArrayList;

public class ArticlesPresenter extends BasePresenter<ArticlesActivity> implements ArticlesContract.IArticlesPresenter {

    @Override
    public void getArticles() {
        addSubscribe(create(ApiService.class).getArticles(), new BaseObserver<ArrayList<ArticlesBean>>() {

            @Override
            protected void onSuccess(BaseResponse<ArrayList<ArticlesBean>> data) {
                if (isViewAttached()) {
                    getView().articlesSuccess(data);
                }
            }

            @Override
            protected void onError(String errorMsg) {
                if (isViewAttached()) {
                    getView().articlesError(errorMsg);
                }
            }
        });

    }
}
