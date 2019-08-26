package com.zq.wanandroid.di.component;

import com.zq.wanandroid.di.anno.FragmentScope;
import com.zq.wanandroid.di.module.AppInfoModule;
import com.zq.wanandroid.di.module.RecommendModule;
import com.zq.wanandroid.ui.fragment.CategoryAppFragment;
import com.zq.wanandroid.ui.fragment.RecommendFragment;
import com.zq.wanandroid.ui.fragment.TopListFragment;

import dagger.Component;

/**
 * Created by zhangqi on 2019/8/16
 */
@FragmentScope
@Component(modules = AppInfoModule.class, dependencies = AppComponent.class)
public interface AppInfoComponent {
    void injectTopListFragment(TopListFragment fragment);
    void injectCategoryAppFragment(CategoryAppFragment fragment);
}
