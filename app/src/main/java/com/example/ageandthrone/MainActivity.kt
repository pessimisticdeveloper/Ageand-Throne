package com.example.ageandthrone

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var bDate = ""
    private lateinit var btnDate : ImageButton
    private lateinit var report : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDate :ImageButton = findViewById(R.id.btnDate)
        val report : TextView = findViewById(R.id.report)
       btnDate.setOnClickListener {
           DatePickerDialog(this, { view, year, moon, day ->
               bDate = "${year}" + "/" + "${moon+1}" + "/" + "${day}"
               val date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                   LocalDate.of(year,moon+1,day)
               } else {
                   TODO("VERSION.SDK_INT < O")
               }
               val currentDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                   LocalDateTime.now()
               } else {
                   TODO("VERSION.SDK_INT < O")
               }
               val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                   DateTimeFormatter.ofPattern("yyyy-MM-dd")
               } else {
                   TODO("VERSION.SDK_INT < O")
               }
               val formatted = currentDate.format(formatter)
               val dif = ChronoUnit.DAYS.between(date,currentDate)
               val birth = (365/dif)
               val minute = (TimeUnit.MINUTES.convert(dif,TimeUnit.DAYS))
               report.text = "You've Been Living In This Shitty World For"+" "+dif.toString()+" "+"Days..."+"\n"+
                       "You are"+" "+birth.toString()+" "+"Years old..."+"\n"+"And You Have Been Alive for"+" "+minute.toString()+" "+"Minutes....".toString()

           },2023,6,17).show()
       }
    }

}