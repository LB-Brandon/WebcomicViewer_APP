package com.brandon.webtoonviewerapp

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // webView component 가져오기
        val webView = findViewById<WebView>(R.id.webView)
        // 아래 멤버는 실제로 setWebViewClient(WebViewClient client)이다
        // 자바는 위 메서드를 호출해 사용한다
        webView.webViewClient = WebViewClient()
        // 웹뷰 내 자바스크립트 활성화
        webView.settings.javaScriptEnabled = true

        webView.loadUrl("https://www.google.com/")
    }
}
