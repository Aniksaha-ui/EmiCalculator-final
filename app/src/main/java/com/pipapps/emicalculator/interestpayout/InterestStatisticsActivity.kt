package com.pipapps.emicalculator.interestpayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBar
import androidx.core.app.NavUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pipapps.emicalculator.R
import java.util.*
import kotlin.collections.ArrayList

class InterestStatisticsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interest_statistics)
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        supportActionBar!!.setCustomView(R.layout.action_bar_layout2);
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("EMI Calculator")

        val bundle = intent.extras
        if (bundle!= null){
            val P = bundle!!.getString("P")
            val R= bundle!!.getString("R")
            val T=bundle!!.getString("T")
        }


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView5)

        recyclerView.layoutManager= LinearLayoutManager(this@InterestStatisticsActivity)

        val count = ArrayList<InterestStaticsData>()
        var P = bundle!!.getString("P")!!.toFloat()
        var R= bundle!!.getString("R")!!.toFloat()
        var T= bundle!!.getString("T")!!.toFloat()


        var month=T*12
        var cal = Calendar.getInstance()
        var principle=P.toDouble()

        cal[Calendar.MONTH] = 11
        var year = cal.get(Calendar.YEAR)
        var interestshow=0.0
        var previousprinciple=0
        var countmonth=1
        var i=1
        var const=0
        var a=0
        var month2=0

        while(month>=1.0){

            var monthName=  cal.add(Calendar.MONTH, 1)
            var date = 1
            var maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH).toInt()
            var dailyInterest=(R/365)
            var monthlyInterst=dailyInterest* maxDay
            var monthlyinterestAmount= ((monthlyInterst*principle)/100)


            var monthlyinterestAmount1:String="%.2f".format(monthlyinterestAmount)



                interestshow= monthlyinterestAmount.toFloat().toDouble()
            var interestshow1:String="%.2f".format(interestshow)

                countmonth++
                a= (a+interestshow).toInt()
                count.add(InterestStaticsData(i.toString(),monthlyinterestAmount1.toString(),interestshow1.toString(),principle.toString()))
                month--
                i++
                month2=month.toInt()


        }


        //creating our adapter
        val adapter = InterestStatisticsAdapter(count)
        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}