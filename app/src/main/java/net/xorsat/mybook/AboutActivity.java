package net.xorsat.mybook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        WebView mWebView = (WebView)findViewById(R.id.about_webView);
        mWebView.loadUrl("http://xorsat.com/index.php/about");
    }
}
