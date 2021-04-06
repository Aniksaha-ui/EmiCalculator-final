package com.example.emicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import com.example.emicalculator.fdcalculator.FdActivity
import com.example.emicalculator.fdcalculator.InterestPayoutActivity
import com.example.emicalculator.rdcalculator.RdActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        supportActionBar!!.setCustomView(R.layout.action_bar_layout);

        findViewById<View>(R.id.emiCalculator).setOnClickListener {
            val intent = Intent(this@MainActivity, EmiCalculateActivity::class.java)
            startActivity(intent)
        }

        findViewById<View>(R.id.compare_loan).setOnClickListener {
            val intent = Intent(this@MainActivity, LoanCompairActivity::class.java)
            startActivity(intent)
        }


//
//
        findViewById<View>(R.id.fixedDeposite).setOnClickListener{
            val intent=Intent(this@MainActivity, FdActivity::class.java)
            startActivity(intent)

        }
//
//
        findViewById<View>(R.id.InterestPayout).setOnClickListener{
            val intent=Intent(this@MainActivity, InterestPayoutActivity::class.java)
            startActivity(intent)

        }
//
//
//
        findViewById<View>(R.id.Rdcalculator).setOnClickListener{
            val intent=Intent(this@MainActivity, RdActivity::class.java)
            startActivity(intent)

        }











    }
}