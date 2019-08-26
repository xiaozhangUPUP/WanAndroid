package com.zq.wanandroid.model;

import com.zq.wanandroid.http.ApiService;
import com.zq.wanandroid.http.requestbean.LoginRequestBean;
import com.zq.wanandroid.http.responsebean.BaseBean;
import com.zq.wanandroid.http.responsebean.Category;
import com.zq.wanandroid.http.responsebean.LoginBean;

import java.util.List;

import io.reactivex.Observable;

public class CategoryModel {

    private ApiService apiService;

    public CategoryModel(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<BaseBean<List<Category>>> getCategories() {
        return apiService.getCategories();
    }

}
