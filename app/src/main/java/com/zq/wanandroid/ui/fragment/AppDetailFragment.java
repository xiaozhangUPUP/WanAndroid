package com.zq.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.zq.wanandroid.R;
import com.zq.wanandroid.common.Constants;
import com.zq.wanandroid.common.util.DateUtils;
import com.zq.wanandroid.di.component.AppComponent;
import com.zq.wanandroid.di.component.DaggerAppDetailComponent;
import com.zq.wanandroid.di.module.AppDetailModule;
import com.zq.wanandroid.http.responsebean.AppInfo;
import com.zq.wanandroid.presenter.AppDetailPresenter;
import com.zq.wanandroid.presenter.contract.AppInfoContract;
import com.zq.wanandroid.ui.adapter.AppInfoAdapter;

import butterknife.BindView;

/**
 * Created by zhangqi on 2019/4/4
 */
public class AppDetailFragment extends ProgressFragment<AppDetailPresenter> implements AppInfoContract.AppDetailView {


    @BindView(R.id.view_gallery)
    LinearLayout viewGallery;
    @BindView(R.id.expandable_text)
    TextView expandableText;
    @BindView(R.id.expand_collapse)
    ImageButton expandCollapse;
    @BindView(R.id.view_introduction)
    ExpandableTextView viewIntroduction;
    @BindView(R.id.txt_update_time)
    TextView txtUpdateTime;
    @BindView(R.id.txt_version)
    TextView txtVersion;
    @BindView(R.id.txt_apk_size)
    TextView txtApkSize;
    @BindView(R.id.txt_publisher)
    TextView txtPublisher;
    @BindView(R.id.txt_publisher2)
    TextView txtPublisher2;
    @BindView(R.id.recycler_view_same_dev)
    RecyclerView recyclerViewSameDev;
    @BindView(R.id.recycler_view_relate)
    RecyclerView recyclerViewRelate;

    private int appId;
    private AppInfoAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_app_detail;
    }

    @Override
    protected void setAppcomponent(AppComponent appcomponent) {
        DaggerAppDetailComponent.builder().appComponent(appcomponent)
                .appDetailModule(new AppDetailModule(this))
                .build().inject(this);

    }

    @Override
    public void onEmptyViewClick() {
        presenter.getAppDetail(appId);
    }

    @Override
    public void init() {
        Bundle bundle = this.getArguments();
        appId = bundle.getInt(Constants.APP_ID);
        // todo
//        presenter.getAppDetail(appId);
    }

    @Override
    public void showAppDetail(AppInfo appInfo) {
        showScreenshot(appInfo.getScreenshot());
        viewIntroduction.setText(appInfo.getIntroduction());
        txtUpdateTime.setText(DateUtils.formatDate(appInfo.getUpdateTime()));
        txtApkSize.setText((appInfo.getApkSize() / 1024 / 1024) + " MB");
        txtVersion.setText(appInfo.getVersionName());
        txtPublisher.setText(appInfo.getPublisherName());
        txtPublisher2.setText(appInfo.getPublisherName());


        adapter = AppInfoAdapter.builder()
                .setLayout(R.layout.template_appinfo2)
                .build();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewSameDev.setLayoutManager(layoutManager);
        adapter.addData(appInfo.getSameDevAppInfoList());
        recyclerViewSameDev.setAdapter(adapter);

        adapter = AppInfoAdapter.builder()
                .setLayout(R.layout.template_appinfo2)
                .build();

        recyclerViewRelate.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter.addData(appInfo.getRelateAppInfoList());
        recyclerViewRelate.setAdapter(adapter);

    }

    private void showScreenshot(String screenshot) {
        String[] urls = screenshot.split(",");
        for (String url : urls) {
            ImageView imageView = (ImageView) LayoutInflater.from(getActivity()).inflate(R.layout.template_imageview, viewGallery, false);
            Glide.with(getActivity()).load(Constants.BASE_IMG_URL + url).into(imageView);
            viewGallery.addView(imageView);
        }
    }


}
