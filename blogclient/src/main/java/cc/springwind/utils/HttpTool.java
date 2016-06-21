package cc.springwind.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by HeFan on 2016/5/27.
 */
public class HttpTool {

    public static String getArticlesJson(final String url, final Context context) {

        HttpURLConnection connection;
        InputStream inputStream;

        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            if (connection.getResponseCode()!=HttpURLConnection.HTTP_OK){
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("错误").setMessage("错误代码"+connection.getResponseCode()).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((Activity)context).finish();
                    }
                }).create();
            }
            inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);

            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream getInputStreamFromHttp(final String pic_url) {
        InputStream inputStream = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(pic_url).openConnection();
            connection.setConnectTimeout(3000);
            int n = connection.getResponseCode();
            if (n == 200) {
                inputStream = connection.getInputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }
}
