package com.example.emicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.emicalculator.fdcalculator.FdActivity
import com.example.emicalculator.fdcalculator.InterestPayoutActivity
import com.example.emicalculator.rdcalculator.RdActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<View>(R.id.emiCalculator).setOnClickListener {
            val intent = Intent(this@MainActivity, EmiCalculateActivity::class.java)
            startActivity(intent)
        }
//
//
//        findViewById<View>(R.id.FixedDeposite).setOnClickListener{
//            val intent=Intent(this@MainActivity, FdActivity::class.java)
//            startActivity(intent)
//
//        }
//
//
//        findViewById<View>(R.id.InterestPayout).setOnClickListener{
//            val intent=Intent(this@MainActivity, InterestPayoutActivity::class.java)
//            startActivity(intent)
//
//        }
//
//
//
//        findViewById<View>(R.id.RecurringDeposite).setOnClickListener{
//            val intent=Intent(this@MainActivity, RdActivity::class.java)
//            startActivity(intent)
//
//        }











    }
}