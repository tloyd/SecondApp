package cc.springwind.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HeFan on 2016/5/28.
 *
 * 该类继承自SQLiteOpenHelper帮助类,在应用创建的时候初始化数据库
 */
public class Database extends SQLiteOpenHelper {
    public final static String DB_NOTES="notes";
    public final static String DB_MEDIAS="medias";

    public static final String COLUMN_NAME_ID="_id";
    public static final String COLUMN_NAME_TITLE="title";
    public static final String COLUMN_NAME_CONTENT="content";
    public static final String COLUMN_NAME_TIME="time";

    public static final String COLUMN_NAME_MEDIA_ID="_id";
    public static final String COLUMN_NAME_MEDIA_PATH="path";
    public static final String COLUMN_NAME_MEDIA_OWNER="owner";
    public static final String COLUMN_NAME_MEDIA_INSERT_INDEX="insert_index";

    /**
     * 数据库构造方法
     *
     * @param context 上下文对象
     */
    public Database(Context context) {
        super(context, "notes", null   , 1);
    }

    /**
     * 第一次安装app创建数据库
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+DB_NOTES+"(" +
                ""+COLUMN_NAME_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                ""+COLUMN_NAME_TITLE+" TEXT NOT NULL DEFAULT \"\"," +
                ""+COLUMN_NAME_CONTENT+" TEXT NOT NULL DEFAULT \"\"," +
                ""+COLUMN_NAME_TIME+" TEXT NOT NULL DEFAULT \"\"" +
                ")");

        db.execSQL("CREATE TABLE "+DB_MEDIAS+"(" +
                ""+COLUMN_NAME_MEDIA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                ""+COLUMN_NAME_MEDIA_OWNER+" INTEGER NOT NULL DEFAULT \"\"," +
                ""+COLUMN_NAME_MEDIA_PATH+" TEXT NOT NULL DEFAULT \"\"," +
                ""+COLUMN_NAME_MEDIA_INSERT_INDEX+" INTEGER NOT NULL DEFAULT 0)");
    }

    /**
     * 数据库版本更新
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
