package com.zq.wanandroid.presenter;

import com.zq.wanandroid.common.RxHttpResponseCompose;
import com.zq.wanandroid.common.subscriber.ProgressObserver;
import com.zq.wanandroid.http.responsebean.IndexBean;
import com.zq.wanandroid.model.AppInfoModel;
import com.zq.wanandroid.presenter.contract.AppInfoContract;

/**
 * Created by zhangqi on 2019/8/16
 */
public class RecommedPresenter extends BasePresenter<AppInfoModel, AppInfoContract.View> {


    public RecommedPresenter(AppInfoModel mModel, AppInfoContract.View mView) {
        super(mModel, mView);
    }

    public void requestData() {
        mModel.index().compose(RxHttpResponseCompose.<IndexBean>compatResult())
                .subscribe(new ProgressObserver<IndexBean>(mContext, mView) {
                    @Override
                    public void onNext(IndexBean indexBean) {
                        mView.showResult(indexBean);
                    }
                });

//        mModel.getApps()
//                .compose(RxHttpResponseCompose.<PageBean<AppInfo>>compatResult())
//                .subscribe(new ProgressObserver<PageBean<AppInfo>>(mContext, mView) {
//                    @Override
//                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
//                        mView.showResult(appInfoPageBean.getDatas());
//                    }
//                });

    }
}
