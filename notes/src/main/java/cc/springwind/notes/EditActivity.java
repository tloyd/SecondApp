package cc.springwind.notes;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cc.springwind.adapter.MediaItem;
import cc.springwind.db.Database;
import cc.springwind.utils.MixedTool;
import cc.springwind.utils.TextTool;

/**
 * Created by HeFan on 2016/5/28.
 */
public class EditActivity extends BaseActivity {
    @InjectView(R.id.et_note_title)
    EditText etNoteTitle;
    @InjectView(R.id.et_note_content)
    EditText etNoteContent;
    @InjectView(R.id.my_toolbar)
    Toolbar myToolbar;
    @InjectView(R.id.tv_eidt_time)
    TextView tvEidtTime;
    @InjectView(R.id.take_photo)
    FloatingActionButton takePhoto;

    private int _id = -1;
    // 可读写数据库引用
    private SQLiteDatabase dbWrite;
    // 数据库帮助类引用
    private Database db;
    private List<MediaItem> media_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.inject(this);

        // 创建数据库帮助类对象
        db = new Database(this);
        // 获取可读写数据库对象
        dbWrite = db.getWritableDatabase();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();// Get a support ActionBar corresponding to this toolbar
        ab.setDisplayHomeAsUpEnabled(true);// Enable the Up button
        etNoteContent.setMovementMethod(ScrollingMovementMethod.getInstance());

        _id = getIntent().getIntExtra("_id", -1);
        tvEidtTime.setText(MixedTool.getTime());
        if (_id > 0) {
            etNoteTitle.setText(getIntent().getStringExtra("title"));
            tvEidtTime.setText(getIntent().getStringExtra("time"));
            etNoteContent.setText(TextTool.getWeiboContent(this,etNoteContent,getIntent().getStringExtra("content")));
            return;
        }
        media_list = new ArrayList<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // 点击保存按钮操作
        if (id == R.id.action_save) {
            ContentValues notes_values = new ContentValues();
            notes_values.put("title", etNoteTitle.getText().toString());
            notes_values.put("content", etNoteContent.getText().toString());
            notes_values.put("time", MixedTool.getTime());
            if (_id > 0) {
                // 调用数据库更新方法
                int n=dbWrite.update(Database.DB_NOTES, notes_values, "_id=?", new String[]{_id + ""});
                if (n>0){
                    showToast("保存成功");
                }
                return true;
            }
            // 调用数据库插入方法
            long s=dbWrite.insert(Database.DB_NOTES, null, notes_values);
            if (s>0){
                showToast("保存成功");
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static final int REQUEST_CODE_TAKE_PHOTO = 1;
    /**
     * 存放拍照图片的uri地址
     */
    public static Uri imageUriFromCamera;
    @OnClick(R.id.take_photo)
    public void onClick() {
        imageUriFromCamera = createImageUri(this);
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriFromCamera);
        startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
    }

    private Uri createImageUri(EditActivity editActivity) {
        String name = "notes" + System.currentTimeMillis();
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, name);
        values.put(MediaStore.Images.Media.DISPLAY_NAME, name + ".jpeg");
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        return uri;
    }

    /**
     * 删除一条图片
     */
    public void deleteImageUri(Uri uri) {
        getContentResolver().delete(imageUriFromCamera, null, null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_TAKE_PHOTO:
                if(resultCode == RESULT_CANCELED) {
                    deleteImageUri(imageUriFromCamera);
                } else {
                    int curPosition = etNoteContent.getSelectionStart();
                    StringBuilder sb = new StringBuilder(etNoteContent.getText().toString());
                    sb.insert(curPosition, "\n["+imageUriFromCamera+"]\n");
                    etNoteContent.setText(TextTool.getWeiboContent(this,etNoteContent,sb.toString()));
                    etNoteContent.setSelection(curPosition + ("\n["+imageUriFromCamera+"]\n").length());
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbWrite.close();
    }

}
