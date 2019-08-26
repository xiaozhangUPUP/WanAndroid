package com.zq.wanandroid.presenter;

import com.zq.wanandroid.common.RxHttpResponseCompose;
import com.zq.wanandroid.common.subscriber.ProgressObserver;
import com.zq.wanandroid.http.responsebean.Category;
import com.zq.wanandroid.model.AppInfoModel;
import com.zq.wanandroid.model.CategoryModel;
import com.zq.wanandroid.presenter.contract.AppInfoContract;
import com.zq.wanandroid.presenter.contract.CategoryContract;

import java.util.List;

public class AppDetailPresenter extends BasePresenter<AppInfoModel, AppInfoContract.AppDetailView> {


    public AppDetailPresenter(AppInfoModel mModel, AppInfoContract.AppDetailView mView) {
        super(mModel, mView);
    }

}
