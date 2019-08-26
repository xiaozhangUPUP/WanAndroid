package com.zq.wanandroid.di.module;

import com.zq.wanandroid.di.anno.FragmentScope;
import com.zq.wanandroid.http.ApiService;
import com.zq.wanandroid.model.AppInfoModel;
import com.zq.wanandroid.presenter.AppInfoPresenter;
import com.zq.wanandroid.presenter.RecommedPresenter;
import com.zq.wanandroid.presenter.contract.AppInfoContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangqi on 2019/8/16
 */
@Module
public class AppInfoModule {

    private AppInfoContract.AppInfoView view;

    public AppInfoModule(AppInfoContract.AppInfoView view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    public AppInfoPresenter provideAppInfoPresenter(AppInfoModel model) {
        return new AppInfoPresenter(model, view);
    }

    @Provides
    @FragmentScope
    public AppInfoModel provideRecommendModel(ApiService apiService) {
        return new AppInfoModel(apiService);
    }

}
