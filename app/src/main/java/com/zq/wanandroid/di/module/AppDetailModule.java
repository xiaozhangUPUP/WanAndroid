package com.zq.wanandroid.di.module;

import com.zq.wanandroid.di.anno.FragmentScope;
import com.zq.wanandroid.http.ApiService;
import com.zq.wanandroid.model.AppInfoModel;
import com.zq.wanandroid.model.CategoryModel;
import com.zq.wanandroid.presenter.AppDetailPresenter;
import com.zq.wanandroid.presenter.CategoryPresenter;
import com.zq.wanandroid.presenter.contract.AppInfoContract;
import com.zq.wanandroid.presenter.contract.CategoryContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangqi on 2019/8/16
 */
@Module
public class AppDetailModule {

    private AppInfoContract.AppDetailView view;

    public AppDetailModule(AppInfoContract.AppDetailView view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    public AppDetailPresenter provideAppDetailPresenter(AppInfoModel model) {
        return new AppDetailPresenter(model, view);
    }

    @Provides
    @FragmentScope
    public AppInfoModel provideAppInfoModel(ApiService apiService) {
        return new AppInfoModel(apiService);
    }

}
