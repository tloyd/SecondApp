package cc.springwind.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by HeFan on 2016/5/22.
 */
public class BitmapCompressTool {

    /**
     * 位图的采样压缩
     *
     * @param res
     * @param id 资源id
     * @param reqWidth 压缩后的宽度
     * @param reqHeigh 压缩后的高度
     * @return
     */
    public static Bitmap compressBitmapFromResources(Resources res,int id,int reqWidth,int reqHeigh){
        Bitmap bitmap=null;

        BitmapFactory.Options opts=new BitmapFactory.Options();
        opts.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(res,id,opts);

        int inSampleSize=calculateInSampleSize(opts,reqWidth,reqHeigh);

        opts.inSampleSize=inSampleSize;
        opts.inJustDecodeBounds=false;

        bitmap=BitmapFactory.decodeResource(res,id,opts);

        return bitmap;
    }

    public static Bitmap compressBitmapFromInputStream(InputStream inputStream, int reqWidth, int reqHeigh) throws IOException {
        Bitmap bitmap=null;

        BitmapFactory.Options opts=new BitmapFactory.Options();
        opts.inJustDecodeBounds=true;
        BitmapFactory.decodeStream(inputStream,null,opts);

        int inSampleSize=calculateInSampleSize(opts,reqWidth,reqHeigh);

        opts.inSampleSize=inSampleSize;
        opts.inJustDecodeBounds=false;

        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        byte[] bytes=new byte[1024];
        int length=-1;
        while (inputStream.read(bytes)!=-1){
            outputStream.write(bytes,0,bytes.length);
        }
        bitmap=BitmapFactory.decodeByteArray(outputStream.toByteArray(),0,outputStream.toByteArray().length,opts);
//        bitmap=BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;   //原始位图高度
        final int width = options.outWidth;     //原始位图宽度
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
