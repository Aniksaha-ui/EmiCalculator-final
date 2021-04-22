package com.pipapps.emicalculator.rateofinterest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.pipapps.emicalculator.R


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


        MobileAds.initialize(this) {

        }


        val mAdView: AdView = findViewById(R.id.adRateOfInterest)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

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

        addTextFormater()

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

                var P=Principleamount!!.text.toString().replace(",","").toDouble()
                var N= Year!!.text.toString().replace(",","").toDouble()
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





        //calculation for
          statstics!!.setOnClickListener {
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

                var P=Principleamount!!.text.toString().replace(",","").toDouble()
                var N= Year!!.text.toString().toDouble()
                var E= emi!!.text.toString().toDouble()

                if(toggle1!!.isChecked()){
                    N=N
                }
                else{
                    N=N*12
                }

                var rateofInterest = RateofInterest(P,N,E)

                val bundle = Bundle()
                bundle.putString("P", P.toDouble().toString())
                bundle.putString("N", N.toDouble().toString())
                bundle.putString("E", E.toDouble().toString())
                bundle.putString("rateofInterest", rateofInterest.toString())
                val intent = Intent(this@RateOfInterestActivity, RateStatisticsActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }


        //end stats

        //start reset

        reset!!.setOnClickListener {
            Principleamount!!.setText("")
            Year!!.setText("")
            emi!!.setText("")
            interestRate!!.setText("0.0")
            yearlyPayment!!.setText("0.0")
            TotalWithInterest!!.setText("0.0")
            payableInterest!!.setText("0.0")

        }

        //end reset


    }

    private fun addTextFormater() {

        Principleamount!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charaters: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                charaters: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun afterTextChanged(editable: Editable?) {
                Principleamount!!.removeTextChangedListener(this)

                try {
                    val s = Principleamount!!.text.toString().replace(",", "")
                    val value = s.toDouble()
                    Principleamount?.setText(doubleToStringNoDecimal(value))
                    Principleamount?.setSelection(Principleamount!!.text.toString().length)


                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                }

                Principleamount!!.addTextChangedListener(this)
            }
        })

    }
    fun doubleToStringNoDecimal(d: Double): String {
        val formatter = String.format("%,.0f", d)
        Log.d("number", formatter)
        return formatter
    }



    fun RateofInterest(p: Double, n: Double, e: Double):Float {

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

        return pow.toFloat()



    }
}