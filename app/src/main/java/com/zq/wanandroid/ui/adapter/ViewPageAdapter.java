package com.zq.wanandroid.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zq.wanandroid.bean.FragmentInfo;
import com.zq.wanandroid.ui.fragment.CategoryFragment;
import com.zq.wanandroid.ui.fragment.RankingFragment;
import com.zq.wanandroid.ui.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangqi on 2019/8/14
 */
public class ViewPageAdapter extends FragmentStatePagerAdapter {

    private List<FragmentInfo> fragmentList;


    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    private void initFragments() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentInfo("推荐", new RecommendFragment()));
        fragmentList.add(new FragmentInfo("排行", new RankingFragment()));
        fragmentList.add(new FragmentInfo("分类", new CategoryFragment()));
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).getTitle();
    }
}
