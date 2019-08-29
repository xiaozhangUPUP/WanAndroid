package com.zq.wanandroid.ui.fragment;

import com.zq.wanandroid.di.component.AppComponent;
import com.zq.wanandroid.di.component.DaggerAppInfoComponent;
import com.zq.wanandroid.di.module.AppInfoModule;
import com.zq.wanandroid.presenter.AppInfoPresenter;
import com.zq.wanandroid.ui.adapter.AppInfoAdapter;

/**
 * Created by zhangqi on 2019/8/14
 */
public class TopListFragment extends BaseAppInfoFragment {

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder()
                .showPosition(true)
                .showCategoryName(true).build();
    }

    @Override
    int type() {
        return AppInfoPresenter.TOP_LIST;
    }

    @Override
    protected void setAppcomponent(AppComponent appcomponent) {

        DaggerAppInfoComponent.builder().appComponent(appcomponent)
                .appInfoModule(new AppInfoModule(this))
                .build()
                .injectTopListFragment(this);
    }
}
