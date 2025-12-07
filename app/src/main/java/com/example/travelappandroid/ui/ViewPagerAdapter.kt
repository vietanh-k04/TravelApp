package com.example.travelappandroid.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.travelappandroid.ui.explore.ExploreFragment
import com.example.travelappandroid.ui.food.FoodFragment
import com.example.travelappandroid.ui.home.HomeFragment
import com.example.travelappandroid.ui.itinerary.ItineraryFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> HomeFragment()
            1 -> ExploreFragment()
            2 -> FoodFragment()
            3 -> ItineraryFragment()
            else -> HomeFragment()
        }
    }

    override fun getItemCount(): Int {
        return 4
    }
}