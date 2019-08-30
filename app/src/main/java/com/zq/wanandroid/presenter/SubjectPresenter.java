package com.zq.wanandroid.presenter;


import com.zq.wanandroid.common.RxHttpResponseCompose;
import com.zq.wanandroid.common.subscriber.ErrorHandlerObserver;
import com.zq.wanandroid.common.subscriber.ProgressObserver;
import com.zq.wanandroid.http.responsebean.PageBean;
import com.zq.wanandroid.http.responsebean.Subject;
import com.zq.wanandroid.http.responsebean.SubjectDetail;
import com.zq.wanandroid.model.SubjectModel;
import com.zq.wanandroid.presenter.contract.SubjectContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class SubjectPresenter extends BasePresenter<SubjectModel, SubjectContract.SubjectView> {

    @Inject
    public SubjectPresenter(SubjectModel mModel, SubjectContract.SubjectView mView) {
        super(mModel, mView);
    }

    public void getSubjects(int page) {

        Observer subscriber = null;

        if (page == 0) {

            subscriber = new ProgressObserver<PageBean<Subject>>(mContext, mView) {
                @Override
                public void onNext(PageBean<Subject> pageBean) {
                    mView.showSubjects(pageBean);
                }
            };
        } else {
            subscriber = new ErrorHandlerObserver<PageBean<Subject>>(mContext) {
                @Override
                public void onComplete() {
                    mView.onLoadMoreComplete();
                }

                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(PageBean<Subject> pageBean) {

                    mView.showSubjects(pageBean);
                }
            };
        }

        mModel.getSubjects(page)
                .compose(RxHttpResponseCompose.<PageBean<Subject>>compatResult())
                .subscribe(subscriber);
    }


    public void getSubjectDetail(int id) {
        mModel.getSubjectDetail(id).compose(RxHttpResponseCompose.<SubjectDetail>compatResult())
                .subscribe(new ProgressObserver<SubjectDetail>(mContext, mView) {
                    @Override
                    public void onNext(SubjectDetail subjectDetail) {

                        mView.showSubjectDetail(subjectDetail);
                    }
                });
    }


}
