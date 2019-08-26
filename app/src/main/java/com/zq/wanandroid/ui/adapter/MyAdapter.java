package com.zq.wanandroid.ui.adapter;

import android.util.Log;

public class MyAdapter {
    private Builder builder;

    public MyAdapter(Builder builder) {
        this.builder = builder;
    }

    public void test() {
        Log.e("MyAdapter", "test: " + builder.i);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        public boolean isFlag;
        public int i;

        public Builder setI(int i) {
            this.i = i;
            return this;
        }

        public MyAdapter build() {
            return new MyAdapter(this);
        }
    }
}
