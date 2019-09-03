package com.zq.wanandroid.model;

import android.util.Log;

import com.zq.wanandroid.http.ApiService;
import com.zq.wanandroid.http.CacheProvider;
import com.zq.wanandroid.http.responsebean.AppInfo;
import com.zq.wanandroid.http.responsebean.BaseBean;
import com.zq.wanandroid.http.responsebean.IndexBean;
import com.zq.wanandroid.http.responsebean.IndexBeanCache;
import com.zq.wanandroid.http.responsebean.PageBean;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.Reply;

/**
 * Created by zhangqi on 2019/8/16
 */
public class AppInfoModel extends BaseModel {


    public AppInfoModel(ApiService apiService, CacheProvider cacheProvider) {
        super(apiService, cacheProvider);
    }

    public Observable<IndexBeanCache> getIndexCache() {
        Log.e("AppInfoModel", "-------------getIndexCache-----------------");
//        return  apiService.indexCache();

        return cacheProvider.getIndexInfo(apiService.indexCache())
                .flatMap(new Function<Reply<IndexBeanCache>, ObservableSource<IndexBeanCache>>() {
                    @Override
                    public ObservableSource<IndexBeanCache> apply(Reply<IndexBeanCache> indexBeanCacheReply) throws Exception {
                        Log.e("AppInfoModel", "----------apply: " + indexBeanCacheReply.getSource().name());
                        return Observable.just(indexBeanCacheReply.getData());
                    }
                });
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getApps() {
        return apiService.getApps("{\"page\":0}");
    }

    public Observable<BaseBean<IndexBean>> index() {
        return apiService.index();
    }

    public Observable<BaseBean<PageBean<AppInfo>>> topList(int page) {
        return apiService.topList(page);
    }


    public Observable<BaseBean<PageBean<AppInfo>>> games(int page) {

        return apiService.games(page);
    }


    public Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppsByCategory(int categoryid, int page) {

        return apiService.getFeaturedAppsByCategory(categoryid, page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getTopListAppsByCategory(int categoryid, int page) {

        return apiService.getTopListAppsByCategory(categoryid, page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getNewListAppsByCategory(int categoryid, int page) {

        return apiService.getNewListAppsByCategory(categoryid, page);
    }

    public Observable<BaseBean<AppInfo>> getAppDetail(int id) {

        return apiService.getAppDetail(id);
    }

}
