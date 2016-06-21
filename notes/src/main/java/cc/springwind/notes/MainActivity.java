package cc.springwind.notes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cc.springwind.adapter.ItemAdapter;
import cc.springwind.adapter.NoteItem;
import cc.springwind.db.Database;

public class MainActivity extends BaseActivity {

    @butterknife.InjectView(R.id.listView)
    ListView listView;

    private List<NoteItem> list = new ArrayList<>();
    private ItemAdapter adapter = new ItemAdapter(this);
    // 获取当前应用数据库帮助类对象
    private Database db = new Database(this);
    // 可读写数据库引用
    private SQLiteDatabase dbWrite;
    public static final int REQUEST_CODE_ADD_NOTE = 1;
    public static final int REQUEST_CODE_MODIFY_NOTE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_NOTE);
            }
        });
        // 通过数据库帮助类对象获取可读写数据库对象
        dbWrite = db.getWritableDatabase();
        // 打开应用的时候查询便签notes表,并得到游标
        Cursor cursor = dbWrite.query(Database.DB_NOTES, null, null, null, null, null, null);
        // 遍历游标并将需要的数据设置到对应实体类对象的属性,并添加进列表
        while (cursor.moveToNext()) {
            NoteItem item = new NoteItem();
            item.set_id(cursor.getInt(cursor.getColumnIndex(Database.COLUMN_NAME_ID)));
            item.setTime(cursor.getString(cursor.getColumnIndex(Database.COLUMN_NAME_TIME)));
            item.setTitle(cursor.getString(cursor.getColumnIndex(Database.COLUMN_NAME_TITLE)));
            item.setContent(cursor.getString(cursor.getColumnIndex(Database.COLUMN_NAME_CONTENT)));
            list.add(item);
        }

        adapter.bindData(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("_id", list.get(position).get_id());
                intent.putExtra("time", list.get(position).getTime());
                intent.putExtra("title", list.get(position).getTitle());
                intent.putExtra("content",list.get(position).getContent());
                startActivityForResult(intent, REQUEST_CODE_MODIFY_NOTE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 程序销毁时关闭相关数据库连接
        dbWrite.close();
        db.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_ADD_NOTE:
                if (resultCode == RESULT_OK) {
                    refreshNoteList();
                }
                break;
        }
    }

    /**
     * 刷新日志列表
     */
    private void refreshNoteList() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
