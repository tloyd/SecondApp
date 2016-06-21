package cc.springwind.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cc.springwind.notes.R;

/**
 * Created by HeFan on 2016/5/29.
 */
public class ItemAdapter extends BaseAdapter {
    private Context context;
    List<NoteItem> list = null;

    public void bindData(List<NoteItem> list) {
        this.list = list;
    }

    public ItemAdapter(Context context) {
        this.context = context;
    }

    public ItemAdapter() {
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public NoteItem getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_note, null);
        }

        TextView tv_note_time = (TextView) convertView.findViewById(R.id.tv_note_time);
        tv_note_time.setText(getItem(position).getTime());
        TextView tv_note_title = (TextView) convertView.findViewById(R.id.tv_note_title);
        String title = getItem(position).getTitle();
        String s = "";
        if (title.trim().equals("")) {
            s = getItem(position).getContent();
        } else {
            s = title;
        }
        tv_note_title.setText(s);

        return convertView;
    }
}
