package com.example.emicalculator.rdcalculator

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emicalculator.R
import java.security.Principal
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class rdStatisticsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rd_statistics)
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        supportActionBar!!.setCustomView(R.layout.action_bar_layout2);
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("EMI Calculator")

        val bundle = intent.extras
        if (bundle!= null){
            val P = bundle!!.getString("P")
            val R= bundle!!.getString("R")
            val T=bundle!!.getString("T")
        }
        val recyclerView = findViewById(R.id.recyclerView1) as RecyclerView

        recyclerView.layoutManager= LinearLayoutManager(this@rdStatisticsActivity, LinearLayout.VERTICAL,
            false)
        val count = ArrayList<RdStatisticsData>()

        var P = bundle!!.getString("P")!!.toFloat()
        var R= bundle!!.getString("R")!!.toFloat()
        var T= bundle!!.getString("T")!!.toFloat()

        var month=T*12

        var cal = Calendar.getInstance()

        var principle=P;
//        var monthName = Calendar.JANUARY
        cal[Calendar.MONTH] = 11
        var year = 2021


        var interestshow=0.0
        var previousprinciple=0
        var countmonth=3
        var i=1
        var const=0
        var a=0
        var month2=0

        count.add(RdStatisticsData(i.toString(),const.toString(),const.toString(),principle.toString()))
        month--
        i++



        while(month>=1.0){

            previousprinciple= principle.toInt()
            principle= (P+principle).toInt().toFloat()

            var monthName=  cal.add(Calendar.MONTH, 1)
            var date = 1


            var maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH).toInt()



            var dailyInterest=(R/365)
            var monthlyInterst=dailyInterest* maxDay
            var monthlyinterestAmount= ((monthlyInterst*previousprinciple)/100)
            var monthyinterestAmount2:String="%.2f".format(monthlyinterestAmount)

            if(countmonth%3!=2){

                interestshow= (interestshow+monthlyinterestAmount).toFloat().toDouble()

                countmonth++
                 a=0
                count.add(RdStatisticsData(i.toString(),monthyinterestAmount2.toString(),a.toString(),principle.toString()))
                month--
                i++
                month2=month.toInt()
            }
            else if(countmonth%3==2){


                 a= (interestshow+monthlyinterestAmount).toInt()
                countmonth=0
                interestshow=0.0

                principle= (principle).toInt().toFloat()+a.toFloat()
                count.add(RdStatisticsData(i.toString(),monthyinterestAmount2.toString(),a.toString(),principle.toString()))
                month--
                i++
                month2=month.toInt()

            }
            if(month2==0){

                a= (interestshow+monthlyinterestAmount).toInt()+a
                principle=principle+a
                monthlyInterst=((monthlyInterst*previousprinciple)/100)
                count.add(RdStatisticsData(i.toString(),monthlyInterst.toString(),a.toString(),principle.toString()))
            }
        }







        //creating our adapter
        val adapter = RdStatsticsAdapter(count)
        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter












    }
}