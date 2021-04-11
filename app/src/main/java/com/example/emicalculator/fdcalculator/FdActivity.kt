package com.example.emicalculator.fdcalculator

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
import android.widget.ToggleButton
import androidx.appcompat.app.ActionBar
import androidx.core.app.NavUtils
import com.example.emicalculator.R
import com.example.emicalculator.rdcalculator.rdStatisticsActivity


private var DepositeAmount:EditText?=null
private var Interest:EditText?=null
private var year:EditText?=null
private var reset: Button?=null
private var calculate:Button?=null
private var statstics:Button?=null
private var deposite:TextView?=null
private var totalInterest:TextView?=null
private var maturityAmount:TextView?=null
private var absoluteAmount:TextView?=null
private var toggle2: ToggleButton?=null




class FdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fd)

        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        supportActionBar!!.setCustomView(R.layout.action_bar_layout2);
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("EMI Calculator")

        DepositeAmount=findViewById<View>(R.id.fdDepositeAmount1) as EditText
        Interest=findViewById<View>(R.id.fdInterest1) as EditText
        year=findViewById<View>(R.id.year2) as EditText
        reset=findViewById<View>(R.id.reset) as Button
        calculate=findViewById<View>(R.id.calculate) as Button
        statstics=findViewById<View>(R.id.statstics) as Button
        deposite=findViewById<View>(R.id.deposite) as TextView
        totalInterest=findViewById<View>(R.id.totalInterest) as TextView
        maturityAmount=findViewById<View>(R.id.maturityAmount) as TextView
        absoluteAmount=findViewById<View>(R.id.absoluteAmount) as TextView
        toggle2=findViewById<View>(R.id.toggle2) as ToggleButton


        reset!!.setOnClickListener {
            DepositeAmount!!.setText("")
            Interest!!.setText("")
            year!!.setText("")
            deposite!!.setText("0.0")
            totalInterest!!.setText("0.0")
            maturityAmount!!.setText("0.0")
            absoluteAmount!!.setText("0.0")

        }



        calculate!!.setOnClickListener {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)

            if(DepositeAmount!!.length()==0){
                DepositeAmount!!.setError("Enter Deposite amount")
            }
            else if(Interest!!.length()==0){
                Interest!!.setError("Enter Interest Amount")
            }
            else if(year!!.length()==0){
                year!!.setError("Enter Year")
            }


            else{

                var P= DepositeAmount!!.text.toString().toDouble()
                var R= Interest!!.text.toString().toDouble()
                var N=4
                var T= year!!.text.toString().toDouble()



                if(toggle2!!.isChecked()){
                    T=T/12
                }
                else{
                    T=T

                }
                FdCalculate(P,R,N,T)

            }



        }

        statstics!!.setOnClickListener {

            if(DepositeAmount!!.length()==0){
                DepositeAmount!!.setError("Enter Deposite amount")
            }
            else if(Interest!!.length()==0){
                Interest!!.setError("Enter Interest Amount")
            }
            else if(year!!.length()==0){
                toggle2!!.setError("Enter Year")
            }


            else{

                var P= DepositeAmount!!.text.toString().toDouble()
                var R= Interest!!.text.toString().toDouble()
                var T= year!!.text.toString().toDouble()


                if(toggle2!!.isChecked()){
                    T=T/12
                }
                else{
                    T=T

                }


                val bundle = Bundle()
                bundle.putString("P", P.toDouble().toString())
                bundle.putString("R", R.toDouble().toString())
                bundle.putString("T", T.toDouble().toString())
                val intent = Intent(this@FdActivity, FdStatisticsActivity::class.java)
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



    public fun FdCalculate(p: Double, r: Double, n: Int, t: Double) {


        deposite=findViewById<View>(R.id.deposite) as TextView
        totalInterest=findViewById<View>(R.id.totalInterest) as TextView
        maturityAmount=findViewById<View>(R.id.maturityAmount) as TextView
        absoluteAmount=findViewById<View>(R.id.absoluteAmount) as TextView


        var i=r/100
        var simplepart=i/n
        var simplepart1=(1+simplepart)
        var simplepart2=n*t
        var simplepart3=Math.pow(simplepart1,simplepart2)
        var result=p*simplepart3
        var result1:String="%.2f".format(result)
        var interest=result-p


        var interest1:String="%.2f".format(interest)
        var absolutereturn=((result/p)-1)*100
        var absolutereturn1:String="%.2f".format(absolutereturn)


        deposite!!.text=p.toString()
        totalInterest!!.text=interest1.toString()
        maturityAmount!!.text= result1.toString()
        absoluteAmount!!.text=absolutereturn1.toString()

        
    }
}