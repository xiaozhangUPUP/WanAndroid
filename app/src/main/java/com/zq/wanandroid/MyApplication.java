package com.zq.wanandroid;

import android.app.Application;
import android.view.View;

import com.mikepenz.iconics.Iconics;
import com.zq.wanandroid.di.component.AppComponent;
import com.zq.wanandroid.di.component.DaggerAppComponent;
import com.zq.wanandroid.di.module.AppModule;
import com.zq.wanandroid.di.module.CacheModule;
import com.zq.wanandroid.di.module.HttpModule;
import com.zq.wanandroid.common.fonts.AppIcons;

/**
 * Created by zhangqi on 2019/8/16
 */
public class MyApplication extends Application {

    private static AppComponent appComponent;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    private View view;

    @Override
    public void onCreate() {
        super.onCreate();

        //only required if you add a custom or generic font on your own
        Iconics.init(this);

        //register custom fonts like this (or also provide a font definition file)
        Iconics.registerFont(new AppIcons());

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .cacheModule(new CacheModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
