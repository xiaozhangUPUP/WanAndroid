package com.zq.wanandroid.http;

import com.zq.wanandroid.http.requestbean.AppInfo;
import com.zq.wanandroid.http.requestbean.BaseBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zhangqi on 2019/8/16
 */
public interface ApiService {
    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

    @GET("featured")
    public Call<BaseBean<AppInfo>> getApps(@Query("p") String jsonParam);
}
