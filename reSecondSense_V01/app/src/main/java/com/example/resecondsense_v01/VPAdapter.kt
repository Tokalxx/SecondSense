package com.example.resecondsense_v01

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
//This is the View pager adapter,
// which helps with instantiating the Tabs and fragments and helps us with navigation
// between the different fragments

class VPAdapter(fragmentActivity: FragmentManager) : FragmentStatePagerAdapter(fragmentActivity,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragmentList: MutableList<Fragment> = ArrayList()
    private var titleList: MutableList<String> = ArrayList()
    override fun getCount(): Int {

       return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {

        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        titleList.add(title)
    }
    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
        notifyDataSetChanged()
    }

}