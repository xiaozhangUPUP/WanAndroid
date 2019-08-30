package com.zq.wanandroid.ui.fragment;


import com.zq.wanandroid.di.component.AppComponent;
import com.zq.wanandroid.di.component.DaggerSubjectComponent;
import com.zq.wanandroid.di.module.SubjectModule;
import com.zq.wanandroid.http.responsebean.PageBean;
import com.zq.wanandroid.http.responsebean.Subject;
import com.zq.wanandroid.http.responsebean.SubjectDetail;
import com.zq.wanandroid.presenter.SubjectPresenter;
import com.zq.wanandroid.presenter.contract.SubjectContract;

public abstract class BaseSubjectFragment extends ProgressFragment<SubjectPresenter> implements SubjectContract.SubjectView {

    @Override
    public void showSubjects(PageBean<Subject> subjects) {

    }

    @Override
    public void onLoadMoreComplete() {

    }

    @Override
    public void showSubjectDetail(SubjectDetail detail) {

    }

    @Override
    protected void setAppcomponent(AppComponent appcomponent) {
        DaggerSubjectComponent.builder()
                .appComponent(appcomponent)
                .subjectModule(new SubjectModule(this))
                .build().inject(this);
    }
}
