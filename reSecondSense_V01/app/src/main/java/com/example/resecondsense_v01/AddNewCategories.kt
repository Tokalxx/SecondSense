package com.example.resecondsense_v01

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import com.example.resecondsense_v01.databinding.ActivityAddNewCategoriesBinding
import java.util.Date


class AddNewCategories : AppCompatActivity() {


    private lateinit var binding: ActivityAddNewCategoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_categories)

        // Example navigation from activity to fragment
        val btnbackHome: Button = findViewById(R.id.btnBackHome)
        btnbackHome.setOnClickListener {
            onBackPressed()
        }

        val catTempText: TextView = findViewById(R.id.txtCategoryName)

        // Code below is suppose to add the details into he arrayList.
        val btnCatCreate: Button = findViewById(R.id.btnCatCreate)
        btnCatCreate.setOnClickListener {

            //Calling data from dataContext
            val catDBObj = DataContext()

            catDBObj.CreateCategory(catTempText.text.toString())
          //  catDBObj.extractDetails(catTempText.text.toString())
            // Show a toast message to indicate the data has been added
            Toast.makeText(this, "Data added successfully", Toast.LENGTH_SHORT).show()


            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("CategoryPosition",1)
            startActivity(intent)
//            val myviewPager : ViewPager = findViewById(R.id.vpNavigation)
//            myviewPager.currentItem = 1
//            val fragment = Category()
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.vpNavigation, fragment)
//            transaction.commit()
        }
    }
}

/*public class NetworkUtil {
    private static String WEATHERBASE_URL =
            "https://dataservice.accuweather.com/forecasts/v1/daily/5day/305605";
    private static String PARAM_METRIC = "metric";
    private static String METRIC_VALUE = "true";
    private static String PARAM_API_KEY = "apikey";
    private static String LOGGING_TAG = "URLWECREATED";

    public NetworkUtil() {
    }
}
*/
