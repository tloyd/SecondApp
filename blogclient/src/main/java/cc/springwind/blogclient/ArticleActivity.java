package cc.springwind.blogclient;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by HeFan on 2016/5/28.
 */
public class ArticleActivity extends Activity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_article);
        this.webView=(WebView) findViewById(R.id.webView);
        String content_url=getIntent().getStringExtra("content_url");
        this.webView.loadUrl(content_url);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }
}
