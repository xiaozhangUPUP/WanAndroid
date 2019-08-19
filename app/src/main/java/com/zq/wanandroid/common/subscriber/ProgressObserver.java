package com.zq.wanandroid.common.subscriber;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.JsonParseException;
import com.zq.wanandroid.common.exception.ApiException;
import com.zq.wanandroid.common.exception.BaseException;
import com.zq.wanandroid.common.exception.ErrorMessageFactory;
import com.zq.wanandroid.presenter.BaseView;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class ProgressObserver<T> extends ErrorHandlerObserver<T> {

    private BaseView view;

    public ProgressObserver(Context context, BaseView view) {
        super(context);
        this.view = view;
    }

    @Override
    public void onSubscribe(Disposable d) {
        view.showLoading();
    }

    @Override
    public void onComplete() {
        view.dismissLoading();
    }

    @Override
    public void onError(Throwable e) {
        String errorMsg = null;
        BaseException exception = new BaseException();
        if (e instanceof ApiException) {
            errorMsg = ((ApiException) e).getDisplayMessage();
        } else if (e instanceof SocketException) {
            exception.setCode(BaseException.SOCKET_ERROR);
        } else if (e instanceof JsonParseException) {
            exception.setCode(BaseException.JSON_ERROR);
        } else if (e instanceof SocketTimeoutException) {
            exception.setCode(BaseException.SOCKET_TIMEOUT_ERROR);
        } else if (e instanceof HttpException) {
            exception.setCode(((HttpException) e).code());
        } else {
            exception.setCode(BaseException.UNKNOWN_ERROR);
        }
        if (TextUtils.isEmpty(errorMsg)) {
            errorMsg = ErrorMessageFactory.create(context, exception.getCode());
        }
        view.showError(errorMsg + "\n点击屏幕重试");
    }
}
