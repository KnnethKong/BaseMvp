package com.base.one;

/**
 * Created by KXF on 2018/5/16.
 */

public abstract class BasePresenter<V extends BaseView> {


    private V view;

    public V getView() {
        return view;
    }

    public void attachView(V view) {
        this.view = view;
    }

    public void dettachView() {
        this.view = null;
    }
}
