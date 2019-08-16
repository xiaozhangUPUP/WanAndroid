package com.zq.wanandroid.model;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.zq.wanandroid.http.ApiService;
import com.zq.wanandroid.http.requestbean.AppInfo;
import com.zq.wanandroid.http.requestbean.BaseBean;
import com.zq.wanandroid.ui.adapter.RecommendAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhangqi on 2019/8/16
 */
public class RecommendModel {


    public void getApps(Callback<BaseBean<AppInfo>> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getApps("{\"page\":0}").enqueue(callback);
    }
}
