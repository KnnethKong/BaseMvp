package com.base.keyboard;


import android.support.v7.app.AppCompatActivity;

import com.base.mvp.R;

public class LoginKeyBoard extends BaseKeyBoard {

    public LoginKeyBoard(AppCompatActivity activity) {
        super.init(activity, R.xml.login_keyboard);
    }
}
