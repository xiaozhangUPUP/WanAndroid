package com.zq.wanandroid.ui.fragment;

import android.annotation.SuppressLint;

import com.zq.wanandroid.di.component.AppComponent;
import com.zq.wanandroid.di.component.DaggerAppInfoComponent;
import com.zq.wanandroid.di.module.AppInfoModule;
import com.zq.wanandroid.ui.adapter.AppInfoAdapter;

@SuppressLint("ValidFragment")
public class CategoryAppFragment extends BaseAppInfoFragment {
    public static final int FEATURED = 0;
    public static final int TOPLIST = 1;
    public static final int NEWLIST = 3;

    private int categoryId;
    private int fragmentType;

    private CategoryAppFragment(int categoryId, int fragmentType) {
        this.categoryId = categoryId;
        this.fragmentType = fragmentType;
    }

    public static CategoryAppFragment newInstance(int categoryId, int fragmentType) {
        return new CategoryAppFragment(categoryId, fragmentType);
    }


    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder().showBrief(true).build();
    }

    @Override
    public void init() {
        presenter.requestCategoryApps(categoryId, page, fragmentType);
        initRecyclerView();
    }

    @Override
    int type() {
        return 0;
    }

    @Override
    protected void setAppcomponent(AppComponent appcomponent) {
        DaggerAppInfoComponent.builder().appComponent(appcomponent)
                .appInfoModule(new AppInfoModule(this))
        .build().injectCategoryAppFragment(this);
    }
}
