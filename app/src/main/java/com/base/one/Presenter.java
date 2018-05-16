package com.base.one;


import com.base.tools.HttpUtils;

/**
 * Created by KXF on 2018/5/16.
 */

public class Presenter extends BasePresenter<LoginView> {
    private Model model_1;

    public Presenter() {
        model_1 = new Model();
    }

    public void login(String url, String name, String pwd) {
        model_1.login(url, name, pwd, new HttpUtils.OnHttpResultListener() {
            @Override
            public void onResult(String result) {
                LoginView view = getView();
                if (result != null && view != null)
                    view.onLogin(result);
            }
        });

    }

}
