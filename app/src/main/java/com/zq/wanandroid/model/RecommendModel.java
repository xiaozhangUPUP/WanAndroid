package com.zq.wanandroid.model;

import com.zq.wanandroid.http.ApiService;
import com.zq.wanandroid.http.responsebean.AppInfo;
import com.zq.wanandroid.http.responsebean.BaseBean;
import com.zq.wanandroid.http.responsebean.IndexBean;
import com.zq.wanandroid.http.responsebean.PageBean;

import io.reactivex.Observable;

/**
 * Created by zhangqi on 2019/8/16
 */
public class RecommendModel {

    private ApiService apiService;

    public RecommendModel(ApiService apiService) {
        this.apiService = apiService;
    }

    //    public  Observable<PageBean<AppInfo>> getApps() {
    //        return apiService.getApps("{\"page\":0}");
    //    }

    public Observable<BaseBean<IndexBean>> index() {
        return apiService.index();
    }
}
