package com.zq.wanandroid.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zq.wanandroid.R;
import com.zq.wanandroid.common.Constants;
import com.zq.wanandroid.di.component.AppComponent;
import com.zq.wanandroid.di.component.DaggerAppInfoComponent;
import com.zq.wanandroid.di.module.AppInfoModule;
import com.zq.wanandroid.http.responsebean.AppInfo;
import com.zq.wanandroid.http.responsebean.PageBean;
import com.zq.wanandroid.presenter.AppInfoPresenter;
import com.zq.wanandroid.presenter.contract.AppInfoContract;
import com.zq.wanandroid.ui.adapter.AppInfoAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
