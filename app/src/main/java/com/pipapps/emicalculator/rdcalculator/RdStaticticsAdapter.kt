package com.pipapps.emicalculator.rdcalculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pipapps.emicalculator.R
import com.pipapps.emicalculator.fdcalculator.FdStatsticsAdapter

class RdStatsticsAdapter(val statistics: ArrayList<RdStatisticsData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val HEADER_ROW = 0
    private val ITEM_ROW = 1

    override fun getItemViewType(position: Int): Int {

        /*if(position == 0){
            return HEADER_ROW
        }*/

        return ITEM_ROW
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == HEADER_ROW){

            val v = LayoutInflater.from(parent.context).inflate(R.layout.rd_stat_recycler_title_row, parent, false)
            return RdStatsticsAdapter.RdStatTitleViewHolder(v)
        }

        val v = LayoutInflater.from(parent.context).inflate(R.layout.rd_recycler_row, parent, false)
        return RdStatsticsAdapter.ViewHolder(v)

    }


    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == ITEM_ROW)
        {
            val holderStatItem = viewHolder as RdStatsticsAdapter.ViewHolder

            holderStatItem.bindItems(statistics[position])
        }
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


    class RdStatTitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }



}