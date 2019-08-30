package com.zq.wanandroid.presenter;

import com.zq.wanandroid.common.RxHttpResponseCompose;
import com.zq.wanandroid.common.subscriber.ProgressObserver;
import com.zq.wanandroid.http.responsebean.Category;
import com.zq.wanandroid.model.CategoryModel;
import com.zq.wanandroid.presenter.contract.CategoryContract;

import java.util.List;

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
