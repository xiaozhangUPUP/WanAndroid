package com.zq.wanandroid.model;

import com.zq.wanandroid.http.ApiService;
import com.zq.wanandroid.http.CacheProvider;

/**
 * Created by zhangqi on 2019/9/3
 */
public class BaseModel {
    protected ApiService apiService;
    protected CacheProvider cacheProvider;

    public BaseModel(ApiService apiService, CacheProvider cacheProvider) {
        this.apiService = apiService;
        this.cacheProvider = cacheProvider;
    }
}
