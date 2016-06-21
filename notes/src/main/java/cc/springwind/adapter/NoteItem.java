package cc.springwind.adapter;

/**
 * Created by HeFan on 2016/5/29.
 */
public class NoteItem {

    private int _id;
    private String time;
    private String title;
    private String content;

    public NoteItem() {
    }

    public int get_id() {
        return _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
