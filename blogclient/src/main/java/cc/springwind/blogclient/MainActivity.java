package cc.springwind.blogclient;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import cc.springwind.beans.ArticleBean;
import cc.springwind.override.RefreshLayout;
import cc.springwind.utils.HttpTool;
import cc.springwind.utils.JsonTool;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ProgressDialog dialog;
    private String URL_PATH = "http://192.168.1.19:8080/BlogServer/servlet/RetrieveArtivleJson";
    private List<ArticleBean> beanList;
    private RefreshLayout refreshLayout;
    @butterknife.InjectView(R.id.listView)
    ListView listView;
    private MyAdapter myAdapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                refreshLayout.setRefreshing(false);
            }
            if (msg.what == 2) {
                refreshLayout.setLoading(false);
            }
            if (msg.what == 3) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).setTitle("错误").setMessage
                        ("无法连接到服务器").setPositiveButton("确定", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                }).create();
                dialog.dismiss();
                alertDialog.show();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.inject(this);
        listView.setOnItemClickListener(this);
        refreshLayout = (RefreshLayout) findViewById(R.id.swiperefresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new MyTask().execute(URL_PATH);
                myAdapter.notifyDataSetChanged();
                mHandler.sendEmptyMessageDelayed(1, 1000);
            }
        });
        dialog = new ProgressDialog(this);
        dialog.setTitle("加载中");
        dialog.setMessage("请稍候");
        new MyTask().execute(URL_PATH);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ArticleBean articleBean = beanList.get(position);
        Intent intent = new Intent(MainActivity.this, ArticleActivity.class);
        intent.putExtra("content_url", articleBean.getContent_url());
        startActivity(intent);
    }

    public class MyTask extends AsyncTask<String, Void, List<ArticleBean>> {
        String jsonString;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<ArticleBean> doInBackground(String... params) {
            jsonString = HttpTool.getArticlesJson(params[0], MainActivity.this);
            if (jsonString == null || jsonString == "") {
                mHandler.sendEmptyMessage(3);
                return null;
            }
            List<ArticleBean> list = JsonTool.jsonToArticleList(jsonString);
            myAdapter = new MyAdapter(MainActivity.this, list);
            return list;
        }

        @Override
        protected void onPostExecute(List<ArticleBean> articleBeen) {
            dialog.dismiss();
            super.onPostExecute(articleBeen);
            beanList = articleBeen;
            listView.setAdapter(myAdapter);
        }
    }

}
