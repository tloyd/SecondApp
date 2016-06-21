package cc.springwind.adapter;

/**
 * Created by HeFan on 2016/5/30.
 */
public class MediaItem {
    private String path;
    private int insert_index;

    public MediaItem(String path, int insert_index) {
        this.path = path;
        this.insert_index = insert_index;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getInsert_index() {
        return insert_index;
    }

    public void setInsert_index(int insert_index) {
        this.insert_index = insert_index;
    }
}
