package com.zq.wanandroid.di.component;

import android.app.Application;

import com.zq.wanandroid.di.module.AppModule;
import com.zq.wanandroid.di.module.HttpModule;
import com.zq.wanandroid.http.ApiService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zhangqi on 2019/8/16
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    public ApiService getApiService();

    public Application getApplicationg();

}
