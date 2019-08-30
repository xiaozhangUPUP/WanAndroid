package com.zq.wanandroid.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zq.wanandroid.R;
import com.zq.wanandroid.common.Constants;
import com.zq.wanandroid.http.responsebean.Subject;


public class SubjectAdapter extends BaseQuickAdapter<Subject, BaseViewHolder> {

    public SubjectAdapter() {
        super(R.layout.template_subject_imageview);
    }

    @Override
    protected void convert(BaseViewHolder helper, Subject item) {
        ImageView imageView = helper.getView(R.id.imageview);
        String url = Constants.BASE_IMG_URL + item.getMticon();
        Glide.with(mContext).load(url).into(imageView);

    }
}
