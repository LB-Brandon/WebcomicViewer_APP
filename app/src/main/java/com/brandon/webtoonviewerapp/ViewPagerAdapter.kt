package com.brandon.webtoonviewerapp

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(private val mainActivity: MainActivity) : FragmentStateAdapter(mainActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                return WebViewFragment(position, "https://comic.naver.com/webtoon/detail?titleId=183559&no=592&week=mon").apply {
                    listener = mainActivity
                }
            }

            1 -> {
                return WebViewFragment(position, "https://comic.naver.com/webtoon?tab=tue").apply{
                    listener = mainActivity
                }
            }

            else -> {
                return WebViewFragment(position, "https://comic.naver.com/webtoon?tab=wed").apply{
                    listener = mainActivity
                }
            }

        }
    }
}
