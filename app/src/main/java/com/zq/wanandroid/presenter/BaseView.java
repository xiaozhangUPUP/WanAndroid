package com.zq.wanandroid.presenter;

/**
 * Created by zhangqi on 2019/8/16
 */
public interface BaseView {

    void showLoading();

    void showError(String errorMsg);

    void dismissLoading();
}
