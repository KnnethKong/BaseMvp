package com.base.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.base.mvp.R;
import com.base.tools.DesUtils;
import com.base.tools.MD5Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class QrcodeActivity extends AppCompatActivity implements View.OnClickListener {

    private String address = "836syw63h6sh623he6wen7q73h6sh36sd63h6wh6ajhe63htd7362je73ys7";
    private String amount = "";
    private ImageView qrcodeImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode);
        qrcodeImg = (ImageView) findViewById(R.id.qrcode_img);
        findViewById(R.id.qrcode_amount).setOnClickListener(this);
        createQRCode();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.qrcode_amount:
                showAmount();
                break;
        }
    }

    private void createQRCode() {
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                return QRCodeEncoder.syncEncodeQRCode(address + amount, BGAQRCodeUtil.dp2px(QrcodeActivity.this, 220), Color.parseColor("#ff0000"));
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    qrcodeImg.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(QrcodeActivity.this, "生成英文二维码失败", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }


    private void intentActivity() {
        startActivity(new Intent(QrcodeActivity.this, TestScanActivity.class));
    }

    private void showAmount() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();
        View view1 = LayoutInflater.from(this).inflate(R.layout.alert_amount, null);
        alertDialog.setView(view1);
        Button button = (Button) view1.findViewById(R.id.amount_5);
        final EditText editText = (EditText) view1.findViewById(R.id.amount_3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = editText.getText().toString().trim();
                createQRCode();
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void showPwd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();
        View view1 = LayoutInflater.from(this).inflate(R.layout.alert_password, null);

        alertDialog.setView(view1);
        Button button = (Button) view1.findViewById(R.id.password_ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        Button cancel = (Button) view1.findViewById(R.id.password_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }


    private void sign() {
        DesUtils desTest = new DesUtils();
        try {
            String md5key = "TfuyUoq8hwI=";
            String password = "";
            String passwordMD5 = MD5Utils.toHex(MD5Utils.md5(password + md5key));
            Log.e("kxflog", "passwordMD5: " + passwordMD5);

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
