package com.zq.wanandroid.ui.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mikepenz.iconics.IconicsDrawable;
import com.zq.wanandroid.R;
import com.zq.wanandroid.di.component.AppComponent;
import com.zq.wanandroid.common.fonts.AppIcons;
import com.zq.wanandroid.ui.fragment.bottomNav.HomeFragment;
import com.zq.wanandroid.ui.fragment.bottomNav.OtherFragment;
import com.zq.wanandroid.ui.fragment.bottomNav.SettingFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BottomNavActivity extends BaseActivity {

    private static final String TAG = BottomNavActivity.class.getSimpleName();
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    /**
     * 选中的Fragment的对应的位置
     */
    private int position;
    private Fragment mFragment;
    private List<Fragment> fragmentList;
    private IconicsDrawable homeIcon, otherIcon, settingIcon, homeIconChecked, otherIconChecked, settingIconChecked;

    @Override
    protected void init() {
        homeIcon = new IconicsDrawable(this, AppIcons.Icon.appicon_bottm_nav_home);
        otherIcon = new IconicsDrawable(this, AppIcons.Icon.appicon_bottm_nav_other);
        settingIcon = new IconicsDrawable(this, AppIcons.Icon.appicon_bottm_nav_setting);
        homeIconChecked = new IconicsDrawable(this, AppIcons.Icon.appicon_bottm_nav_home).color(Color.RED);
        otherIconChecked = new IconicsDrawable(this, AppIcons.Icon.appicon_bottm_nav_other).color(Color.RED);
        settingIconChecked = new IconicsDrawable(this, AppIcons.Icon.appicon_bottm_nav_setting).color(Color.RED);

        resetToDefaultIcon();
        initFragment();
        setListener();
        navigation.setSelectedItemId(navigation.getMenu().findItem(R.id.navigation_home).getItemId());
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_bottom_nav;
    }

    @Override
    protected void setAppcomponent(AppComponent appcomponent) {
    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new OtherFragment());
        fragmentList.add(new SettingFragment());

    }

    private void switchBottomNavItem(Fragment from, Fragment to) {
        if (from != to) {
            mFragment = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) {
                if (from != null) {
                    ft.hide(from);
                }
                if (to != null) {
                    ft.add(R.id.framelayout, to).commit();
                }
            } else {
                if (from != null) {
                    ft.hide(from);
                }
                if (to != null) {
                    ft.show(to).commit();
                }
            }
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            resetToDefaultIcon();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    item.setIcon(homeIconChecked);
                    position = 0;
                    break;
                case R.id.navigation_other:
                    item.setIcon(otherIconChecked);
                    position = 1;
                    break;
                case R.id.navigation_setting:
                    item.setIcon(settingIconChecked);
                    position = 2;
                    break;
                default:
                    position = 0;
                    break;
            }
            switchBottomNavItem(mFragment, fragmentList.get(position));
            return true;
        }
    };


    private void setListener() {
        navigation.setItemIconTintList(null);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar.inflateMenu(R.menu.tool_bar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolBar_item1:
                        Log.e(TAG, "onOptionsItemSelected: 1111111");
                        return true;
                    case R.id.toolBar_item2:
                        Log.e(TAG, "onOptionsItemSelected: 22222222");
                        return true;
                    case R.id.toolBar_item3:
                        Log.e(TAG, "onOptionsItemSelected: 33333333");
                        return true;
                    default:
                        return false;
                }
            }
        });

    }

    private void resetToDefaultIcon() {
        navigation.getMenu().findItem(R.id.navigation_home).setIcon(homeIcon);
        navigation.getMenu().findItem(R.id.navigation_other).setIcon(otherIcon);
        navigation.getMenu().findItem(R.id.navigation_setting).setIcon(settingIcon);
    }

}
