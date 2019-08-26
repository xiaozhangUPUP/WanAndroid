package com.zq.wanandroid.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zq.wanandroid.MyApplication;
import com.zq.wanandroid.R;
import com.zq.wanandroid.common.Constants;
import com.zq.wanandroid.common.util.ACache;
import com.zq.wanandroid.http.responsebean.AppInfo;
import com.zq.wanandroid.http.responsebean.Category;
import com.zq.wanandroid.http.responsebean.PageBean;
import com.zq.wanandroid.presenter.AppInfoPresenter;
import com.zq.wanandroid.presenter.contract.AppInfoContract;
import com.zq.wanandroid.ui.activity.AppDetailActivity;
import com.zq.wanandroid.ui.adapter.AppInfoAdapter;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zhangqi on 2019/3/13
 */
public abstract class BaseAppInfoFragment extends ProgressFragment<AppInfoPresenter> implements AppInfoContract.AppInfoView, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    protected int page = 0;

    protected AppInfoAdapter adapter;

    protected void initRecyclerView() {
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = buildAdapter();
        adapter.setOnLoadMoreListener(this, recycler_view);
        recycler_view.setAdapter(adapter);
        recycler_view.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyApplication myApplication = (MyApplication) activity.getApplication();
                myApplication.setView(view);
                AppInfo item = (AppInfo) adapter.getItem(position);
                Intent intent = new Intent(getActivity(), AppDetailActivity.class);
                intent.putExtra(Constants.ITEM_APP_INFO_OBJ, item);
                startActivity(intent);
            }
        });

    }

    abstract AppInfoAdapter buildAdapter();

    abstract int type();

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void init() {
        presenter.requestData(type(), page);
        initRecyclerView();
    }

    @Override
    public void showResult(PageBean<AppInfo> pageBean) {
        adapter.addData(pageBean.getDatas());
        if (pageBean.isHasMore()) {
            page++;
        }
        adapter.setEnableLoadMore(pageBean.isHasMore());
    }

    @Override
    public void onLoadMoreComplete() {
        adapter.loadMoreComplete();
    }


    @Override
    public void onLoadMoreRequested() {
        presenter.requestData(type(), page);
    }

    @Override
    public void onEmptyViewClick() {
        presenter.requestData(type(), page);
    }
}
