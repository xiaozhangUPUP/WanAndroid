package com.zq.wanandroid.presenter;

import com.zq.wanandroid.common.Constants;
import com.zq.wanandroid.common.RxHttpResponseCompose;
import com.zq.wanandroid.common.subscriber.ErrorHandlerObserver;
import com.zq.wanandroid.common.subscriber.ProgressObserver;
import com.zq.wanandroid.common.util.ACache;
import com.zq.wanandroid.common.util.VerificationUtils;
import com.zq.wanandroid.http.responsebean.IndexBean;
import com.zq.wanandroid.http.responsebean.LoginBean;
import com.zq.wanandroid.model.AppInfoModel;
import com.zq.wanandroid.model.LoginModel;
import com.zq.wanandroid.presenter.contract.AppInfoContract;
import com.zq.wanandroid.presenter.contract.LoginContract;

import io.reactivex.disposables.Disposable;

public class LoginPresenter extends BasePresenter<LoginModel, LoginContract.View> {

    public LoginPresenter(LoginModel mModel, LoginContract.View mView) {
        super(mModel, mView);
    }

    public void login(String phone, String password) {
        if (!VerificationUtils.matcherPhoneNum(phone)) {
            mView.checkPhoneError();
            return;
        } else {
            mView.checkPhoneSuccess();
        }

        mModel.login(phone, password)
                .compose(RxHttpResponseCompose.<LoginBean>compatResult())
                .subscribe(new ErrorHandlerObserver<LoginBean>(mContext) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        mView.loginSuccess(loginBean);
                        saveUser(loginBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.dismissLoading();
                    }

                    @Override
                    public void onComplete() {
                        mView.dismissLoading();
                    }
                });

    }

    private void saveUser(LoginBean loginBean) {
        ACache aCache = ACache.get(mContext);
        aCache.put(Constants.TOKEN, loginBean.getToken());
        aCache.put(Constants.USER, loginBean.getUser());
    }

}
