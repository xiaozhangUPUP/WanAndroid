package com.zq.wanandroid.di.component;

import com.zq.wanandroid.di.anno.FragmentScope;
import com.zq.wanandroid.di.module.RecommendModule;
import com.zq.wanandroid.model.RecommendModel;
import com.zq.wanandroid.ui.fragment.RecommendFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by zhangqi on 2019/8/16
 */
@FragmentScope
@Component(modules = RecommendModule.class)
public interface RecommendComponent {
    void inject(RecommendFragment fragment);
}
