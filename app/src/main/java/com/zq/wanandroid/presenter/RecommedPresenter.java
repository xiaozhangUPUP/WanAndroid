package com.zq.wanandroid.presenter;

import com.zq.wanandroid.common.RxHttpResponseCompose;
import com.zq.wanandroid.common.subscriber.ErrorHandlerObserver;
import com.zq.wanandroid.common.subscriber.ProgressObserver;
import com.zq.wanandroid.http.responsebean.AppInfo;
import com.zq.wanandroid.http.responsebean.IndexBean;
import com.zq.wanandroid.http.responsebean.PageBean;
import com.zq.wanandroid.model.RecommendModel;
import com.zq.wanandroid.presenter.contract.RecommendContract;

import io.reactivex.disposables.Disposable;

/**
 * Created by zhangqi on 2019/8/16
 */
public class RecommedPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {


    public RecommedPresenter(RecommendModel mModel, RecommendContract.View mView) {
        super(mModel, mView);
    }

    public void requestData() {

        mModel.getApps()
                .compose(RxHttpResponseCompose.<PageBean<AppInfo>>compatResult())
                .subscribe(new ProgressObserver<PageBean<AppInfo>>(mContext, mView) {
                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        mView.showResult(appInfoPageBean.getDatas());
                    }
                });

    }
}
