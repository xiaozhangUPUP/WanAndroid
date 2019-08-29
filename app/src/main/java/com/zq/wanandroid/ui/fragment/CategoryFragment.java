package com.zq.wanandroid.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zq.wanandroid.R;
import com.zq.wanandroid.common.Constants;
import com.zq.wanandroid.di.component.AppComponent;
import com.zq.wanandroid.di.component.DaggerCategoryComponent;
import com.zq.wanandroid.di.module.CategoryModule;
import com.zq.wanandroid.http.responsebean.Category;
import com.zq.wanandroid.presenter.CategoryPresenter;
import com.zq.wanandroid.presenter.contract.CategoryContract;
import com.zq.wanandroid.ui.adapter.CategoryAdapter;
import com.zq.wanandroid.ui.activity.CategoryAppActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zhangqi on 2019/8/14
 */
public class CategoryFragment extends ProgressFragment<CategoryPresenter> implements CategoryContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private CategoryAdapter adapter;

    @Override
    protected void init() {
        initRecyclerView();
        presenter.requestCategories();
    }

    @Override
    protected int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    protected void setAppcomponent(AppComponent appcomponent) {
        DaggerCategoryComponent.builder().appComponent(appcomponent)
                .categoryModule(new CategoryModule(this))
                .build().inject(this);
    }

    @Override
    protected void onEmptyViewClick() {
        presenter.requestCategories();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new CategoryAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), CategoryAppActivity.class);
                intent.putExtra(Constants.CATEGORY, (Serializable) adapter.getData().get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void showResult(List<Category> categories) {
        adapter.addData(categories);
    }
}
