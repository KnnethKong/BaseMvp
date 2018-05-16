package com.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by KXF on 2018/5/16.
 */

@Target(ElementType.TYPE)//作用在类
@Retention(RetentionPolicy.RUNTIME)//运行时
public @interface ContentView {
    int value();
}
