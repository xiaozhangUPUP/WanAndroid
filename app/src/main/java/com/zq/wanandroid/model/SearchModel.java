package com.zq.wanandroid.model;


import com.zq.wanandroid.http.ApiService;
import com.zq.wanandroid.http.responsebean.BaseBean;
import com.zq.wanandroid.http.responsebean.SearchResult;

import java.util.List;

import io.reactivex.Observable;


public class SearchModel {


    private ApiService mApiService;


    public SearchModel(ApiService apiService) {

        this.mApiService = apiService;
    }

    public Observable<BaseBean<List<String>>> getSuggestion(String keyword) {


        return mApiService.searchSuggest(keyword);

    }

    public Observable<BaseBean<SearchResult>> search(String keyword) {
        return mApiService.search(keyword);
    }
}
