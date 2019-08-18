package com.zq.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zq.wanandroid.MyApplication;
import com.zq.wanandroid.R;
import com.zq.wanandroid.di.component.AppComponent;
import com.zq.wanandroid.di.component.DaggerRecommendComponent;
import com.zq.wanandroid.di.module.RecommendModule;
import com.zq.wanandroid.http.responsebean.AppInfo;
import com.zq.wanandroid.presenter.RecommedPresenter;
import com.zq.wanandroid.presenter.contract.RecommendContract;
import com.zq.wanandroid.ui.adapter.RecommendAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhangqi on 2019/8/14
 */
public class RecommendFragment extends BaseFragment<RecommedPresenter> implements RecommendContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void init() {
        presenter.requestData();
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_other;
    }

    @Override
    protected void setAppcomponent(AppComponent appcomponent) {

        DaggerRecommendComponent.builder().appComponent(appcomponent)
                .recommendModule(new RecommendModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showResult(List<AppInfo> appInfoList) {
        RecommendAdapter recommendAdapter = new RecommendAdapter(appInfoList, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //                为RecyclerView设置分割线(这个可以对DividerItemDecoration进行修改，自定义)
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recommendAdapter);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
