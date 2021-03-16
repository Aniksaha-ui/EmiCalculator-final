package com.example.emicalculator.fdcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.emicalculator.R



private var DepositeAmount: EditText?=null
private var Interest: EditText?=null
private var year: EditText?=null
private var reset: Button?=null
private var calculate: Button?=null
private var statstics: Button?=null
private var deposite: TextView?=null
private var totalInterest: TextView?=null
private var maturityAmount: TextView?=null
private var absoluteAmount: TextView?=null

class InterestPayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interest_payout)

        DepositeAmount=findViewById<View>(R.id.fdDepositeAmount) as EditText
        Interest=findViewById<View>(R.id.fdInterest) as EditText
        year=findViewById<View>(R.id.year) as EditText
        reset=findViewById<View>(R.id.reset) as Button
        calculate=findViewById<View>(R.id.calculate) as Button
        statstics=findViewById<View>(R.id.statstics) as Button
        deposite=findViewById<View>(R.id.deposite) as TextView
        totalInterest=findViewById<View>(R.id.totalInterest) as TextView
        maturityAmount=findViewById<View>(R.id.maturityAmount) as TextView



        calculate!!.setOnClickListener {
            var P= DepositeAmount!!.text.toString().toDouble()
            var R= Interest!!.text.toString().toDouble()
            var T= year!!.text.toString().toDouble()
            InterestPayoutCalculation(P,R,T)
        }


    }

    private fun InterestPayoutCalculation(p: Double, r: Double, t: Double) {
        deposite=findViewById<View>(R.id.deposite) as TextView
        totalInterest=findViewById<View>(R.id.totalInterest) as TextView
        maturityAmount=findViewById<View>(R.id.maturityAmount) as TextView

        var interest=((p*r)/100)*t

        deposite!!.text=p.toString()
        totalInterest!!.text=interest.toString()
        maturityAmount!!.text= p.toString()

    }
}