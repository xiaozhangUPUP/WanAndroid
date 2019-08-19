package com.zq.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zq.wanandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhangqi on 2019/8/19
 */
public class GuideFragment extends Fragment {

    public static final String IMG_ID = "IMG_ID";
    public static final String COLOR_ID = "COLOR_ID";
    public static final String TEXT_ID = "TEXT_ID";
    @BindView(R.id.imgView)
    ImageView imgView;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.rootView)
    LinearLayout rootView;
    Unbinder unbinder;

    public static GuideFragment newInstance(int imgResId, int colorResId, int textResId) {
        GuideFragment guideFragment = new GuideFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(IMG_ID, imgResId);
        bundle.putInt(COLOR_ID, colorResId);
        bundle.putInt(TEXT_ID, textResId);
        guideFragment.setArguments(bundle);
        return guideFragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        Bundle arguments = getArguments();
        int imgResId = arguments.getInt(IMG_ID);
        int colorResId = arguments.getInt(COLOR_ID);
        int textResId = arguments.getInt(TEXT_ID);

        rootView.setBackgroundColor(getResources().getColor(colorResId));
        imgView.setBackgroundResource(imgResId);
        text.setText(textResId);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
