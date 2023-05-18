package com.example.resecondsense_v01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.example.resecondsense_v01.databinding.ActivityHomePageBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding

    val data = listOf(
        data_RecentEntry(R.drawable.the_goat, "Title 1", "Date 1"),
        data_RecentEntry(R.drawable.the_cow, "Title 2", "Date 2"),
        data_RecentEntry(R.drawable.the_other_goat, "Title 3", "Date 3"),
        // Add more items as needed
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)


        // code fo creating the Tablayout
        // video for this https://youtu.be/DJiqBhqyZeg
        var tabLayout: TabLayout = findViewById(R.id.tabNavigation)
        var viewPage : ViewPager = findViewById(R.id.vpNavigation)
        // Create an instance of your custom adapter
        val vpAdapter = VPAdapter(supportFragmentManager)
        // Add more fragments as needed
        vpAdapter.addFragment(Home(),"Home")
        vpAdapter.addFragment(Category(),"CATEGORY")
        // Set the adapter to the ViewPager
        viewPage.adapter = vpAdapter
        tabLayout.setupWithViewPager(viewPage)




    }
}
