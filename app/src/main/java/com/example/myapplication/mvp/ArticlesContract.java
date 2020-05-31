package com.example.myapplication.mvp;

import com.example.myapplication.bean.ArticlesBean;
import com.example.myapplication.base.BaseResponse;

import java.util.ArrayList;

/**
 * MVP契约类
 */

public interface ArticlesContract {
    interface IArticlesView {
        void articlesSuccess(BaseResponse<ArrayList<ArticlesBean>> data);

        void articlesError(String error);
    }

    interface IArticlesPresenter {
        void getArticles();
    }

}
