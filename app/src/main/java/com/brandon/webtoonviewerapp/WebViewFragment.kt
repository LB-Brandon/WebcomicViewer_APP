package com.brandon.webtoonviewerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.brandon.webtoonviewerapp.databinding.FragmentWebviewBinding

class WebViewFragment : Fragment() {
    // lateinit 를 사용한다는 것은 nonNull 형태를 사용한다는 것이다
    // private var binding: FragmentWebviewBinding? = null 도 가능하지만 nullable 해진다
    private lateinit var binding: FragmentWebviewBinding

    // LayoutInflater 객체는 Activity 로 부터 전달 받으며,
    // layout xml, ViewGroup 객체, xml 파일 속성에 대한 정보를 가지고 있다
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentWebviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.apply {
            // 아래 멤버는 실제로 setWebViewClient(WebViewClient client)이다
            // 자바는 위 메서드를 호출해 사용한다
            webViewClient = WebViewClient()
            // 웹뷰 내 자바스크립트 활성화
            settings.javaScriptEnabled = true
            loadUrl("https://google.com")
        }

    }
}
