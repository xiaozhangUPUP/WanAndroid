package com.zq.wanandroid.ui.fragment.bottomNav;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zq.wanandroid.R;
import com.zq.wanandroid.fonts.AppIcons;

/**
 * Created by zhangqi on 2019/8/14
 */
public class SettingFragment extends Fragment {

    private ImageView img;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        img = view.findViewById(R.id.img);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Drawable iconicsDrawable = new IconicsDrawable(getActivity()).icon(AppIcons.Icon.appicon_bottm_nav_home)
                .sizeDp(50);


        img.setImageDrawable(iconicsDrawable);

    }
}
