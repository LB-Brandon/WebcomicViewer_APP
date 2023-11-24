package com.brandon.webtoonviewerapp

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.brandon.webtoonviewerapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity(), OnTabLayoutNameChanged {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference = getSharedPreferences(WebViewFragment.Companion.SHARED_PREFERENCE, Context.MODE_PRIVATE)
        val tab0 = sharedPreference?.getString("tab0_name", "tab1")
        val tab1 = sharedPreference?.getString("tab1_name", "tab2")
        val tab2 = sharedPreference?.getString("tab2_name", "tab3")

        binding.viewPager.adapter = ViewPagerAdapter(this)


        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // TabConfigurationStrategy 함수형 인터페이스 구현
            run {
//                val myCustomView = TextView(this)
//                myCustomView.text = when(position){
//                    0 -> "신의 탑"
//                    1 -> "화요 웹툰"
//                    else -> "수요 웹툰"
//                }
//                myCustomView.gravity = Gravity.CENTER
//                myCustomView.setTextColor(Color.BLUE)
//                tab.customView = myCustomView
                tab.text = when(position){
                    0 -> tab0
                    1 -> tab1
                    else -> tab2
                }
            }
        }.attach()


    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.fragments[binding.viewPager.currentItem]
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

    override fun nameChanged(position: Int, name: String) {
        val tab = binding.tabLayout.getTabAt(position)
        tab?.text = name

    }


}
