package cc.springwind.beans;

/**
 * Created by HeFan on 2016/5/27.
 */
public class ArticleBean {
    private String id;
    private String title;
    private String desc;
    private String content_url;
    private String pic_url;
    private String time;

    public ArticleBean() {
    }

    public ArticleBean(String id, String title, String desc,String time, String content_url, String pic_url) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.content_url = content_url;
        this.pic_url = pic_url;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
