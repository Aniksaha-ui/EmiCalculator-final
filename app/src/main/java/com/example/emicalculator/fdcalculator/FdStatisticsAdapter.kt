package com.example.emicalculator.fdcalculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.emicalculator.R
import com.example.emicalculator.rdcalculator.RdStatisticsData

class FdStatsticsAdapter(val statistics: ArrayList<FdStatisticsData>) : RecyclerView.Adapter<FdStatsticsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fd_recycler_row, parent, false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(statistics[position])
    }

    override fun getItemCount(): Int {
        return statistics.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(statistics: FdStatisticsData) {
            val textViewYear = itemView.findViewById(R.id.fdyear) as TextView
            val textViewPrincipal  = itemView.findViewById(R.id.fdinterestAmount) as TextView
            val textViewInterest  = itemView.findViewById(R.id.fdinterestCaptilized) as TextView
            val textViewBalance  = itemView.findViewById(R.id.fdbalance) as TextView

            textViewYear.text = statistics.yearCount
            textViewPrincipal.text = statistics.interestAmountCount
            textViewBalance.text=statistics.fdBalance
            textViewInterest.text=statistics.interestCaptilizedCount
        }
    }



}