package com.zq.wanandroid.presenter;


import com.zq.wanandroid.common.RxHttpResponseCompose;
import com.zq.wanandroid.common.subscriber.ProgressObserver;
import com.zq.wanandroid.common.util.ACache;
import com.zq.wanandroid.http.responsebean.SearchResult;
import com.zq.wanandroid.model.SearchModel;
import com.zq.wanandroid.presenter.contract.SearchContract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class SearchPresenter extends BasePresenter<SearchModel, SearchContract.SearchView> {

    @Inject
    public SearchPresenter(SearchModel mModel, SearchContract.SearchView mView) {
        super(mModel, mView);
    }

    public void getSuggestions(String keyword) {
        saveSearchHistory(keyword);

        mModel.getSuggestion(keyword)
                .compose(RxHttpResponseCompose.<List<String>>compatResult())
                .subscribe(new ProgressObserver<List<String>>(mContext, mView) {
                    @Override
                    public void onNext(List<String> suggestions) {

                        mView.showSuggestions(suggestions);
                    }
                });

    }


    public void search(String keyword) {

        saveSearchHistory(keyword);

        mModel.search(keyword)
                .compose(RxHttpResponseCompose.<SearchResult>compatResult())
                .subscribe(new ProgressObserver<SearchResult>(mContext, mView) {
                    @Override
                    public void onNext(SearchResult searchResult) {
                        mView.showSearchResult(searchResult);
                    }
                });

    }

    private void saveSearchHistory(String keyword) {

        // save to database
        ArrayList<String> list = (ArrayList<String>) ACache.get(mContext).getAsObject("keywordList");
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(0, keyword);
        ACache.get(mContext).put("keywordList", (Serializable) list);
    }


    public void showHistory() {

        // get search history from  database


//        List<String> list = new ArrayList<>();
//        list.add("地图");
//        list.add("KK");
//        list.add("爱奇艺");
//        list.add("播放器");
//        list.add("支付宝");
//        list.add("微信");
//        list.add("QQ");
//        list.add("TV");
//        list.add("直播");
//        list.add("妹子");
//        list.add("美女");
        ArrayList<String> list = (ArrayList<String>) ACache.get(mContext).getAsObject("keywordList");


        mView.showSearchHistory(list);


    }

}
