package com.zq.wanandroid.di.module;

import android.app.Application;

import com.google.gson.Gson;
import com.zq.wanandroid.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangqi on 2019/8/16
 */
@Module
public class AppModule {
    private Application myApplication;

    public AppModule(Application myApplication) {
        this.myApplication = myApplication;
    }


    @Provides
    @Singleton
    public Application provideMyApplication() {
        return myApplication;
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new Gson();
    }
}
