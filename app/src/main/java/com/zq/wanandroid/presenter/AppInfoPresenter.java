package com.zq.wanandroid.presenter;

import com.zq.wanandroid.common.RxHttpResponseCompose;
import com.zq.wanandroid.common.subscriber.ErrorHandlerObserver;
import com.zq.wanandroid.common.subscriber.ProgressObserver;
import com.zq.wanandroid.http.responsebean.AppInfo;
import com.zq.wanandroid.http.responsebean.BaseBean;
import com.zq.wanandroid.http.responsebean.Category;
import com.zq.wanandroid.http.responsebean.IndexBean;
import com.zq.wanandroid.http.responsebean.PageBean;
import com.zq.wanandroid.model.AppInfoModel;
import com.zq.wanandroid.presenter.contract.AppInfoContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zhangqi on 2019/8/16
 */
public class AppInfoPresenter extends BasePresenter<AppInfoModel, AppInfoContract.AppInfoView> {

    public static final int TOP_LIST = 1;
    public static final int GAME = 2;
    public static final int CATEGORY = 3;

    public static final int FEATURED = 0;
    public static final int TOPLIST = 1;
    public static final int NEWLIST = 2;


    public AppInfoPresenter(AppInfoModel mModel, AppInfoContract.AppInfoView mView) {
        super(mModel, mView);
    }

    public void requestData(int type, int page) {
        request(type, page, 0, 0);
    }

    public void requestCategoryApps(int categoryId, int page, int fragType) {
        request(CATEGORY, page, categoryId, fragType);
    }

    public void request(int type, int page, int categoryId, int fragType) {
        Observer observer = null;
        if (page == 0) {
            observer = new ProgressObserver<PageBean<AppInfo>>(mContext, mView) {
                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    mView.showResult(appInfoPageBean);
                }
            };
        } else {
            observer = new ErrorHandlerObserver<PageBean<AppInfo>>(mContext) {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    mView.showResult(appInfoPageBean);
                }

                @Override
                public void onComplete() {
                    mView.onLoadMoreComplete();
                }
            };
        }

        Observable observable = getObservable(type, page, categoryId, fragType);

        observable
                .compose(RxHttpResponseCompose.<PageBean<AppInfo>>compatResult())
                .subscribe(observer);


    }

    private Observable<BaseBean<PageBean<AppInfo>>> getObservable(int type, int page, int categoryId, int fragType) {
        switch (type) {
            case TOP_LIST:
                return mModel.topList(page);
            case GAME:
                return mModel.games(page);
            case CATEGORY:
                if (fragType == FEATURED) {
                    return mModel.getFeaturedAppsByCategory(categoryId, page);
                } else if (fragType == TOPLIST) {
                    return mModel.getTopListAppsByCategory(categoryId, page);
                } else if (fragType == NEWLIST) {
                    return mModel.getNewListAppsByCategory(categoryId, page);
                }
            default:
                return Observable.empty();
        }

    }

}
