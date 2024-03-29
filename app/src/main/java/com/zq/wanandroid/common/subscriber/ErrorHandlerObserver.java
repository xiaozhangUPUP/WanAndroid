package com.zq.wanandroid.common.subscriber;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.zq.wanandroid.common.exception.ApiException;
import com.zq.wanandroid.common.exception.BaseException;
import com.zq.wanandroid.common.exception.ErrorMessageFactory;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

/**
 * Created by zhangqi on 2019/8/19
 */
public abstract class ErrorHandlerObserver<T> extends BaseObserver<T> {

    protected Context context;

    public ErrorHandlerObserver(Context context) {
        this.context = context;
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        BaseException exception = new BaseException();
        if (e instanceof ApiException) {
            exception.setCode(((ApiException) e).getCode());
            exception.setDisplayMessage(((ApiException) e).getDisplayMessage());
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
        if (TextUtils.isEmpty(exception.getDisplayMessage())) {
            exception.setDisplayMessage(ErrorMessageFactory.create(context, exception.getCode()));
        }
        showErrorMessage(exception);
    }

    public void showErrorMessage(BaseException e) {
        Toast.makeText(context, e.getDisplayMessage(), Toast.LENGTH_SHORT).show();
    }

}
