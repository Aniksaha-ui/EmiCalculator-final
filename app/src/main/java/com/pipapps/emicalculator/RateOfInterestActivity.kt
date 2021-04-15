package com.pipapps.emicalculator

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class RateOfInterestActivity : AppCompatActivity() {

    private var Principleamount:EditText?=null
    private var emi:EditText?=null
    private var Year:EditText?=null
    private var Calculator: Button?=null
    private var interestRate: TextView?=null
    private var yearlyPayment: TextView?=null
    private var payableInterest: TextView?=null
    private var TotalWithInterest: TextView?=null
    private var reset: Button?=null
    private var statstics: Button?=null
    private var toggle1: ToggleButton?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_of_interest)

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM;
        supportActionBar!!.setCustomView(R.layout.action_bar_layout2);
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "EMI Calculator"

        Principleamount = findViewById<View>(R.id.principleamount) as EditText
        emi=findViewById<View>(R.id.emi) as EditText
        Year=findViewById<View>(R.id.year7) as EditText
        Calculator=findViewById<View>(R.id.calculator) as Button
        reset=findViewById<View>(R.id.reset) as Button
        statstics=findViewById<View>(R.id.statstics) as Button
        interestRate=findViewById<View>(R.id.interestRate) as TextView
        yearlyPayment=findViewById<View>(R.id.yearlyPayment) as TextView
        TotalWithInterest=findViewById<View>(R.id.TotalWithInterest) as TextView
        payableInterest=findViewById<View>(R.id.PayableInterest) as TextView
        toggle1=findViewById<View>(R.id.toggle) as ToggleButton



        Calculator!!.setOnClickListener {
            if(currentFocus == null) return@setOnClickListener

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            if(Principleamount!!.length()==0){
                Principleamount!!.setError(getString(R.string.get_principal_amount))
            }

            else if(Year!!.length()==0){
                Year!!.setError(getString(R.string.year))
            }
            else if(emi!!.length()==0){
                emi!!.setError(getString(R.string.emierror))
            }

            else{

                var P=Principleamount!!.text.toString().toDouble()
                var N= Year!!.text.toString().toDouble()
                var E= emi!!.text.toString().toDouble()

                if(toggle1!!.isChecked()){
                    N=N
                }
                else{
                    N=N*12

                }

                RateofInterest(P,N,E)
            }
        }


    }

    fun RateofInterest(p: Double, n: Double, e: Double) {

        var yearlypayment=e*12
        var p1=p.toDouble()
        var e1=e.toDouble()
        var month=n.toDouble()

        val log = Math.log(1.0 / (month) + 1.0) / Math.log(2.0)
        val pow = ((Math.pow(Math.pow(e1 / p1 + 1.0, 1.0 / log) - 1.0, log) - 1.0) * 1200.0).toDouble()


        val InterestValue=pow/12/100
        var CommonPart=Math.pow(1+InterestValue,month)
        var DivUp=(p1*InterestValue*CommonPart)
        var DivDown=CommonPart-1
        var emiCalculationPerMonth:Float=((DivUp/DivDown).toFloat())
        var emiCalculationPerYear=emiCalculationPerMonth*12
        var totalInterest=(emiCalculationPerMonth*month)-p1
        var totalPayment=totalInterest+p1


        var yearlypayment1:String="%.2f".format(yearlypayment)
        var pow1:String="%.2f".format(pow)
        var totalInterest1:String="%.2f".format(totalInterest)
        var totalPayment1:String="%.2f".format(totalPayment)

        interestRate=findViewById<View>(R.id.interestRate) as TextView
        yearlyPayment=findViewById<View>(R.id.yearlyPayment) as TextView
        TotalWithInterest=findViewById<View>(R.id.TotalWithInterest) as TextView
        payableInterest=findViewById<View>(R.id.PayableInterest) as TextView








        interestRate!!.text=pow1!!.toString().toFloat().toString()
        yearlyPayment!!.text=yearlypayment1!!.toString().toFloat().toString()
        payableInterest!!.text=totalInterest1.toString().toFloat().toString()
        TotalWithInterest!!.text=totalPayment.toString().toFloat().toString()



    }
}