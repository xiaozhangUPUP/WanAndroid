package com.zq.wanandroid.presenter.contract;

import com.zq.wanandroid.http.responsebean.AppInfo;
import com.zq.wanandroid.http.responsebean.Category;
import com.zq.wanandroid.http.responsebean.IndexBean;
import com.zq.wanandroid.http.responsebean.PageBean;
import com.zq.wanandroid.presenter.BasePresenter;
import com.zq.wanandroid.presenter.BaseView;

import java.util.List;

/**
 * Created by zhangqi on 2019/8/16
 */
public interface AppInfoContract {

    interface View extends BaseView {
        void showResult(IndexBean indexBean);
    }

    interface AppInfoView extends BaseView {

        void showResult(PageBean<AppInfo> page);

        void onLoadMoreComplete();

    }

    interface AppDetailView extends BaseView {

        void showAppDetail(AppInfo appInfo);
    }



}
