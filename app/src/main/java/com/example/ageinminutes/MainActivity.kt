package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var buttonSelectDate = findViewById<Button>(R.id.buttonPickData);


        buttonSelectDate.setOnClickListener { view ->
            clickDatePicker(view)
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance();
        val day = myCalendar.get(Calendar.DAY_OF_MONTH);
        val year = myCalendar.get(Calendar.YEAR);
        val month = myCalendar.get(Calendar.MONTH);
           val dpc =  DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val selectedDay = "$year/${month+1}/$dayOfMonth"
                var selectedDate = findViewById<TextView>(R.id.selectedDate);
                selectedDate.setText(selectedDay)
                val sdf = SimpleDateFormat("dd/MM/YYYY", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDay)

                var selectedDateInMinutesTV = findViewById<TextView>(R.id.selectedDateInMinutes)

                val selectedDateInMinutes = theDate!!.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate!!.time / 6000
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                selectedDateInMinutesTV.setText(differenceInMinutes.toString())
            }, year, month, day)

        dpc.datePicker.maxDate = Date().time - 86400000
        dpc.show()
    }
}