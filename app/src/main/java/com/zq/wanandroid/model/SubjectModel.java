package com.zq.wanandroid.model;


import com.zq.wanandroid.http.ApiService;
import com.zq.wanandroid.http.responsebean.BaseBean;
import com.zq.wanandroid.http.responsebean.PageBean;
import com.zq.wanandroid.http.responsebean.Subject;
import com.zq.wanandroid.http.responsebean.SubjectDetail;

import io.reactivex.Observable;


public class SubjectModel {

    private ApiService mApiService;

    public SubjectModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    public Observable<BaseBean<PageBean<Subject>>> getSubjects(int page) {
        return mApiService.subjects(page);
    }

    public Observable<BaseBean<SubjectDetail>> getSubjectDetail(int id) {
        return mApiService.subjectDetail(id);
    }
}
