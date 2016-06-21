package cc.springwind.blogclient;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cc.springwind.beans.ArticleBean;
import cc.springwind.utils.DateTool;
import cc.springwind.utils.ImageTool;

/**
 * Created by HeFan on 2016/5/28.
 */
class MyAdapter extends BaseAdapter {
    private MainActivity mainActivity;
    private List<ArticleBean> list;


    public MyAdapter(MainActivity mainActivity, List<ArticleBean> list) {
        this.mainActivity = mainActivity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ArticleBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(mainActivity).inflate(R.layout.article_item, null);
        }

        final ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        final ImageTool image = new ImageTool(list.get(position).getPic_url());
        image.loadImage(new ImageTool.ImageCallBack() {
            @Override
            public void getDrawable(Drawable drawable) {
                imageView.setImageDrawable(drawable);
            }
        });

        TextView article_title = (TextView) convertView.findViewById(R.id.article_title);
        article_title.setText(list.get(position).getTitle());
        TextView time = (TextView) convertView.findViewById(R.id.timestamp);
        time.setText(DateTool.getShortTime(getItem(position).getTime()));
//        TextView article_desc = (TextView) convertView.findViewById(R.id.article_desc);
//        article_desc.setText(list.get(position).getDesc());


        return convertView;
    }
}
