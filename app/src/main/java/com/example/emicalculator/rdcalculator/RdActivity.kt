package com.example.emicalculator.rdcalculator

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.core.app.NavUtils
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

class RdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rd)
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        supportActionBar!!.setCustomView(R.layout.action_bar_layout2);
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("EMI Calculator")


        DepositeAmount=findViewById<View>(R.id.Monthlydeposite) as EditText
        Interest=findViewById<View>(R.id.fdInterest) as EditText
        year=findViewById<View>(R.id.year) as EditText
        reset=findViewById<View>(R.id.reset) as Button
        calculate=findViewById<View>(R.id.calculate) as Button
        statstics=findViewById<View>(R.id.statstics) as Button
        deposite=findViewById<View>(R.id.deposite) as TextView
        totalInterest=findViewById<View>(R.id.totalInterest) as TextView
        maturityAmount=findViewById<View>(R.id.maturityAmount) as TextView
        absoluteAmount=findViewById<View>(R.id.absoluteAmount) as TextView



        calculate!!.setOnClickListener {

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)

            if(DepositeAmount!!.length()==0){
                DepositeAmount!!.setError("Enter Deposite Amount")
            }
            else if(Interest!!.length()==0){
                Interest!!.setError("Enter Interest Amount")
            }

            else if(year!!.length()==0){
                year!!.setError("Enter year")
            }

            else{

                var P= DepositeAmount!!.text.toString().toDouble()
                var R= Interest!!.text.toString().toDouble()
                var T= year!!.text.toString().toDouble()
                RdCalculation(P,R,T)
            }

        }


        statstics!!.setOnClickListener{

            if(DepositeAmount!!.length()==0){
                DepositeAmount!!.setError("Enter Deposite Amount")
            }
            else if(Interest!!.length()==0){
                Interest!!.setError("Enter Interest Amount")
            }

            else if(year!!.length()==0){
                year!!.setError("Enter year")
            }

            else{
                var P= DepositeAmount!!.text.toString().toDouble()
                var R= Interest!!.text.toString().toDouble()
                var T= year!!.text.toString().toDouble()
                RdCalculation(P,R,T)
                val bundle = Bundle()
                bundle.putString("P", P.toDouble().toString())
                bundle.putString("R", R.toDouble().toString())
                bundle.putString("T", T.toDouble().toString())

                val intent = Intent(this@RdActivity, rdStatisticsActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }


        reset!!.setOnClickListener {
            DepositeAmount!!.setText("")
            Interest!!.setText("")
            year!!.setText("")
            deposite!!.setText("0.0")
            totalInterest!!.setText("0.0")
            maturityAmount!!.setText("0.0")
            absoluteAmount!!.setText("0.0")

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

    public fun RdCalculation(p: Double, r: Double, t: Double) {

        deposite=findViewById<View>(R.id.deposite) as TextView
        totalInterest=findViewById<View>(R.id.totalInterest) as TextView
        maturityAmount=findViewById<View>(R.id.maturityAmount) as TextView
        absoluteAmount=findViewById<View>(R.id.absoluteAmount) as TextView

        var default=0
        var Month=(t*12)
        var totaldeposite=p*Month
        var interest=((p)*(Month*(Month+1))*r)/(2*12*100)
        var totalamount=totaldeposite+interest

        deposite!!.text=totaldeposite.toString()
        totalInterest!!.text=interest.toString()
        maturityAmount!!.text= totalamount.toString()
        absoluteAmount!!.text=default.toString()

    }
}