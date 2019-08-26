package com.zq.wanandroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zq.wanandroid.R;
import com.zq.wanandroid.common.Constants;
import com.zq.wanandroid.di.component.AppComponent;
import com.zq.wanandroid.http.responsebean.Category;
import com.zq.wanandroid.ui.adapter.CategoryAppViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAppActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.activity_cateogry_app)
    LinearLayout activityCateogryApp;

    private Category category;

    @Override
    protected void init() {
        parseIntent();
        initTablayout();
    }


    @Override
    protected int setLayout() {
        return R.layout.activity_category;
    }

    @Override
    protected void setAppcomponent(AppComponent appcomponent) {

    }

    private void initTablayout() {
        toolBar.setTitle(category.getName());
        toolBar.setNavigationIcon(new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_arrow_back)
                .sizeDp(16));

        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (category != null) {
            CategoryAppViewPagerAdapter adapter = new CategoryAppViewPagerAdapter(getSupportFragmentManager(), category.getId());
            viewPager.setOffscreenPageLimit(adapter.getCount());
            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
        }

    }

    private void parseIntent() {
        Intent intent = getIntent();
        category = (Category) intent.getSerializableExtra(Constants.CATEGORY);
    }


}
