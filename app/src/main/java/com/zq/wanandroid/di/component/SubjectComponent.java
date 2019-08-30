package com.zq.wanandroid.di.component;


import com.zq.wanandroid.di.anno.FragmentScope;
import com.zq.wanandroid.di.module.SubjectModule;
import com.zq.wanandroid.ui.fragment.BaseSubjectFragment;

import dagger.Component;

@FragmentScope
@Component(modules = SubjectModule.class,dependencies= AppComponent.class)
public interface SubjectComponent {

    void  inject(BaseSubjectFragment fragment);
}
