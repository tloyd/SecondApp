package cc.springwind.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by HeFan on 2016/5/30.
 */
public class MixedTool {
    /**
     * 往SD卡写入图片
     *
     * @param context
     * @param bitmap
     * @param name
     * @return
     */
    public static File write2SdCard(Context context, Bitmap bitmap, String name) {
        FileOutputStream out = null;

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(context, "sd card is not mounted!"+Environment.getExternalStorageState(), Toast.LENGTH_SHORT).show();
            return null;
        }

        File root=Environment.getExternalStorageDirectory();
        File subDir=new File(root,"notes");

        if (!subDir.exists()){
            subDir.mkdirs();
        }
        File file = new File(subDir, name);

        try {
            out = new FileOutputStream(file);
            ((Bitmap) bitmap).compress(Bitmap.CompressFormat.JPEG, 100, out);// 把数据写入文件
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static String getTime() {
        SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm");
        return df.format(new Date());
    }

    public static float getxDpi(Activity context){
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);

        return metric.xdpi;
    }

    public static float getyDpi(Activity context){
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);

        return metric.ydpi;
    }
}
