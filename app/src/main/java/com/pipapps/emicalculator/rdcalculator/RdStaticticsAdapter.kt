package com.pipapps.emicalculator.rdcalculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pipapps.emicalculator.R

class RdStatsticsAdapter(val statistics: ArrayList<RdStatisticsData>) : RecyclerView.Adapter<RdStatsticsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rd_recycler_row, parent, false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(statistics[position])
    }

    override fun getItemCount(): Int {
        return statistics.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(statistics: RdStatisticsData) {
            val textViewYear = itemView.findViewById(R.id.year1) as TextView
            val textViewPrincipal  = itemView.findViewById(R.id.interestAmount) as TextView
            val textViewInterest  = itemView.findViewById(R.id.interestCaptilized) as TextView
            val textViewBalance  = itemView.findViewById(R.id.Rdbalance1) as TextView

            textViewYear.text = statistics.year
            textViewPrincipal.text = statistics.interestAmount
            textViewBalance.text=statistics.RdBalance
            textViewInterest.text=statistics.interestCaptilized
        }
    }



}