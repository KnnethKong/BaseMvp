package com.base.one;

import com.base.tools.HttpTask;
import com.base.tools.HttpUtils;

/**
 * Created by KXF on 2018/5/16.
 */

public class Model {

    public void login(String url, String uname, String pwd, final HttpUtils.OnHttpResultListener listener) {
        HttpTask httpTask = new HttpTask(new HttpUtils.OnHttpResultListener() {
            @Override
            public void onResult(String result) {
                listener.onResult(result);
            }

        });
        httpTask.execute(url, uname, pwd);
    }
}
