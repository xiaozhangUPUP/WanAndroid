package com.zq.wanandroid.presenter;

import com.zq.wanandroid.http.requestbean.AppInfo;
import com.zq.wanandroid.http.requestbean.BaseBean;
import com.zq.wanandroid.model.RecommendModel;
import com.zq.wanandroid.presenter.contract.RecommendContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhangqi on 2019/8/16
 */
public class RecommedPresenter implements RecommendContract.Presenter {

    RecommendModel model;
    RecommendContract.View mView;

    public RecommedPresenter(RecommendContract.View mView, RecommendModel model) {
        this.mView = mView;
        this.model = model;
    }

    @Override
    public void requestData() {
        model.getApps(new Callback<BaseBean<AppInfo>>() {
            @Override
            public void onResponse(Call<BaseBean<AppInfo>> call, Response<BaseBean<AppInfo>> response) {
                mView.showResult(response.body().getDatas());
            }

            @Override
            public void onFailure(Call<BaseBean<AppInfo>> call, Throwable t) {

            }
        });
    }
}
