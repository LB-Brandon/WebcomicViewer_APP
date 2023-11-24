package com.brandon.webtoonviewerapp

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.core.view.isVisible

class WebtoonWebViewClient(
    private val progressBar: ProgressBar,
    private val saveData: (String) -> Unit
) : WebViewClient() {

    // URL 로딩 직전 호출되는 함수
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        // webtoon page 이면 url 을 sharedPrefereneces 에 저장한다
        if (request != null && request.url.toString().contains("comic.naver.com/webtoon/detail")) {
            saveData.invoke(request.url.toString())
        }

        // comic.naver.com 하위 url 이동만 가능
        return !(request != null && request.url.toString().contains("comic.naver.com/"))

    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        progressBar.visibility = View.GONE
//        progressBar.isVisible = false 이것도 가능
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)

        progressBar.visibility = View.VISIBLE
    }

    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
        super.onReceivedError(view, request, error)
        // 에러 페이지 띄우기
    }
}
