package cc.springwind.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.widget.TextView;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by HeFan on 2016/6/17.
 */
public class TextTool {
    public static SpannableString getWeiboContent(final Context context, final TextView tv, String source) {
        String regexEmoji = "\\[[^\\[\\]]+\\]";

        String regex = regexEmoji;

        SpannableString spannableString = new SpannableString(source);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(spannableString);

        if (matcher.find()) {
            tv.setMovementMethod(LinkMovementMethod.getInstance());
            matcher.reset();
        }

        while (matcher.find()) {
            String emojiStr = matcher.group();
            if (emojiStr != null) {
                int start = matcher.start();
                String pathName = emojiStr.substring(1, emojiStr.length() - 1);
                Uri uri = Uri.parse(pathName);
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (bitmap != null) {
                    int size = (int) tv.getTextSize();
                    float scale = (float) bitmap.getHeight() / bitmap.getWidth();
                    int screenWidthPixels = ScreenTool.getScreenWidthPixels((Activity) context);
                    int height = (int) (screenWidthPixels * scale);

                    bitmap = Bitmap.createScaledBitmap(bitmap, screenWidthPixels, height, true);
                    ImageSpan imageSpan = new ImageSpan(context, bitmap);
                    spannableString.setSpan(imageSpan, start, start + emojiStr.length(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
        return spannableString;
    }
}