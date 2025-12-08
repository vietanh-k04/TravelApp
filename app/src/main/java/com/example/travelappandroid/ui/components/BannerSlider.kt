package com.example.travelappandroid.ui.components

import android.os.Handler
import android.os.Looper
import androidx.viewpager2.widget.ViewPager2

class BannerSlider(private val viewPager: ViewPager2, private val interval: Long = 7000L) {
    private val handler = Handler(Looper.getMainLooper())

    private val runnable = object : Runnable {
        override fun run() {
            val adapter = viewPager.adapter ?: return
            val next = (viewPager.currentItem + 1) % adapter.itemCount
            viewPager.setCurrentItem(next, true)
            handler.postDelayed(this, interval)
        }
    }

    fun start() {
        stop()
        handler.postDelayed(runnable, interval)
    }

    fun stop() {
        handler.removeCallbacks(runnable)
    }

    fun restart() {
        stop()
        start()
    }
}