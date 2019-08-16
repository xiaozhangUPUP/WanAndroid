package com.zq.wanandroid;

import android.app.Application;

import com.mikepenz.iconics.Iconics;
import com.zq.wanandroid.fonts.AppIcons;

/**
 * Created by zhangqi on 2019/8/16
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //only required if you add a custom or generic font on your own
        Iconics.init(this);

        //register custom fonts like this (or also provide a font definition file)
        Iconics.registerFont(new AppIcons());
    }
}
