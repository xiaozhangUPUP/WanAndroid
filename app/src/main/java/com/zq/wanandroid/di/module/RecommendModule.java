package com.zq.wanandroid.di.module;

import com.zq.wanandroid.di.anno.FragmentScope;
import com.zq.wanandroid.http.ApiService;
import com.zq.wanandroid.http.CacheProvider;
import com.zq.wanandroid.model.AppInfoModel;
import com.zq.wanandroid.presenter.RecommedPresenter;
import com.zq.wanandroid.presenter.contract.AppInfoContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangqi on 2019/8/16
 */
@Module
public class RecommendModule {

    private AppInfoContract.View view;

    public RecommendModule(AppInfoContract.View view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    public RecommedPresenter provideRecommendPresenter(AppInfoModel model) {
        return new RecommedPresenter(model, view);
    }

    @Provides
    @FragmentScope
    public AppInfoModel provideRecommendModel(ApiService apiService, CacheProvider cacheProvider) {
        return new AppInfoModel(apiService, cacheProvider);
    }

}
