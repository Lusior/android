package com.longdian.service.base;


import com.longdian.service.HoolaiException;

/**
 * Created by Administrator on 2017/3/15.
 */

public interface ObserverOnNextAndErrorListener<T> {
    void onNext(T t);

    void onError(HoolaiException e);
}
