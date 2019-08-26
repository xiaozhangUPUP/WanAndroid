package com.zq.wanandroid.ui.activity;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zq.wanandroid.R;
import com.zq.wanandroid.common.Constants;
import com.zq.wanandroid.http.responsebean.Category;

/**
 * Created by zhangqi on 2019/3/28
 */
public class CategoryAdapter extends BaseQuickAdapter<Category, BaseViewHolder> {


    public CategoryAdapter() {
        super(R.layout.template_catetory);
    }

    @Override
    protected void convert(BaseViewHolder helper, Category item) {
        helper.setText(R.id.text_name, item.getName());
        ImageView icon = helper.getView(R.id.img_icon);
        Glide.with(icon.getContext()).load(Constants.BASE_IMG_URL + item.getIcon()).into(icon);

    }
}
