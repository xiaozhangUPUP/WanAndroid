package com.zq.wanandroid.model;

import com.zq.wanandroid.http.ApiService;
import com.zq.wanandroid.http.requestbean.LoginRequestBean;
import com.zq.wanandroid.http.responsebean.AppInfo;
import com.zq.wanandroid.http.responsebean.BaseBean;
import com.zq.wanandroid.http.responsebean.IndexBean;
import com.zq.wanandroid.http.responsebean.LoginBean;
import com.zq.wanandroid.http.responsebean.PageBean;

import io.reactivex.Observable;

public class LoginModel {

    private ApiService apiService;

    public LoginModel(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<BaseBean<LoginBean>> login(String phone, String pwd) {

        LoginRequestBean param = new LoginRequestBean();
        param.setEmail(phone);
        param.setPassWord(pwd);

        return apiService.login(param);
    }

}
