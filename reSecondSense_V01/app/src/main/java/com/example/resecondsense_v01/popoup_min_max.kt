import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.resecondsense_v01.R

class popoup_min_max : AppCompatActivity() {


    private val inputValues = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popoup_min_max)


        val cancelButton = findViewById<Button>(R.id.CnButton)
        val doneButton = findViewById<Button>(R.id.DoButton)

        cancelButton.setOnClickListener {
            // Handle cancel button click
            //dialog.dismiss()
            Toast.makeText(this, "Successfully", Toast.LENGTH_SHORT).show()

        }

        doneButton.setOnClickListener {
            // Retrieve the minimum and maximum values from the input fields
            val minEditText = findViewById<EditText>(R.id.InputMin)
            val maxEditText = findViewById<EditText>(R.id.InputMax)

            val minValue = minEditText.text.toString().toIntOrNull()
            val maxValue = maxEditText.text.toString().toIntOrNull()

            if (minValue != null && maxValue != null) {
                // Check if both values are valid integers
                if (minValue <= maxValue) {
                    // Valid input values
                    inputValues.add(minValue)
                    inputValues.add(maxValue)
                    // Do whatever you want with the minimum and maximum values here
                    Toast.makeText(this, "Successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Invalid input, show an error or handle it accordingly", Toast.LENGTH_SHORT).show()

                }
            } else {
                Toast.makeText(this, "Invalid input, show an error or handle it accordingly", Toast.LENGTH_SHORT).show()
            }

            // After processing the input, go back to the home page
            finish()
        }
    }
}