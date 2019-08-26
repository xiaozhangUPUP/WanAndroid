package com.zq.wanandroid.presenter;

import com.zq.wanandroid.common.Constants;
import com.zq.wanandroid.common.RxHttpResponseCompose;
import com.zq.wanandroid.common.subscriber.ErrorHandlerObserver;
import com.zq.wanandroid.common.subscriber.ProgressObserver;
import com.zq.wanandroid.common.util.ACache;
import com.zq.wanandroid.common.util.VerificationUtils;
import com.zq.wanandroid.http.responsebean.Category;
import com.zq.wanandroid.http.responsebean.LoginBean;
import com.zq.wanandroid.model.CategoryModel;
import com.zq.wanandroid.model.LoginModel;
import com.zq.wanandroid.presenter.contract.CategoryContract;
import com.zq.wanandroid.presenter.contract.LoginContract;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class CategoryPresenter extends BasePresenter<CategoryModel, CategoryContract.View> {


    public CategoryPresenter(CategoryModel mModel, CategoryContract.View mView) {
        super(mModel, mView);
    }

    public void requestCategories() {
        mModel.getCategories()
                .compose(RxHttpResponseCompose.<List<Category>>compatResult())
                .subscribe(new ProgressObserver<List<Category>>(mContext, mView) {
                    @Override
                    public void onNext(List<Category> categories) {
                        mView.showResult(categories);
                    }
                });
    }
}
