package cc.springwind.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.EditText;

import java.util.List;

import cc.springwind.adapter.MediaItem;
import cc.springwind.notes.EditActivity;

/**
 * Created by HeFan on 2016/5/30.
 */
public class MediaTool {

    public static void insertImageSpan2Text(Context context, Bitmap bitmap, String path, EditText editText,List<MediaItem> media_list) {
        ImageSpan imageSpan = new ImageSpan(context, bitmap);
        SpannableString spannableString = new SpannableString(path);
        spannableString.setSpan(imageSpan, 0, path.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 将选择的图片追加到EditText中光标所在位置
        int index = editText.getSelectionStart();
        // 获取光标所在位置
        Editable edit_text = editText.getEditableText();
        if (index < 0 || index >= edit_text.length()) {
            edit_text.append(spannableString);
        } else {
            edit_text.insert(index, spannableString);
        }
        MediaItem item = new MediaItem(path,index);
        media_list.add(item);
    }


    public static void insertImageSpan2TextAtBegin(EditActivity editActivity, Bitmap bitmap, String path, EditText
            etNoteContent, List<MediaItem> media_list, int index) {
        ImageSpan imageSpan = new ImageSpan(editActivity, bitmap);
        SpannableString spannableString = new SpannableString(path);
        spannableString.setSpan(imageSpan, 0, path.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        Editable edit_text = etNoteContent.getEditableText();
        if (index < 0 || index >= edit_text.length()) {
            edit_text.append(spannableString);
        } else {
            edit_text.replace(index,path.length()+index,spannableString);
        }
        MediaItem item = new MediaItem(path,index);
        media_list.add(item);
    }
}
