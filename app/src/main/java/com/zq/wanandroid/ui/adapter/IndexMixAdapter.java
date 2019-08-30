package com.zq.wanandroid.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zq.wanandroid.R;
import com.zq.wanandroid.http.responsebean.IndexBean;
import com.zq.wanandroid.ui.activity.SubjectActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndexMixAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    public static final int TYPE_BANNER = 1;
    public static final int TYPE_ICON = 2;
    public static final int TYPE_APPS = 3;
    public static final int TYPE_GAMES = 4;

    private Context context;
    private IndexBean indexBean;

    public IndexMixAdapter(Context context, IndexBean indexBean) {
        this.context = context;
        this.indexBean = indexBean;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else if (position == 1) {
            return TYPE_ICON;
        } else if (position == 2) {
            return TYPE_APPS;
        } else if (position == 3) {
            return TYPE_GAMES;
        }
        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER) {
            View view = LayoutInflater.from(context).inflate(R.layout.template_banner, parent, false);
            return new BannerViewHolder(view);
        } else if (viewType == TYPE_ICON) {
            View view = LayoutInflater.from(context).inflate(R.layout.template_nav_icon, parent, false);
            return new IconViewHolder(view);
        } else if (viewType == TYPE_APPS) {
            return new AppViewHolder(LayoutInflater.from(context).inflate(R.layout.template_recyleview_with_title, null, false), TYPE_APPS);
        } else if (viewType == TYPE_GAMES) {
            return new AppViewHolder(LayoutInflater.from(context).inflate(R.layout.template_recyleview_with_title, null, false), TYPE_GAMES);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            List<com.zq.wanandroid.http.responsebean.Banner> banners = indexBean.getBanners();
            List<String> urls = new ArrayList<>();
            for (com.zq.wanandroid.http.responsebean.Banner banner : banners) {
                urls.add(banner.getThumbnail());
            }
            bannerViewHolder.banner.setImages(urls);
            bannerViewHolder.banner.start();

        } else if (position == 1) {
            IconViewHolder iconViewHolder = (IconViewHolder) holder;
            iconViewHolder.layoutHotApp.setOnClickListener(this);
            iconViewHolder.layoutHotGame.setOnClickListener(this);
            iconViewHolder.layoutHotSubject.setOnClickListener(this);
        } else {
            AppViewHolder appViewHolder = (AppViewHolder) holder;
            AppInfoAdapter appInfoAdapter = AppInfoAdapter
                    .builder()
                    .showCategoryName(true)
                    .build();
            if (appViewHolder.viewType == TYPE_APPS) {
                appViewHolder.text.setText(R.string.hot_app);
                appInfoAdapter.addData(indexBean.getRecommendApps());
            } else {
                appViewHolder.text.setText(R.string.hot_game);
                appInfoAdapter.addData(indexBean.getRecommendGames());
            }
            appViewHolder.recyclerView.setAdapter(appInfoAdapter);
            appViewHolder.recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
                @Override
                public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.layout_hot_subject) {
            context.startActivity(new Intent(context, SubjectActivity.class));
        }
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

        }

    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        Banner banner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //设置图片加载器
            banner.setImageLoader(new GlideImageLoader());
        }
    }

    class IconViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.layout_hot_app)
        LinearLayout layoutHotApp;
        @BindView(R.id.layout_hot_game)
        LinearLayout layoutHotGame;
        @BindView(R.id.layout_hot_subject)
        LinearLayout layoutHotSubject;

        public IconViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class AppViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.recycler_view)
        RecyclerView recyclerView;

        int viewType;

        public AppViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.viewType = viewType;
            initRecyclerView();
        }

        private void initRecyclerView() {

            recyclerView.setLayoutManager(new LinearLayoutManager(context) {

                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        }
    }

}
