package com.example.emicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBar

class LoanCompairActivity : AppCompatActivity() {



    //Find all the resources
    private var Principleamount1:EditText?=null
    private var Interest1:EditText?=null
    private var Year1:EditText?=null

    private var Principleamount2:EditText?=null
    private var Interest2:EditText?=null
    private var Year2:EditText?=null
    private var Calculator:Button?=null
    private var MonthlyPayment:TextView?=null
    private var MonthlyPayment2:TextView?=null
    private var YearlyPayment:TextView?=null
    private var YearlyPayment2:TextView?=null
    private var PayableInterest:TextView?=null
    private var PayableInterest2:TextView?=null
    private var TotalWithInterest:TextView?=null
    private var Totalpayment1:TextView?=null
    private var Totalpayment2:TextView?=null

    private var reset:Button?=null

    //End all the resources


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_compair)
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        supportActionBar!!.setCustomView(R.layout.action_bar_layout2);
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("EMI Calculator")

        Principleamount1 = findViewById<View>(R.id.principleamount1) as EditText
        Interest1=findViewById<View>(R.id.interestAmount1) as EditText
        Year1=findViewById<View>(R.id.year5) as EditText
        Principleamount2 = findViewById<View>(R.id.principleamount2) as EditText
        Interest2=findViewById<View>(R.id.interestAmount2) as EditText
        Year2=findViewById<View>(R.id.year6) as EditText
        Calculator=findViewById<View>(R.id.cal) as Button
        reset=findViewById<View>(R.id.reset) as Button
        MonthlyPayment=findViewById<View>(R.id.monthlypayment1) as TextView
        PayableInterest=findViewById<View>(R.id.totalinterest1) as TextView
        MonthlyPayment2=findViewById<View>(R.id.monthlypayment2) as TextView
        PayableInterest2=findViewById<View>(R.id.totalinterest2) as TextView


        Calculator!!.setOnClickListener{
            var P=Principleamount1!!.text.toString().toDouble()
            var N= Year1!!.text.toString().toDouble()
            var I= Interest1!!.text.toString().toDouble()
            var P1=Principleamount2!!.text.toString().toDouble()
            var N1= Year2!!.text.toString().toDouble()
            var I1= Interest2!!.text.toString().toDouble()

            emi(P,I,N,P1,I1,N1)
        }




    }


    fun emi(P: Double,I:Double,N:Double,P1:Double,I1:Double,N1:Double): Float {
        MonthlyPayment=findViewById<View>(R.id.monthlypayment1) as TextView
        PayableInterest=findViewById<View>(R.id.totalinterest1) as TextView
        MonthlyPayment2=findViewById<View>(R.id.monthlypayment2) as TextView
        PayableInterest2=findViewById<View>(R.id.totalinterest2) as TextView
        Totalpayment1=findViewById<View>(R.id.totalpayout1) as TextView
        Totalpayment2=findViewById<View>(R.id.totalpayout2) as TextView


        var Month=N*12
        var Month1=N1*12
        var InterestValue=I/12/100
        var InterestValue1=I1/12/100
        var CommonPart=Math.pow(1+InterestValue,Month)
        var CommonPart1=Math.pow(1+InterestValue1,Month1)
        var DivUp=(P*InterestValue*CommonPart)
        var DivUp1=(P1*InterestValue1*CommonPart1)
        var DivDown=CommonPart-1
        var DivDown1=CommonPart1-1
        var emiCalculationPerMonth:Float=((DivUp/DivDown).toFloat())
        var emiCalculationPerMonth1:Float=((DivUp1/DivDown1).toFloat())
        var emiCalculationPerYear=emiCalculationPerMonth*12
        var emiCalculationPerYear1=emiCalculationPerMonth1*12
        var totalInterest=(emiCalculationPerMonth*Month)-P
        var totalInterest1=(emiCalculationPerMonth1*Month1)-P1
        var totalPayment=totalInterest+P
        var totalPayment1=totalInterest1+P1




        MonthlyPayment!!.text= emiCalculationPerMonth.toString().toFloat().toString()
        PayableInterest!!.text=totalInterest.toString().toFloat().toString()
        MonthlyPayment2!!.text= emiCalculationPerMonth1.toString().toFloat().toString()
        PayableInterest2!!.text=totalInterest1.toString().toFloat().toString()
        Totalpayment1!!.text=totalPayment.toString().toFloat().toString()
        Totalpayment2!!.text=totalPayment1.toString().toFloat().toString()

        return emiCalculationPerMonth
    }



}