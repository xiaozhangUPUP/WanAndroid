package com.zq.wanandroid.di.component;

import com.zq.wanandroid.di.anno.FragmentScope;
import com.zq.wanandroid.di.module.AppDetailModule;
import com.zq.wanandroid.ui.fragment.AppDetailFragment;

import dagger.Component;

/**
 * Created by zhangqi on 2019/8/16
 */
@FragmentScope
@Component(modules = AppDetailModule.class, dependencies = AppComponent.class)
public interface AppDetailComponent {
    void inject(AppDetailFragment fragment);
}
