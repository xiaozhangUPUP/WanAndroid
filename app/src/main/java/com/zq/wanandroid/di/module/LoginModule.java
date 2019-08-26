package com.zq.wanandroid.di.module;

import com.zq.wanandroid.di.anno.FragmentScope;
import com.zq.wanandroid.http.ApiService;
import com.zq.wanandroid.model.AppInfoModel;
import com.zq.wanandroid.model.LoginModel;
import com.zq.wanandroid.presenter.AppInfoPresenter;
import com.zq.wanandroid.presenter.LoginPresenter;
import com.zq.wanandroid.presenter.contract.AppInfoContract;
import com.zq.wanandroid.presenter.contract.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangqi on 2019/8/16
 */
@Module
public class LoginModule {

    private LoginContract.View view;

    public LoginModule(LoginContract.View view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    public LoginPresenter provideLoginPresenter(LoginModel model) {
        return new LoginPresenter(model, view);
    }

    @Provides
    @FragmentScope
    public LoginModel provideLoginModel(ApiService apiService) {
        return new LoginModel(apiService);
    }

}
