package com.zq.wanandroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zq.wanandroid.R;
import com.zq.wanandroid.common.Constants;
import com.zq.wanandroid.common.util.ACache;
import com.zq.wanandroid.common.util.DensityUtil;
import com.zq.wanandroid.ui.adapter.GuideViewPagerAdapter;
import com.zq.wanandroid.ui.fragment.GuideFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends AppCompatActivity {

    @BindView(R.id.guide_view_pager)
    ViewPager guideViewPager;
    @BindView(R.id.guide_btn_enter)
    Button guideBtnEnter;
    @BindView(R.id.indicator)
    LinearLayout indicator;
    private GuideViewPagerAdapter guideViewPagerAdapter;

    private int prePosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        if (!TextUtils.isEmpty(ACache.get(this).getAsString(Constants.IS_SHOW_GUIDE))) {
            jumpToHome();
            finish();
            return;
        }

        initData();
    }

    private void initData() {
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(GuideFragment.newInstance(R.drawable.guide_1, R.color.color_bg_guide1, R.string.guide_1));
        fragmentList.add(GuideFragment.newInstance(R.drawable.guide_2, R.color.color_bg_guide2, R.string.guide_2));
        fragmentList.add(GuideFragment.newInstance(R.drawable.guide_3, R.color.color_bg_guide3, R.string.guide_3));

        for (int i = 0; i < fragmentList.size(); i++) {
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.guide_drawable_selector);
            int width = DensityUtil.dip2px(this, 8);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
                params.leftMargin = width;
            }
            point.setLayoutParams(params);
            indicator.addView(point);
        }

        guideViewPagerAdapter = new GuideViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        guideViewPager.setAdapter(guideViewPagerAdapter);
        guideViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                indicator.getChildAt(prePosition).setEnabled(false);
                indicator.getChildAt(position).setEnabled(true);
                prePosition = position;
                guideBtnEnter.setVisibility(position == guideViewPagerAdapter.getCount() - 1 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick(R.id.guide_btn_enter)
    public void onViewClicked() {
        ACache.get(this)
                .put(Constants.IS_SHOW_GUIDE, "false");
        jumpToHome();
    }

    private void jumpToHome() {
        startActivity(new Intent(GuideActivity.this, BottomNavActivity.class));
        finish();
    }
}
