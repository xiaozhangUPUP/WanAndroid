package com.zq.wanandroid.di.module;


import com.zq.wanandroid.di.anno.FragmentScope;
import com.zq.wanandroid.http.ApiService;
import com.zq.wanandroid.model.SubjectModel;
import com.zq.wanandroid.presenter.contract.SubjectContract;

import dagger.Module;
import dagger.Provides;


@Module
public class SubjectModule {


    private SubjectContract.SubjectView mView;


    public SubjectModule(SubjectContract.SubjectView view){
        this.mView = view;
    }



    @FragmentScope
    @Provides
    public SubjectModel provideModel(ApiService apiService){

        return  new SubjectModel(apiService);
    }



    @FragmentScope
    @Provides
    public SubjectContract.SubjectView provideView(){


        return  mView;
    }
}
