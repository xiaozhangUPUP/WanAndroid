package com.zq.wanandroid.ui.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zq.wanandroid.R;
import com.zq.wanandroid.common.Constants;
import com.zq.wanandroid.http.responsebean.Subject;
import com.zq.wanandroid.http.responsebean.SubjectDetail;
import com.zq.wanandroid.ui.adapter.AppInfoAdapter;

import butterknife.BindView;


@SuppressLint("ValidFragment")
public class SubjectDetailFragment extends BaseSubjectFragment {


    @BindView(R.id.imageview)
    ImageView mImageView;
    @BindView(R.id.txt_desc)
    TextView mtxtDesc;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private Subject mSubject;

    private AppInfoAdapter mAdapter;

    public SubjectDetailFragment(Subject subject) {

        this.mSubject = subject;

    }

    @Override
    public void init() {

        initRecycleView();

        presenter.getSubjectDetail(mSubject.getRelatedId());


    }

    @Override
    public void showSubjectDetail(SubjectDetail detail) {
        Glide.with(activity)
                .load(Constants.BASE_IMG_URL + detail.getPhoneBigIcon())
                .into(mImageView);

        mtxtDesc.setText(detail.getDescription());

        mAdapter.addData(detail.getListApp());


    }

    private void initRecycleView() {

        mAdapter = AppInfoAdapter.builder().showBrief(false).showCategoryName(true).build();

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);

        mRecyclerView.addItemDecoration(itemDecoration);

        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public int setLayout() {
        return R.layout.fragment_subject_detail;
    }

    @Override
    protected void onEmptyViewClick() {
        presenter.getSubjectDetail(mSubject.getRelatedId());
    }
}
