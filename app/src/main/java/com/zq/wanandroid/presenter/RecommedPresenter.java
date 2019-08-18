package com.zq.wanandroid.presenter;

import com.zq.wanandroid.http.responsebean.AppInfo;
import com.zq.wanandroid.http.responsebean.BaseBean;
import com.zq.wanandroid.model.RecommendModel;
import com.zq.wanandroid.presenter.contract.RecommendContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhangqi on 2019/8/16
 */
public class RecommedPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {


    public RecommedPresenter(RecommendModel mModel, RecommendContract.View mView) {
        super(mModel, mView);
    }

    public void requestData() {
        mModel.getApps(new Callback<BaseBean<AppInfo>>() {
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
