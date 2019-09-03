package com.zq.wanandroid.di.module;

import com.google.gson.Gson;
import com.zq.wanandroid.di.anno.FileType;
import com.zq.wanandroid.http.CacheProvider;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

/**
 * Created by zhangqi on 2019/9/3
 */
@Module
public class CacheModule {

    @Singleton
    @Provides
    public RxCache provideRxCache(@FileType("cache") File cacheFile, Gson gson) {
        return new RxCache.Builder()
                .persistence(cacheFile, new GsonSpeaker(gson));
    }

    @Singleton
    @Provides
    public CacheProvider providerCacheProvider(RxCache rxCache) {

        return rxCache.using(CacheProvider.class);
    }
}
