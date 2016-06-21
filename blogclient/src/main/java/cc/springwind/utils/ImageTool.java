package cc.springwind.utils;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.net.URL;

/**
 * Created by HeFan on 2016/5/28.
 */
public class ImageTool {

    public String image_path;


    public ImageTool(String image_path) {
        this.image_path = image_path;
    }

    public void loadImage(final ImageCallBack callBack) {

        final Handler handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                Drawable drawable = (Drawable) msg.obj;
                callBack.getDrawable(drawable);
//                Bitmap bitmap = (Bitmap) msg.obj;
//                callBack.getBitmap(bitmap);
            }

        };

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Drawable drawable = Drawable.createFromStream(new URL(image_path).openStream(), "");
                    Message message = Message.obtain();
                    message.obj = drawable;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                /*try {
                    Bitmap bitmap = BitmapCompressTool.compressBitmapFromInputStream(HttpTool.getInputStreamFromHttp(image_path), 60, 60);
                    Message message = Message.obtain();
                    message.obj = bitmap;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        }).start();
    }


    public interface ImageCallBack {
                public void getDrawable(Drawable drawable);
//        public void getBitmap(Bitmap bitmap);
    }
}
