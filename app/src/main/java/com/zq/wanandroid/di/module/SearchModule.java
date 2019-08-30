package com.zq.wanandroid.di.module;


import com.zq.wanandroid.di.anno.FragmentScope;
import com.zq.wanandroid.http.ApiService;
import com.zq.wanandroid.model.SearchModel;
import com.zq.wanandroid.presenter.contract.SearchContract;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchModule {


    private SearchContract.SearchView mView;


    public SearchModule(SearchContract.SearchView view){

        this.mView = view;
    }

    @FragmentScope
    @Provides
    public SearchModel provideModel(ApiService apiService){

        return  new SearchModel(apiService);
    }

    @FragmentScope
    @Provides
    public SearchContract.SearchView provideView(){

        return  mView;
    }
}
