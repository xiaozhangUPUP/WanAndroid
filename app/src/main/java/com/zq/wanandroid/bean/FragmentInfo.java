package com.zq.wanandroid.bean;

import android.support.v4.app.Fragment;

/**
 * Created by zhangqi on 2019/8/15
 */
public class FragmentInfo {
    private String title;
    private Fragment fragment;

    public FragmentInfo(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
