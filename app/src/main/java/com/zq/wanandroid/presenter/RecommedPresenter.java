package com.zq.wanandroid.presenter;

import android.app.Activity;
import android.content.Context;

import com.zq.wanandroid.common.RxHttpResponseCompose;
import com.zq.wanandroid.common.subscriber.ErrorHandlerObserver;
import com.zq.wanandroid.http.responsebean.AppInfo;
import com.zq.wanandroid.http.responsebean.BaseBean;
import com.zq.wanandroid.http.responsebean.IndexBean;
import com.zq.wanandroid.http.responsebean.PageBean;
import com.zq.wanandroid.model.RecommendModel;
import com.zq.wanandroid.presenter.contract.RecommendContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhangqi on 2019/8/16
 */
public class RecommedPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {


    public RecommedPresenter(RecommendModel mModel, RecommendContract.View mView) {
        super(mModel, mView);
    }

    public void requestData() {

        mModel.index()
                .compose(RxHttpResponseCompose.<IndexBean>compatResult())
                .subscribe(new ErrorHandlerObserver<IndexBean>(mContext) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(IndexBean appInfoPageBean) {
                        mView.showResult(appInfoPageBean.getRecommendApps());
                    }

                    @Override
                    public void onComplete() {
                        mView.dismissLoading();
                    }
                });

    }
}
