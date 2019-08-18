package com.zq.wanandroid.di.module;

import android.content.Context;

import com.zq.wanandroid.di.anno.FragmentScope;
import com.zq.wanandroid.http.ApiService;
import com.zq.wanandroid.model.RecommendModel;
import com.zq.wanandroid.presenter.RecommedPresenter;
import com.zq.wanandroid.presenter.contract.RecommendContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangqi on 2019/8/16
 */
@Module
public class RecommendModule {

    private RecommendContract.View view;

    public RecommendModule(RecommendContract.View view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    public RecommedPresenter provideRecommendPresenter(RecommendModel model) {
        return new RecommedPresenter(model, view);
    }

    @Provides
    @FragmentScope
    public RecommendModel provideRecommendModel(ApiService apiService) {
        return new RecommendModel(apiService);
    }

}
