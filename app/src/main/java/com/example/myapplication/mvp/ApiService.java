package com.example.myapplication.mvp;

import com.example.myapplication.base.BaseResponse;
import com.example.myapplication.bean.ArticlesBean;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("wxarticle/chapters/json")
    Observable<BaseResponse<ArrayList<ArticlesBean>>> getArticles();
}
