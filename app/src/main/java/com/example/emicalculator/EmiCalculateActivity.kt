package com.example.emicalculator

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.ToggleButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils





class EmiCalculateActivity : AppCompatActivity() {

    //Find all the resources
    private var Principleamount:EditText?=null
    private var Interest:EditText?=null
    private var Year:EditText?=null
    private var Calculator:Button?=null
    private var MonthlyPayment:TextView?=null
    private var YearlyPayment:TextView?=null
    private var PayableInterest:TextView?=null
    private var TotalWithInterest:TextView?=null
    private var reset:Button?=null
    private var statstics:Button?=null
    private var toggle1:ToggleButton?=null
    //End all the resources


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emi_calculate)
//        supportActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
//        supportActionBar.setCustomView(R.layooou.toolbar)
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        supportActionBar!!.setCustomView(R.layout.action_bar_layout2);
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("EMI Calculator")



        Principleamount = findViewById<View>(R.id.principleamount) as EditText
        Interest=findViewById<View>(R.id.fdInterest) as EditText
        Year=findViewById<View>(R.id.year) as EditText
        Calculator=findViewById<View>(R.id.calculator) as Button
        reset=findViewById<View>(R.id.reset) as Button
        statstics=findViewById<View>(R.id.statstics) as Button
        MonthlyPayment=findViewById<View>(R.id.MonthlyPayment) as TextView
        YearlyPayment=findViewById<View>(R.id.YearlyPayment) as TextView
        TotalWithInterest=findViewById<View>(R.id.TotalWithInterest) as TextView
        PayableInterest=findViewById<View>(R.id.PayableInterest) as TextView
        toggle1=findViewById<View>(R.id.toggle) as ToggleButton


        Calculator!!.setOnClickListener{

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)


            if(Principleamount!!.length()==0){
                Principleamount!!.setError("Enter Principle Amount")
            }

            else if(Year!!.length()==0){
                Year!!.setError("Enter Year")
            }
            else if(Interest!!.length()==0){
                Interest!!.setError("Enter Interest Amount")
            }

            else if(Year!!.length()==0 && Interest!!.length()==0){
                Year!!.setError("Enter Year")
                Interest!!.setError("Enter Interest Amount")
            }
            else if(Principleamount!!.length()==0 && Interest!!.length()==0){
                Principleamount!!.setError("Enter Principle Amount")
                Interest!!.setError("Enter Interest Amount")
            }

            else if(Principleamount!!.length()==0 && Year!!.length()==0){
                Principleamount!!.setError("Enter Principle Amount")
                Year!!.setError("Enter Year")
            }



            else{

                var P=Principleamount!!.text.toString().toDouble()
                var N= Year!!.text.toString().toDouble()
                var I= Interest!!.text.toString().toDouble()
                if(toggle1!!.isChecked()){
                    N=N
                }
                else{
                    N=N*12

                }

                emi(P,I,N)
            }



        }


        reset!!.setOnClickListener{
                Principleamount!!.setText("")
                Interest!!.setText("")
                Year!!.setText("")
                MonthlyPayment!!.text="0.0".toString()
                YearlyPayment!!.text="0.0".toString()
                TotalWithInterest!!.text="0.0".toString()
                PayableInterest!!.text="0.0".toString()
        }


        statstics!!.setOnClickListener{

            if(Principleamount!!.length()==0){
                Principleamount!!.setError("Enter Principle Amount")
            }

            else if(Year!!.length()==0){
                toggle1!!.setError("Enter Year")
            }
            else if(Interest!!.length()==0){
                Interest!!.setError("Enter Interest Amount")
            }

            else if(Year!!.length()==0 && Interest!!.length()==0){
                toggle1!!.setError("Enter Year")
                Interest!!.setError("Enter Interest Amount")
            }
            else if(Principleamount!!.length()==0 && Interest!!.length()==0){
                Principleamount!!.setError("Enter Principle Amount")
                Interest!!.setError("Enter Interest Amount")
            }

            else if(Principleamount!!.length()==0 && Year!!.length()==0){
                Principleamount!!.setError("Enter Principle Amount")
                toggle1!!.setError("Enter Year")
            }


            else{

                var P=Principleamount!!.text.toString().toDouble()
                var N= Year!!.text.toString().toDouble()
                var I= Interest!!.text.toString().toDouble()

                if(toggle1!!.isChecked()){
                    N=N
                }
                else{
                    N=N*12
                }

                var Emi= emi(P,I,N)
                val bundle = Bundle()
                bundle.putString("P", P.toDouble().toString())
                bundle.putString("N", N.toDouble().toString())
                bundle.putString("I", I.toDouble().toString())
                bundle.putString("Emi", Emi.toString())
                val intent = Intent(this@EmiCalculateActivity, emiStatiscticsActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }



        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    fun emi(P: Double, I:Double, N:Double): Float {
        MonthlyPayment=findViewById<View>(R.id.MonthlyPayment) as TextView
        YearlyPayment=findViewById<View>(R.id.YearlyPayment) as TextView
        TotalWithInterest=findViewById<View>(R.id.TotalWithInterest) as TextView
        PayableInterest=findViewById<View>(R.id.PayableInterest) as TextView

        var Month=N
        var InterestValue=I/12/100
        var CommonPart=Math.pow(1+InterestValue,Month)
        var DivUp=(P*InterestValue*CommonPart)
        var DivDown=CommonPart-1
        var emiCalculationPerMonth:Float=((DivUp/DivDown).toFloat())
        var emiCalculationPerYear=emiCalculationPerMonth*12
        var totalInterest=(emiCalculationPerMonth*Month)-P
        var totalPayment=totalInterest+P



        var emiCalculationPerMonth1:String="%.2f".format(emiCalculationPerMonth)
        var emiCalculationPerYear1:String="%.2f".format(emiCalculationPerYear)
        var totalInterest1:String="%.2f".format(totalInterest)
        var totalPayment1:String="%.2f".format(totalPayment)

        MonthlyPayment!!.text= emiCalculationPerMonth1.toString().toFloat().toString()
        YearlyPayment!!.text= emiCalculationPerYear1.toString().toFloat().toString()
        PayableInterest!!.text=totalInterest1.toString().toFloat().toString()
        TotalWithInterest!!.text=totalPayment1.toString().toFloat().toString()
        return emiCalculationPerMonth
    }

}

