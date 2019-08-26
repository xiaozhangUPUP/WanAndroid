package com.zq.wanandroid.di.module;

import com.zq.wanandroid.di.anno.FragmentScope;
import com.zq.wanandroid.http.ApiService;
import com.zq.wanandroid.http.responsebean.Category;
import com.zq.wanandroid.model.CategoryModel;
import com.zq.wanandroid.model.LoginModel;
import com.zq.wanandroid.presenter.CategoryPresenter;
import com.zq.wanandroid.presenter.LoginPresenter;
import com.zq.wanandroid.presenter.contract.CategoryContract;
import com.zq.wanandroid.presenter.contract.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhangqi on 2019/8/16
 */
@Module
public class CategoryModule {

    private CategoryContract.View view;

    public CategoryModule(CategoryContract.View view) {
        this.view = view;
    }

    @Provides
    @FragmentScope
    public CategoryPresenter provideCategoryPresenter(CategoryModel model) {
        return new CategoryPresenter(model, view);
    }

    @Provides
    @FragmentScope
    public CategoryModel provideCategoryModel(ApiService apiService) {
        return new CategoryModel(apiService);
    }

}
