package com.zq.wanandroid.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zq.wanandroid.R;
import com.zq.wanandroid.http.responsebean.PageBean;
import com.zq.wanandroid.http.responsebean.Subject;
import com.zq.wanandroid.ui.activity.SubjectActivity;
import com.zq.wanandroid.ui.adapter.SubjectAdapter;
import com.zq.wanandroid.ui.widget.SpaceItemDecoration2;

import butterknife.BindView;

/**
 * Created by zhangqi on 2019/8/30
 */
public class SubjectFragment extends BaseSubjectFragment implements BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private SubjectAdapter adapter;

    int page = 0;

    @Override
    protected void init() {
        initRecyclerView();
        presenter.getSubjects(page);
    }

    @Override
    public void showSubjects(PageBean<Subject> subjects) {

        adapter.addData(subjects.getDatas());
        if (subjects.isHasMore()) {
            page++;
        }
        adapter.setEnableLoadMore(subjects.isHasMore());
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(activity, 2);
        recyclerView.setLayoutManager(layoutManager);

        SpaceItemDecoration2 dividerDecoration = new SpaceItemDecoration2(5);
        recyclerView.addItemDecoration(dividerDecoration);

        adapter = new SubjectAdapter();
        adapter.setOnLoadMoreListener(this, recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Subject subject = (Subject) adapter.getItem(position);
                SubjectActivity subjectActivity = (SubjectActivity) activity;
                subjectActivity.showSubjectDetailFragment(subject);
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    protected void onEmptyViewClick() {
        presenter.getSubjects(page);
    }

    @Override
    public void onLoadMoreRequested() {
        presenter.getSubjects(page);
    }
}
