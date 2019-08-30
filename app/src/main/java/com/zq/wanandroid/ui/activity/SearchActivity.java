package com.zq.wanandroid.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.zq.wanandroid.R;
import com.zq.wanandroid.di.component.AppComponent;
import com.zq.wanandroid.di.component.DaggerSearchComponent;
import com.zq.wanandroid.di.module.SearchModule;
import com.zq.wanandroid.http.responsebean.SearchResult;
import com.zq.wanandroid.presenter.SearchPresenter;
import com.zq.wanandroid.presenter.contract.SearchContract;
import com.zq.wanandroid.ui.adapter.SearchHistoryAdatper;
import com.zq.wanandroid.ui.widget.SpaceItemDecoration2;

import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.SearchView {


    @BindView(R.id.search_input)
    EditText searchInput;
    @BindView(R.id.search_clear)
    ImageView searchClear;
    @BindView(R.id.search_bar)
    LinearLayout searchBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_clear)
    ImageView btnClear;
    @BindView(R.id.recycler_view_history)
    RecyclerView recyclerViewHistory;
    @BindView(R.id.layout_history)
    LinearLayout layoutHistory;
    @BindView(R.id.recycler_view_suggestion)
    RecyclerView recyclerViewSuggestion;
    @BindView(R.id.recycler_view_result)
    RecyclerView recyclerViewResult;
    private SearchHistoryAdatper historyAdatper;

    @Override
    protected void init() {
        presenter.showHistory();
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void setAppcomponent(AppComponent appcomponent) {
        DaggerSearchComponent.builder()
                .appComponent(appcomponent)
                .searchModule(new SearchModule(this))
                .build().inject(this);
    }

    @Override
    public void showSearchHistory(List<String> list) {
        initHistoryRecycleView(list);
        recyclerViewSuggestion.setVisibility(View.GONE);
        layoutHistory.setVisibility(View.VISIBLE);
        recyclerViewResult.setVisibility(View.GONE);
    }


    @Override
    public void showSuggestions(List<String> list) {

    }

    @Override
    public void showSearchResult(SearchResult result) {

    }

    private void initHistoryRecycleView(List<String> list) {
        historyAdatper = new SearchHistoryAdatper();
        historyAdatper.addData(list);


        RecyclerView.LayoutManager lm = new GridLayoutManager(this, 5);
        SpaceItemDecoration2 itemd = new SpaceItemDecoration2(10);
        recyclerViewHistory.addItemDecoration(itemd);

        recyclerViewHistory.setLayoutManager(lm);
        recyclerViewHistory.setAdapter(historyAdatper);

        recyclerViewHistory.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                search((String) historyAdatper.getItem(position));
            }
        });

    }

    private void search(String keyword) {
        presenter.search(keyword);
    }


}
