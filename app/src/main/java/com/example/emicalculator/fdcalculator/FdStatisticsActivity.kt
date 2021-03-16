package com.example.emicalculator.fdcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emicalculator.R
import com.example.emicalculator.rdcalculator.RdStatisticsData
import com.example.emicalculator.rdcalculator.RdStatsticsAdapter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class FdStatisticsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fd_statistics)


        val bundle = intent.extras
        if (bundle!= null){
            val P = bundle!!.getString("P")
            val R= bundle!!.getString("R")
            val T=bundle!!.getString("T")
        }
        val recyclerView = findViewById(R.id.recyclerView3) as RecyclerView

        recyclerView.layoutManager= LinearLayoutManager(this@FdStatisticsActivity, LinearLayout.VERTICAL,
            false)


        val count = ArrayList<FdStatisticsData>()

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

            if(countmonth%4!=3){

                interestshow= (interestshow+monthlyinterestAmount).toFloat().toDouble()

                countmonth++
                a=0
                count.add(FdStatisticsData(i.toString(),monthlyinterestAmount.toString(),a.toString(),previousprinciple.toString()))
                month--
                i++
                month2=month.toInt()
            }

            else if(countmonth%4==3){


                a= (interestshow+monthlyinterestAmount).toInt()
                countmonth=1
                interestshow=0.0

                principle= ((principle).toInt().toFloat()+a.toFloat()).toDouble()
                previousprinciple= principle.roundToInt()
                count.add(FdStatisticsData(i.toString(),monthlyinterestAmount.toString(),a.toString(),principle.toString()))
                month--
                i++
                month2=month.toInt()

            }





        }



        



//        count.add(FdStatisticsData(const.toString(),const.toString(),const.toString(),const.toString()))
//        count.add(FdStatisticsData(const.toString(),const.toString(),const.toString(),const.toString()))

        //creating our adapter
        val adapter = FdStatsticsAdapter(count)
        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter
    }
}