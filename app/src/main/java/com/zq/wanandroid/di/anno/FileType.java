package com.zq.wanandroid.di.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by zhangqi on 2019/9/3
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface FileType {
    /** The name. */
    String value() default "";
}
