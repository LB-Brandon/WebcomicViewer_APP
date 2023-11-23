package com.brandon.webtoonviewerapp

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.brandon.webtoonviewerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val container = binding.flContainer
        binding.btn1.setOnClickListener {
            // Transaction 은 작업 단위. commit 으로 적용함
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_container, WebViewFragment())
                commit()
            }
        }
        binding.btn2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_container, BFragment())
                commit()
            }
        }
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.fragments[0]
        if (currentFragment is WebViewFragment) {
            if (currentFragment.canGoBack()) {
                currentFragment.goBack()
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }


}
