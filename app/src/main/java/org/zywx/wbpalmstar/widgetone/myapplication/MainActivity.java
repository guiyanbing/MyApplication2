package org.zywx.wbpalmstar.widgetone.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.zmxy.ZMCertification;
import com.zmxy.ZMCertificationListener;

import java.net.URLEncoder;
import java.util.List;


public class MainActivity extends Activity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="";
                doVerify(url,MainActivity.this);
            }
        });
    }






    /**
     * 启动支付宝进行认证
     *
     * @param url 开放平台返回的URL
     */
    public static void doVerify(String url, final Activity mContext) {
        if (hasApplication(mContext)) {

            Log.e("gyb",url);
            Intent action = new Intent(Intent.ACTION_VIEW);
            StringBuilder builder = new StringBuilder();
            // 这里使用固定appid 20000067
//            builder.append("alipays://platformapi/startapp?appId=300000487&url=");
            builder.append("alipays://platformapi/startapp?appId=20000067&url=");
//            builder.append("alipays://platformapi/startapp?appId=300000363&url=");//正式
//            builder.append("alipays://platformapi/startapp?appId=300000487&url=");
            builder.append(URLEncoder.encode(url));
            action.setData(Uri.parse(builder.toString()));
            mContext.startActivity(action);
        } else {
            // 处理没有安装支付宝的情况
            new AlertDialog.Builder(mContext)
                    .setMessage("是否下载并安装支付宝完成认证?")
                    .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent action = new Intent(Intent.ACTION_VIEW);
                            action.setData(Uri.parse("https://m.alipay.com"));
                            mContext.startActivity(action);
                        }
                    }).setNegativeButton("算了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }
    }



















    /**
     * 判断是否安装了支付宝
     *
     * @return true 为已经安装
     */
    private static boolean hasApplication(Context mContext) {
        PackageManager manager = mContext.getPackageManager();
        Intent action = new Intent(Intent.ACTION_VIEW);
        action.setData(Uri.parse("alipays://"));
        List list = manager.queryIntentActivities(action, PackageManager.GET_RESOLVED_FILTER);
        return list != null && list.size() > 0;
    }

}
