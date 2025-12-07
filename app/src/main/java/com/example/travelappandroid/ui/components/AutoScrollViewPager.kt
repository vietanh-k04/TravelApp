package com.example.travelappandroid.ui.components

import android.os.Handler
import android.os.Looper
import androidx.viewpager2.widget.ViewPager2

class AutoScrollViewPager(
    private val viewPager: ViewPager2,
    private val delayMillis: Long = 3000
) {
    private val handler = Handler(Looper.getMainLooper())
    private var runnable: Runnable? = null
    private var isRunning = false
    private var itemCount = 0

    fun start(count: Int) {
        if (count <= 1) return
        itemCount = count

        if (runnable == null) {
            runnable = Runnable {
                if (isRunning && itemCount > 1) {
                    val next = (viewPager.currentItem + 1) % itemCount
                    viewPager.currentItem = next
                    handler.postDelayed(runnable!!, delayMillis)
                }
            }
        }

        // tránh chạy chồng
        stop()

        isRunning = true
        handler.postDelayed(runnable!!, delayMillis)

        // tránh đăng ký callback nhiều lần
        if (!callbackRegistered) {
            viewPager.registerOnPageChangeCallback(callback)
            callbackRegistered = true
        }
    }

    fun stop() {
        isRunning = false
        runnable?.let { handler.removeCallbacks(it) }
    }

    private var callbackRegistered = false

    private val callback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) {
            when (state) {
                ViewPager2.SCROLL_STATE_DRAGGING -> stop()
                ViewPager2.SCROLL_STATE_IDLE -> {
                    // chỉ khởi động lại 1 runnable duy nhất
                    if (isRunning) {
                        handler.postDelayed(runnable!!, delayMillis)
                    }
                }
            }
        }
    }

    fun release() {
        stop()
        if (callbackRegistered) {
            viewPager.unregisterOnPageChangeCallback(callback)
            callbackRegistered = false
        }
    }
}
