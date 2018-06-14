package com.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.base.mvp.R;
import com.base.tools.DesUtils;
import com.base.tools.MD5Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class QrcodeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode);
        findViewById(R.id.qrcode_amount).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.qrcode_amount:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final AlertDialog alertDialog = builder.create();
                View view1 = LayoutInflater.from(view.getContext()).inflate(R.layout.alert_amount, null);
                alertDialog.setView(view1);
                Button button = (Button) view1.findViewById(R.id.amount_5);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                sign();
                break;
        }
    }


    private void sign() {
        DesUtils desTest = new DesUtils();
        try {
            String md5key = "1243728su2_74ssw8/94";
            String password = "xjsuwosauwj237";
            String passwordMD5 = MD5Utils.toHex(MD5Utils.md5(password + md5key));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "kong");
            jsonObject.put("pwd", passwordMD5);
            jsonObject.put("age", 22);
            String encypt = desTest.encrypt("1111");
            Log.e("kxflog", "sign: " + encypt);
            String decrypt = desTest.decrypt(encypt);
            Log.e("kxflog", "sign: " + decrypt);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
