package com.demo.amt.demoapp;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Timer;
import java.util.TimerTask;

public class Main4Activity extends Activity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(R.style.TranThemeBg);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        webView = findViewById(R.id.webview);
        webView.loadUrl("http://www.baidu.com");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(final WebView view, final String url) {
                super.onPageFinished(view, url);

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                if (url.contains("baidu")) {

                                    view.loadUrl("http://www.google.cn");
                                } else {
                                    view.loadUrl("http://www.baidu.com");
                                }
                            }
                        });
                    }
                }, 3000);

            }
        });
    }
}
