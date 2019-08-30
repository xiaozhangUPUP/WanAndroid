package com.zq.wanandroid.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zq.wanandroid.R;
import com.zq.wanandroid.di.component.AppComponent;
import com.zq.wanandroid.http.responsebean.Subject;
import com.zq.wanandroid.ui.fragment.SubjectDetailFragment;
import com.zq.wanandroid.ui.fragment.SubjectFragment;

import butterknife.BindView;

public class SubjectActivity extends BaseActivity {
    public static final int FRAGMENT_SUBJECT = 0;
    public static final int FRAGMENT_DETAIL = 1;

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.activity_subject)
    LinearLayout activitySubject;

    private FragmentManager fragmentManager;
    private SubjectFragment subjectFragment;
    private SubjectDetailFragment subjectDetailFragment;

    private Subject mSubject;

    private int curFragmentType;

    @Override
    protected void init() {
        fragmentManager = getSupportFragmentManager();
        initToolBar();

        showSubjectFragment();
    }

    private void showSubjectFragment() {
        curFragmentType = FRAGMENT_SUBJECT;
        toolBar.setTitle("主题");
        FragmentTransaction transaction =
                fragmentManager.beginTransaction();
        if (subjectFragment == null) {
            subjectFragment = new SubjectFragment();
        }
        if (!subjectFragment.isAdded()) {
            transaction.add(R.id.content, subjectFragment);
        } else {
            transaction.show(subjectFragment);
        }

        transaction.commit();
    }

    public void showSubjectDetailFragment(Subject subject) {
        curFragmentType = FRAGMENT_DETAIL;
        toolBar.setTitle(subject.getTitle());
        FragmentTransaction transaction =
                fragmentManager.beginTransaction();
        transaction.add(R.id.content, new SubjectDetailFragment(subject));
        transaction.commit();
    }

    @Override
    protected int setLayout() {
        return R.layout.template_toolbar_framelayout;
    }

    @Override
    protected void setAppcomponent(AppComponent appcomponent) {

    }

    private void initToolBar() {
        toolBar.setNavigationIcon(new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_arrow_back)
                .color(getResources().getColor(R.color.white))
                .sizeDp(16));

        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curFragmentType == FRAGMENT_SUBJECT) {
                    finish();
                } else {
                    fragmentManager.popBackStack();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (curFragmentType == FRAGMENT_SUBJECT) {
            finish();
        } else {
            fragmentManager.popBackStack();
        }
    }
}
