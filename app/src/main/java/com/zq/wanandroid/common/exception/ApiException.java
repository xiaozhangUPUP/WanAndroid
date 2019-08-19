package com.zq.wanandroid.common.exception;

/**
 * Created by zhangqi on 2019/8/19
 */
public class ApiException extends BaseException {

    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
    }
}
