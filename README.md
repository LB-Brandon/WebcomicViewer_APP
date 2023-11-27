# Webcomic Viewer App

<img src="https://github.com/LB-Brandon/WebtoonViewer_App/assets/84883277/4c1df9a7-f63e-4215-a3e8-1eb113a50884" width="200" height="400"/>

## Overview

This app allows users to conveniently manage their favorite webtoon pages. The app features three tabs for viewing webtoons, and a 'Back to Last Scene' button allows users to easily return to the episode they were watching. Users can customize the names of each tab, and access to URLs other than Naver Webtoon is blocked.

## Features

- Utilizes ViewPager2 to compose N fragments.
- Connects TabLayout and ViewPager2 with TabLayoutMediator, allowing dynamic tab name changes.
- Can restore the latest viewed episode (using SharedPreferences).
- Implements the `OnTabLayoutNameChanged` listener interface to detect tab name changes.

## Key functions

### `onBackPressed()`

```kotlin
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
```

If a WebViewFragment exists on the current screen in MainActivity, the `onBackPressed()` method is overridden to perform a back operation within the current fragment's page.

### `shouldOverrideUrlLoading()`

```kotlin
// URL 로딩 직전 호출되는 함수
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        // webtoon page 이면 url 을 sharedPrefereneces 에 저장한다
        if (request != null && request.url.toString().contains("comic.naver.com/webtoon/detail")) {
            saveData.invoke(request.url.toString())
        }
        // comic.naver.com 하위 url 이동만 가능
        return !(request != null && request.url.toString().contains("comic.naver.com/"))
    }
```

- When accessing a webtoon page, the URL is saved within the WebViewFragment's SharedPreferences through the lambda function `saveData`.
- If the currently accessed URL does not include the path `comic.naver.com`, the page loading is halted.

### `OnTabLayoutNameChanged` Interface

```kotlin
interface OnTabLayoutNameChanged {
    fun nameChanged(position: Int, name: String)
}
```

This interface detects when the tab name in the Fragment has changed and is used to notify the Activity of the changes.

## How to Use

1. Launch the app.
2. Watch webtoons.
3. Click the 'Restore' button on the next app launch to resume from the last viewed episode.
4. Change webtoon titles or tab names arbitrarily.
