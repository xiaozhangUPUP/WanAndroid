package com.zq.wanandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.zq.wanandroid.R;

public abstract class ProgressActivity extends AppCompatActivity {

    FrameLayout rootView;
    View progressView;
    FrameLayout contentView;
    View emptyView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.fragment_progress, null, false);
        progressView = rootView.findViewById(R.id.view_progress);
        contentView = rootView.findViewById(R.id.view_content);
        emptyView = rootView.findViewById(R.id.view_empty);
        setContentView(rootView);
        setRealContentView();
    }

    private void setRealContentView() {
        LayoutInflater.from(this).inflate(setLayout(), rootView, true);
    }

    protected abstract int setLayout();
}
