package com.zq.wanandroid.presenter.contract;


import com.zq.wanandroid.http.responsebean.SearchResult;
import com.zq.wanandroid.presenter.BaseView;

import java.util.List;


public class SearchContract {

    public interface SearchView extends BaseView {

        void showSearchHistory(List<String> list);

        void showSuggestions(List<String> list);

        void showSearchResult(SearchResult result);

    }

}
