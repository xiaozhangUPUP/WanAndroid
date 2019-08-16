package com.zq.wanandroid.presenter.contract;

import com.zq.wanandroid.http.requestbean.AppInfo;
import com.zq.wanandroid.presenter.BasePresenter;
import com.zq.wanandroid.presenter.BaseView;

import java.util.List;

/**
 * Created by zhangqi on 2019/8/16
 */
public interface RecommendContract {

    interface View extends BaseView {
        void showResult(List<AppInfo> appInfoList);
    }

    interface Presenter extends BasePresenter {
        void requestData();
    }
}
