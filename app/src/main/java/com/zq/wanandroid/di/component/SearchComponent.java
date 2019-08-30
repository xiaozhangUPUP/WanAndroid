package com.zq.wanandroid.di.component;


import com.zq.wanandroid.di.anno.FragmentScope;
import com.zq.wanandroid.di.module.SearchModule;
import com.zq.wanandroid.ui.activity.SearchActivity;

import dagger.Component;


@FragmentScope
@Component(modules = SearchModule.class,dependencies= AppComponent.class)
public interface SearchComponent {

    void  inject(SearchActivity fragment);
}
