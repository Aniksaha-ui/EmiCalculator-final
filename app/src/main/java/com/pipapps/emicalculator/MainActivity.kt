package com.pipapps.emicalculator

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener
import com.pipapps.emicalculator.fdcalculator.FdActivity
import com.pipapps.emicalculator.fdcalculator.InterestPayoutActivity
import com.pipapps.emicalculator.rateofinterest.RateOfInterestActivity
import com.pipapps.emicalculator.rdcalculator.RdActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM;
        supportActionBar!!.setCustomView(R.layout.action_bar_layout);

        MobileAds.initialize(this) {

        }


        val mAdView: AdView = findViewById(R.id.adViewHome)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        findViewById<View>(R.id.emiCalculator).setOnClickListener {
            val intent = Intent(this@MainActivity, EmiCalculateActivity::class.java)
            startActivity(intent)
        }

        findViewById<View>(R.id.compare_loan).setOnClickListener {
            val intent = Intent(this@MainActivity, LoanCompairActivity::class.java)
            startActivity(intent)
        }


        rateofInterest.setOnClickListener {
            val intent = Intent(this@MainActivity, RateOfInterestActivity::class.java)
            startActivity(intent)
        }

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