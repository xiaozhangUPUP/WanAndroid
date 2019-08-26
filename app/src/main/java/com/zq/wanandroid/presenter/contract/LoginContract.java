package com.zq.wanandroid.presenter.contract;

import com.zq.wanandroid.http.responsebean.AppInfo;
import com.zq.wanandroid.http.responsebean.IndexBean;
import com.zq.wanandroid.http.responsebean.LoginBean;
import com.zq.wanandroid.http.responsebean.PageBean;
import com.zq.wanandroid.presenter.BaseView;

/**
 * Created by zhangqi on 2019/8/16
 */
public interface LoginContract {

    interface View extends BaseView {
        void checkPhoneError();
        void checkPhoneSuccess();

        void loginSuccess(LoginBean bean);
    }

}
