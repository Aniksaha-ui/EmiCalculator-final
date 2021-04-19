package com.pipapps.emicalculator.fdcalculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pipapps.emicalculator.R
import com.pipapps.emicalculator.StatisticsAdapter

class FdStatsticsAdapter(val statistics: ArrayList<FdStatisticsData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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

            val v = LayoutInflater.from(parent.context).inflate(R.layout.fd_stat_recycler_title_row, parent, false)
            return FdStatsticsAdapter.FdStatTitleViewHolder(v)
        }

        val v = LayoutInflater.from(parent.context).inflate(R.layout.fd_recycler_row, parent, false)
        return FdStatsticsAdapter.ViewHolder(v)

    }


    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == ITEM_ROW)
        {
            val holderStatItem = viewHolder as FdStatsticsAdapter.ViewHolder

            holderStatItem.bindItems(statistics[position])
        }
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
    class FdStatTitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


}