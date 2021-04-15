package com.pipapps.emicalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.ActionBar

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
            var rateofinterest:Double= 0.0
           var upperpart=(e-(e/p))
           var powerpart=(1/n)-1
           var yearlypayment=e*12

          rateofinterest= Math.pow(upperpart,powerpart)
        interestRate=findViewById<View>(R.id.interestRate) as TextView
        yearlyPayment=findViewById<View>(R.id.yearlyPayment) as TextView
        TotalWithInterest=findViewById<View>(R.id.TotalWithInterest) as TextView
        payableInterest=findViewById<View>(R.id.PayableInterest) as TextView
        var rateofinterest1:String="%.2f".format(rateofinterest)
        var yearlypayment1:String="%.2f".format(yearlypayment)

        interestRate!!.text=rateofinterest1!!.toString().toFloat().toString()
        yearlyPayment!!.text=yearlypayment1!!.toString().toFloat().toString()


    }
}