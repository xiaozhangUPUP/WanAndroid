package com.zq.wanandroid.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zq.wanandroid.R;
import com.zq.wanandroid.di.component.AppComponent;
import com.zq.wanandroid.ui.fragment.SettingFragment;

import butterknife.BindView;

/**
 * Created by zhangqi on 2019/9/3
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.activity_subject)
    LinearLayout activitySubject;

    @Override
    protected void init() {
        initToolBar();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, new SettingFragment())
                .commit();
    }

    private void initToolBar() {
        toolBar.setNavigationIcon(new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_arrow_back)
                .color(getResources().getColor(R.color.white))
                .sizeDp(16));
        toolBar.setTitle("设置");
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.template_toolbar_framelayout;
    }

    @Override
    protected void setAppcomponent(AppComponent appcomponent) {

    }

}
