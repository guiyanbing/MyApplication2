package org.zywx.wbpalmstar.widgetone.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class OcrResultiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr_resulti);


        Intent intent = getIntent();
        if (intent.getData() == null) {

            finish();
        } else {
            Log.e("gyb",getIntent().getData().toString());
            String url = intent.getData().toString();
            Log.e("gyb","url---->" + url);
            String params = url.substring(url.indexOf("?params=") + 8, url.indexOf("&sign="));
            String sign = url.substring(url.indexOf("&sign=") + 6);

        }

    }
}
