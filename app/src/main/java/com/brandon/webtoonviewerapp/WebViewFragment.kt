package com.brandon.webtoonviewerapp

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.brandon.webtoonviewerapp.databinding.FragmentWebviewBinding


class WebViewFragment(private val position: Int, private val webViewUrl: String) : Fragment() {
    // lateinit 를 사용한다는 것은 nonNull 형태를 사용한다는 것이다
    // private var binding: FragmentWebviewBinding? = null 도 가능하지만 nullable 해진다
    private lateinit var binding: FragmentWebviewBinding

    var listener: OnTabLayoutNameChanged? = null

    companion object {
        const val SHARED_PREFERENCE = "WEB_HISTORY"
    }

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
            webViewClient = WebtoonWebViewClient(binding.progressBar) { url ->
                activity?.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)?.edit {
                    putString("tab$position", url)
                }
            }
            // 웹뷰 내 자바스크립트 활성화
            settings.javaScriptEnabled = true
            loadUrl(webViewUrl)
        }

        binding.btnBackToLastScene.setOnClickListener {
            val sharedPreferences = activity?.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)
            val url = sharedPreferences?.getString("tab$position", "")
            if (url.isNullOrEmpty()) {
                Toast.makeText(context, "마지막 저장 시점이 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                binding.webView.loadUrl(url)
            }

        }

        binding.btnChangeTapName.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
            val editText = EditText(context)

            dialog.setView(editText)
            dialog.setPositiveButton("저장") { dialogInterface, which ->
                activity?.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)?.edit() {
                    // tab 이름을 key값으로 editText 내 String 을 sharedPreference 에 저장
                    putString("tab${position}_name", editText.text.toString())
                    listener?.nameChanged(position, editText.text.toString())
                }
            }
            dialog.setNegativeButton("취소") { dialogInterface, _ ->
                dialogInterface.cancel()
            }

            dialog.show()
        }
    }

    fun canGoBack(): Boolean {
        return binding.webView.canGoBack()
    }

    fun goBack() {
        binding.webView.goBack()
    }

}

interface OnTabLayoutNameChanged {
    fun nameChanged(position: Int, name: String)
}
