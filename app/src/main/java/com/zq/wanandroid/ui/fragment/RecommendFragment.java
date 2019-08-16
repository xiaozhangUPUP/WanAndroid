package com.zq.wanandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zq.wanandroid.R;
import com.zq.wanandroid.http.ApiService;
import com.zq.wanandroid.http.requestbean.AppInfo;
import com.zq.wanandroid.http.requestbean.BaseBean;
import com.zq.wanandroid.ui.adapter.RecommendAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhangqi on 2019/8/14
 */
public class RecommendFragment extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getApps("{\"page\":0}").enqueue(new Callback<BaseBean<AppInfo>>() {
            @Override
            public void onResponse(Call<BaseBean<AppInfo>> call, Response<BaseBean<AppInfo>> response) {
                List<AppInfo> datas = response.body().getDatas();
                RecommendAdapter recommendAdapter = new RecommendAdapter(datas, getActivity());
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                //                为RecyclerView设置分割线(这个可以对DividerItemDecoration进行修改，自定义)
                recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recommendAdapter);
            }

            @Override
            public void onFailure(Call<BaseBean<AppInfo>> call, Throwable t) {

            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
