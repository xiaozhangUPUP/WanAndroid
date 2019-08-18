package com.zq.wanandroid.presenter;

/**
 * Created by zhangqi on 2019/8/16
 */
public class BasePresenter<M, V extends BaseView> {

    protected M mModel;
    protected V mView;

    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;
    }
}
