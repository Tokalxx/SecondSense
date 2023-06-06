package com.example.resecondsense_v01

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.example.resecondsense_v01.databinding.ActivityHomePageBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import java.util.Date
//This class holds the fragments
class HomeActivity :  AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding
    private lateinit var drawerLayout: DrawerLayout
    val currentDate: Date = Date()
    val data = listOf(
        data_RecentEntry(R.drawable.the_goat, "Title 1", currentDate),
        data_RecentEntry(R.drawable.the_cow, "Title 2", currentDate),
        data_RecentEntry(R.drawable.the_other_goat, "Title 3", currentDate),
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
        vpAdapter.addFragment(Home(),"HOME")
        vpAdapter.addFragment(Category(),"CATEGORY")
        vpAdapter.addFragment(Entries(),"ENTRIES")
        // Set the adapter to the ViewPager
        viewPage.adapter = vpAdapter
        tabLayout.setupWithViewPager(viewPage)
        //navigation drawer
        val btnopenDrawer : Button = findViewById(R.id.btnMenuDrawer)
        val navView : NavigationView = findViewById(R.id.navDrawerView)
        drawerLayout = findViewById(R.id.drawerLayout)
        btnopenDrawer.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        //navigation from activities
        if (intent.hasExtra("DATA")) {
            val desiredFragmentIndex = intent.getIntExtra("DATA", 1)
            viewPage.currentItem = desiredFragmentIndex
        }





    }
}