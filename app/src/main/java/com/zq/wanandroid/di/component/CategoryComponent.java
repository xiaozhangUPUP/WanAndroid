package com.zq.wanandroid.di.component;

import com.zq.wanandroid.di.anno.FragmentScope;
import com.zq.wanandroid.di.module.CategoryModule;
import com.zq.wanandroid.di.module.LoginModule;
import com.zq.wanandroid.ui.fragment.CategoryFragment;
import com.zq.wanandroid.ui.fragment.OtherFragment;

import dagger.Component;

/**
 * Created by zhangqi on 2019/8/16
 */
@FragmentScope
@Component(modules = CategoryModule.class, dependencies = AppComponent.class)
public interface CategoryComponent {
    void inject(CategoryFragment fragment);
}
