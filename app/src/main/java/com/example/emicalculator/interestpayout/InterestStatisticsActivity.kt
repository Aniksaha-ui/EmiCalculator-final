package com.example.emicalculator.interestpayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emicalculator.R
import com.example.emicalculator.StatisticsData
import com.example.emicalculator.fdcalculator.FdStatisticsData
import com.example.emicalculator.fdcalculator.InterestPayoutActivity
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class InterestStatisticsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interest_statistics)


        val bundle = intent.extras
        if (bundle!= null){
            val P = bundle!!.getString("P")
            val R= bundle!!.getString("R")
            val T=bundle!!.getString("T")
        }


        val recyclerView = findViewById(R.id.recyclerView5) as RecyclerView

        recyclerView.layoutManager= LinearLayoutManager(this@InterestStatisticsActivity, LinearLayout.VERTICAL,
            false)

        val count = ArrayList<InterestStaticsData>()
        var P = bundle!!.getString("P")!!.toFloat()
        var R= bundle!!.getString("R")!!.toFloat()
        var T= bundle!!.getString("T")!!.toFloat()


        var month=T*12
        var cal = Calendar.getInstance()
        var principle=P.toDouble()

        cal[Calendar.MONTH] = 11
        var year = 2021
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
        val adapter = InterestStatsticsAdapter(count)
        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter







    }
}