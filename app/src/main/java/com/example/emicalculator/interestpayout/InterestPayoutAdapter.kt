package com.example.emicalculator.interestpayout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.emicalculator.R
import com.example.emicalculator.fdcalculator.FdStatisticsData


class InterestStatsticsAdapter(val statistics: ArrayList<InterestStaticsData>) : RecyclerView.Adapter<InterestStatsticsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.interest_recycler_row, parent, false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(statistics[position])
    }

    override fun getItemCount(): Int {
        return statistics.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(statistics: InterestStaticsData) {
            val textViewYear = itemView.findViewById(R.id.interestyear) as TextView
            val textViewPrincipal  = itemView.findViewById(R.id.IPinterestAmount) as TextView
            val textViewInterest  = itemView.findViewById(R.id.IPinterestCaptilized) as TextView
            val textViewBalance  = itemView.findViewById(R.id.IPbalance) as TextView

            textViewYear.text = statistics.month
            textViewPrincipal.text = statistics.interestAmountCount1
            textViewBalance.text=statistics. fdBalance1
            textViewInterest.text=statistics.interestCaptilizedCount1

        }
    }



}