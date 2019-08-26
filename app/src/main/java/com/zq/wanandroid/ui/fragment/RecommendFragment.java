package com.zq.wanandroid.ui.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

    @Override
    protected void init() {
        presenter.requestData();
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
