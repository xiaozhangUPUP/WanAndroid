package com.zq.wanandroid.http;


import com.zq.wanandroid.http.responsebean.IndexBeanCache;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;


public interface CacheProvider {


    @LifeCache(duration = 1, timeUnit = TimeUnit.MINUTES)
        //    Observable<IndexBeanCache> getIndexInfo(Observable<IndexBeanCache> data);
        //reply 可以去掉 加上是为了知道数据的来源 MEMORY, PERSISTENCE, CLOUD;
    Observable<Reply<IndexBeanCache>> getIndexInfo(Observable<IndexBeanCache> data);


}
