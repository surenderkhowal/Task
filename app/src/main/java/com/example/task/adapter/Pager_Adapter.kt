package com.example.task.adapter

import android.support.v4.app.FragmentPagerAdapter
import android.content.Context;
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.task.Listing
import com.example.task.fragments.PostFragment
import com.example.task.fragments.ToDosFragment

class Pager_Adapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment? {

        when (position) {
            0 -> {
                //  val homeFragment: PostFragment = PostFragment()
                return Listing()
            }
            1 -> {
                return PostFragment()
            }
            2 -> {

                return ToDosFragment()
            }
            else -> return null
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}