package com.zq.wanandroid.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zq.wanandroid.MyApplication;
import com.zq.wanandroid.R;
import com.zq.wanandroid.di.component.AppComponent;
import com.zq.wanandroid.presenter.BasePresenter;
import com.zq.wanandroid.presenter.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class ProgressFragment<T extends BasePresenter> extends Fragment implements BaseView {

    FrameLayout rootView;
    View progressView;
    FrameLayout contentView;
    View emptyView;

    protected Activity activity;
    private Unbinder unbinder;
    private TextView textView;
    @Inject
    T presenter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (FrameLayout) inflater.inflate(R.layout.fragment_progress, container, false);
        progressView = rootView.findViewById(R.id.view_progress);
        contentView = rootView.findViewById(R.id.view_content);
        emptyView = rootView.findViewById(R.id.view_empty);
        textView = rootView.findViewById(R.id.text_tip);

        emptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEmptyViewClick();
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRealContentView();

        setAppcomponent(MyApplication.getAppComponent());
        init();
    }

    private void setRealContentView() {
        View realContentView = LayoutInflater.from(activity).inflate(setLayout(), contentView, true);
        unbinder = ButterKnife.bind(this, realContentView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != Unbinder.EMPTY) {
            unbinder.unbind();
        }
    }

    public void showProgressView() {
        showView(R.id.view_progress);
    }

    public void showContentView() {
        showView(R.id.view_content);
    }

    public void showEmptyView() {
        showView(R.id.view_empty);
    }

    public void showEmptyView(String msg) {
        showEmptyView();
        textView.setText(msg);
    }

    public void showView(int viewResId) {
        for (int i = 0; i < rootView.getChildCount(); i++) {
            if (rootView.getChildAt(i).getId() == viewResId) {
                rootView.getChildAt(i).setVisibility(View.VISIBLE);
            } else {
                rootView.getChildAt(i).setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void showLoading() {
        showProgressView();
    }

    @Override
    public void dismissLoading() {
        showContentView();
    }

    @Override
    public void showError(String errorMsg) {
        showEmptyView(errorMsg);
    }

    protected abstract void init();

    protected abstract int setLayout();

    protected abstract void setAppcomponent(AppComponent appcomponent);

    protected abstract void onEmptyViewClick();
}
