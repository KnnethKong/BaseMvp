package com.base.one;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by KXF on 2018/5/16.
 */

public abstract class BaseActivity<V extends LoginView, P extends BasePresenter<V>> extends AppCompatActivity {

    private P presenter;
    private String TAG = this.getClass().getName();

    public P getPresenter() {
        return presenter;
    }

    private V view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "BaseActivity onCreate: ");

        init();
    }

    protected void init() {
        presenter = createPresenter();
        if (presenter == null)
            throw new NullPointerException("presenter NullPointerException");
        view = createView();
        if (view == null)
            throw new NullPointerException("view NullPointerException");
        presenter.attachView(view);

    }

    public abstract V createView();

    public abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null)
            presenter.dettachView();
    }
}
