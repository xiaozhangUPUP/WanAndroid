package com.zq.wanandroid.ui.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zq.wanandroid.R;
import com.zq.wanandroid.di.component.AppComponent;
import com.zq.wanandroid.di.component.DaggerRecommendComponent;
import com.zq.wanandroid.di.module.RecommendModule;
import com.zq.wanandroid.http.responsebean.IndexBean;
import com.zq.wanandroid.presenter.RecommedPresenter;
import com.zq.wanandroid.presenter.contract.AppInfoContract;
import com.zq.wanandroid.ui.adapter.IndexMixAdapter;

import butterknife.BindView;

/**
 * Created by zhangqi on 2019/8/14
 */
public class RecommendFragment extends ProgressFragment<RecommedPresenter> implements AppInfoContract.View {
    private static final String TAG = RecommendFragment.class.getSimpleName();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;

    @Override
    protected void init() {
        presenter.requestData();

        initRefreshLayout();
    }

    private void initRefreshLayout() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Log.e(TAG, "------------------onRefresh--------------------------- ");
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });

    }

    @Override
    protected int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    protected void setAppcomponent(AppComponent appcomponent) {

        DaggerRecommendComponent.builder().appComponent(appcomponent)
                .recommendModule(new RecommendModule(this))
                .build()
                .inject(this);
    }


    @Override
    protected void onEmptyViewClick() {
        init();
    }

    @Override
    public void showResult(IndexBean indexBean) {
        initRecyclerview(indexBean);
    }

    private void initRecyclerview(IndexBean indexBean) {
        Log.e(TAG, "initRecyclerview:    " + contentView.getVisibility());
        IndexMixAdapter indexMixAdapter = new IndexMixAdapter(activity, indexBean);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        //                为RecyclerView设置分割线(这个可以对DividerItemDecoration进行修改，自定义)
        //        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(indexMixAdapter);
    }

}
