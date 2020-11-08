package com.example.fkotlin

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSelectDate.setOnClickListener {view ->
            clickDatePicker(view)
            //Toast.makeText(this, "Button Works!", Toast.LENGTH_LONG).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun clickDatePicker(view: View?) {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, syear, smonth, sdayOfMonth ->
                Toast.makeText(this, "${syear} ${smonth + 1} ${sdayOfMonth}", Toast.LENGTH_LONG).show()
                val selectedDate = "$syear/${smonth + 1}/$sdayOfMonth"
                this.DateOfBirthText.setText(selectedDate)
                val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
                val theBirthDate = sdf.parse(selectedDate)
                val currentDate = sdf.parse(LocalDate.now().toString())
                val difference: Long = abs(currentDate.time - theBirthDate.time)
                val differenceDates = difference / (24 * 60 * 60 * 1000)
                val dayDifference = differenceDates.toString()
                ageInMinutesText.text = dayDifference
            },
            year, month, day
        ).show()
    }
}