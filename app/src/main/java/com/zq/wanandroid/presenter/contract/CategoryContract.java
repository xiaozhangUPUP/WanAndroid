package com.zq.wanandroid.presenter.contract;

import com.zq.wanandroid.http.responsebean.Category;
import com.zq.wanandroid.http.responsebean.LoginBean;
import com.zq.wanandroid.presenter.BaseView;

import java.util.List;

/**
 * Created by zhangqi on 2019/8/16
 */
public interface CategoryContract {

    interface View extends BaseView {
        void showResult(List<Category> categories);
    }

}
