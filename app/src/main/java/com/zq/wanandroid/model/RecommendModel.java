package com.zq.wanandroid.model;

import com.zq.wanandroid.http.ApiService;
import com.zq.wanandroid.http.responsebean.AppInfo;
import com.zq.wanandroid.http.responsebean.BaseBean;

import retrofit2.Callback;

/**
 * Created by zhangqi on 2019/8/16
 */
public class RecommendModel {

    private ApiService apiService;

    public RecommendModel(ApiService apiService) {
        this.apiService = apiService;
    }

    public void getApps(Callback<BaseBean<AppInfo>> callback) {
        apiService.getApps("{\"page\":0}").enqueue(callback);
    }
}
