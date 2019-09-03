package com.zq.wanandroid.ui.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zq.wanandroid.R;
import com.zq.wanandroid.common.RxSchedulers;
import com.zq.wanandroid.di.component.AppComponent;
import com.zq.wanandroid.di.component.DaggerSearchComponent;
import com.zq.wanandroid.di.module.SearchModule;
import com.zq.wanandroid.http.responsebean.SearchResult;
import com.zq.wanandroid.presenter.SearchPresenter;
import com.zq.wanandroid.presenter.contract.SearchContract;
import com.zq.wanandroid.ui.adapter.AppInfoAdapter;
import com.zq.wanandroid.ui.adapter.SearchHistoryAdatper;
import com.zq.wanandroid.ui.adapter.SuggestionAdapter;
import com.zq.wanandroid.ui.widget.SpaceItemDecoration2;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

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

    private Disposable disposable;

    SuggestionAdapter mSuggestionAdapter;

    private AppInfoAdapter mAppInfoAdapter;

    @Override
    protected void init() {
        presenter.showHistory();
        initView();

        setupSearchView();

        setupSuggestionRecyclerView();

        initSearchResultRecycleView();
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
        mSuggestionAdapter.setNewData(list);
        recyclerViewSuggestion.setVisibility(View.VISIBLE);

        layoutHistory.setVisibility(View.GONE);
        recyclerViewResult.setVisibility(View.GONE);
    }

    @Override
    public void showSearchResult(SearchResult result) {

        mAppInfoAdapter.setNewData(result.getListApp());
        recyclerViewSuggestion.setVisibility(View.GONE);
        layoutHistory.setVisibility(View.GONE);
        recyclerViewResult.setVisibility(View.VISIBLE);
    }

    private void setupSuggestionRecyclerView() {

        mSuggestionAdapter = new SuggestionAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewSuggestion.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        recyclerViewSuggestion.addItemDecoration(itemDecoration);

        recyclerViewSuggestion.setAdapter(mSuggestionAdapter);


        recyclerViewSuggestion.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                search(mSuggestionAdapter.getItem(position));
            }
        });

    }

    private void initSearchResultRecycleView() {
        mAppInfoAdapter = AppInfoAdapter.builder().showBrief(false).showCategoryName(true).build();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewResult.setLayoutManager(layoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerViewResult.addItemDecoration(itemDecoration);

        recyclerViewResult.setAdapter(mAppInfoAdapter);

        recyclerViewResult.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    private void setupSearchView() {


        RxView.clicks(searchClear).subscribe(new Consumer<Object>() {

            @Override
            public void accept(@NonNull Object o) throws Exception {

                searchInput.setText("");

                layoutHistory.setVisibility(View.VISIBLE);
                recyclerViewSuggestion.setVisibility(View.GONE);
                recyclerViewResult.setVisibility(View.GONE);
            }
        });


        RxTextView.editorActions(searchInput).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {

                search(searchInput.getText().toString().trim());
            }
        });


        disposable = RxTextView.textChanges(searchInput)
                .debounce(400, TimeUnit.MILLISECONDS)
                .compose(RxSchedulers.<CharSequence>io_main())
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(@NonNull CharSequence charSequence) throws Exception {
                        searchClear.setVisibility(charSequence.toString().trim().length() > 0 ? View.VISIBLE : View.GONE);
                        return charSequence.toString().trim().length() > 0;
                    }
                })
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(@NonNull CharSequence charSequence) throws Exception {

                        if (charSequence.length() > 0) {
                            searchClear.setVisibility(View.VISIBLE);
                        } else {
                            searchClear.setVisibility(View.GONE);
                        }

                        presenter.getSuggestions(charSequence.toString().trim());

                    }
                });
    }

    private void initView() {

        toolbar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.white)
                        )
        );

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        //        mActionClearBtn.setImageDrawable(new IconicsDrawable(this, Ionicons.Icon.ion_ios_close_empty)
        //                .color(ContextCompat.getColor(this, R.color.white)).sizeDp(16));
        //
        //
        //        mBtnClear.setImageDrawable(new IconicsDrawable(this, Ionicons.Icon.ion_ios_trash_outline)
        //                .color(ContextCompat.getColor(this, R.color.md_grey_600)).sizeDp(16));

        //        RxView.clicks(btnClear).subscribe();new Consumer<Object>() {
        //
        //            @Override
        //            public void accept(@NonNull Object o) throws Exception {
        //
        //            }
        //        }
    }

    private void initHistoryRecycleView(List<String> list) {
        historyAdatper = new SearchHistoryAdatper();
        historyAdatper.setNewData(list);

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
