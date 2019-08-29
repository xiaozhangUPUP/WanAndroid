package com.zq.wanandroid.presenter;

import com.zq.wanandroid.common.RxHttpResponseCompose;
import com.zq.wanandroid.common.subscriber.ProgressObserver;
import com.zq.wanandroid.http.responsebean.AppInfo;
import com.zq.wanandroid.model.AppInfoModel;
import com.zq.wanandroid.presenter.contract.AppInfoContract;

public class AppDetailPresenter extends BasePresenter<AppInfoModel, AppInfoContract.AppDetailView> {


    public AppDetailPresenter(AppInfoModel mModel, AppInfoContract.AppDetailView mView) {
        super(mModel, mView);
    }

    public void getAppDetail(int id) {
        mModel.getAppDetail(id)
                .compose(RxHttpResponseCompose.<AppInfo>compatResult())
                .subscribe(new ProgressObserver<AppInfo>(mContext, mView) {
                    @Override
                    public void onNext(AppInfo appInfo) {
                        mView.showAppDetail(appInfo);
                    }
                });
    }

}
