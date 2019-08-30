package com.zq.wanandroid.presenter.contract;


import com.zq.wanandroid.http.responsebean.PageBean;
import com.zq.wanandroid.http.responsebean.Subject;
import com.zq.wanandroid.http.responsebean.SubjectDetail;
import com.zq.wanandroid.presenter.BaseView;


public class SubjectContract {

    public interface SubjectView extends BaseView {

        void showSubjects(PageBean<Subject> subjects);

        void onLoadMoreComplete();

        void showSubjectDetail(SubjectDetail detail);

    }

}
