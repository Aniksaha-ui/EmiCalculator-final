package com.pipapps.emicalculator.rateofinterest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pipapps.emicalculator.R
import com.pipapps.emicalculator.StatisticsData

class RateStatisticsActivity : AppCompatActivity() {
    private var yearCount: TextView?=null
    private var principleCount: TextView?=null
    private var interestCount: TextView?=null
    private var balanceCount: TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_statistics)


        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        supportActionBar!!.setCustomView(R.layout.action_bar_layout2);
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("EMI Calculator")

        val bundle = intent.extras
        if (bundle!= null){
            val P = bundle!!.getString("P")
            val N= bundle!!.getString("N")
            val E=bundle!!.getString("E")
            val I=bundle!!.getString("rateofInterest")
        }

        val recyclerView = findViewById(R.id.recyclerView10) as RecyclerView

        recyclerView.layoutManager= LinearLayoutManager(this@RateStatisticsActivity, LinearLayout.VERTICAL,
            false)

        val count = ArrayList<RateOfInterestData>()

        var P = bundle!!.getString("P")!!.toFloat()
        var N= bundle!!.getString("N")!!.toFloat()
        var E= bundle!!.getString("E")!!.toFloat()
        var I=bundle!!.getString("rateofInterest")!!.toFloat()
        var i=1


        while (P>=1.0){

            var givenInterest:Float=(P*I)/(12*100)
            var givenInterestFinal:String="%.2f".format(givenInterest)
            var givenPrincipal:Float=(E-givenInterest)
            var givenPrincipalFinal:String="%.2f".format(givenPrincipal)
            P=P-givenPrincipal
            var PrincipalFinal:String="%.2f".format(P)


            count.add(RateOfInterestData(i.toString(),givenPrincipalFinal.toString(), givenInterestFinal.toString(),PrincipalFinal.toString()))
            i++
        }
        //creating our adapter
        val adapter = RateOfInterestAdapter(count)
        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter


    }
}