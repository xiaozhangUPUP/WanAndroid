package com.zq.wanandroid.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zq.wanandroid.MyApplication;
import com.zq.wanandroid.R;
import com.zq.wanandroid.common.Constants;
import com.zq.wanandroid.common.util.DensityUtil;
import com.zq.wanandroid.di.component.AppComponent;
import com.zq.wanandroid.http.responsebean.AppInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppDetailActivity extends BaseActivity {

    @BindView(R.id.view_temp)
    ImageView viewTemp;
//    @BindView(R.id.img_icon)
//    ImageView imgIcon;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    @BindView(R.id.toolbar_layout)
//    CollapsingToolbarLayout toolbarLayout;
//    @BindView(R.id.app_bar)
//    AppBarLayout appBar;
//    @BindView(R.id.view_content)
//    FrameLayout viewContent;
//    @BindView(R.id.txt_name)
//    TextView txtName;
//    @BindView(R.id.view_coordinator)
//    CoordinatorLayout viewCoordinator;

    private View cacheView;
    private AppInfo appInfo;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void init() {
        parseIntent();
        initView();

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initView() {
        cacheView = ((MyApplication) getApplication()).getView();
//        Glide.with(this)
//                .load(Constants.BASE_IMG_URL + appInfo.getIcon())
//                .into(imgIcon);
//        txtName.setText(appInfo.getDisplayName());
//
//        toolbar.setNavigationIcon(
//                new IconicsDrawable(this)
//                        .icon(Ionicons.Icon.ion_ios_arrow_back)
//                        .sizeDp(16));
//
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        // 获取缓存图像
        Bitmap viemBitmapCache = getViemBitmapCache(cacheView);
        if (viemBitmapCache != null) {
            viewTemp.setBackground(new BitmapDrawable(viemBitmapCache));
        }

        // 固定缓存图像的位置
        setCacheViewLocation(cacheView);

        // View 竖直方向展开
        open();

    }

    private void open() {
        int h = DensityUtil.getScreenH(this);
        ObjectAnimator animator = ObjectAnimator.ofFloat(viewTemp, "scaleY", 1f, h);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                viewTemp.setBackgroundColor(getResources().getColor(R.color.white));
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                viewTemp.setVisibility(View.GONE);
            }

        });
        animator.setStartDelay(500);
        animator.setDuration(100);
        animator.start();
    }

    private void setCacheViewLocation(View cacheView) {
        int[] outLocation = new int[2];

        // 拿到原来view的坐标
        cacheView.getLocationOnScreen(outLocation);

        int left = outLocation[0];
        int top = outLocation[1];

        int statusBarH = DensityUtil.getStatusBarH(this);

//        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(cacheView.getLayoutParams());
//        marginLayoutParams.leftMargin = left;
//        marginLayoutParams.topMargin = top - statusBarH;
//        marginLayoutParams.width = cacheView.getWidth();
//        marginLayoutParams.height = cacheView.getHeight();
//        int dif = DensityUtil.getScreenH(this) - top;
//        if (dif < cacheView.getHeight()) {
//            //            marginLayoutParams.topMargin = DensityUtil.getScreenH(this) - view.getHeight() - statusBarH;
//            marginLayoutParams.topMargin = top - statusBarH - (cacheView.getHeight() - dif);
//        }
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(marginLayoutParams);


        LinearLayout.LayoutParams marginLayoutParams = new LinearLayout.LayoutParams(cacheView.getLayoutParams());
        marginLayoutParams.leftMargin = left;
        marginLayoutParams.topMargin = top - statusBarH;
        marginLayoutParams.width = cacheView.getWidth();
        marginLayoutParams.height = cacheView.getHeight();
        int dif = DensityUtil.getScreenH(this) - top;
        if (dif < cacheView.getHeight()) {
            //            marginLayoutParams.topMargin = DensityUtil.getScreenH(this) - view.getHeight() - statusBarH;
            marginLayoutParams.topMargin = top - statusBarH - (cacheView.getHeight() - dif);
        }

        // 重新设置view的位置
        viewTemp.setLayoutParams(marginLayoutParams);

    }

    private void parseIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            appInfo = (AppInfo) intent.getSerializableExtra(Constants.ITEM_APP_INFO_OBJ);
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_app_detail;
    }

    @Override
    protected void setAppcomponent(AppComponent appcomponent) {

    }


    private Bitmap getViemBitmapCache(View view) {

        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        if (bitmap == null) {
            return null;
        }

        bitmap = Bitmap.createBitmap(bitmap);
        view.destroyDrawingCache();
        return bitmap;
    }

}
