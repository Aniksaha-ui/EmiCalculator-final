package com.pipapps.emicalculator.interestpayout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pipapps.emicalculator.R
import com.pipapps.emicalculator.StatisticsAdapter
import com.pipapps.emicalculator.fdcalculator.FdStatsticsAdapter


class InterestStatisticsAdapter(val statistics: ArrayList<InterestStaticsData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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

            val v = LayoutInflater.from(parent.context).inflate(R.layout.interest_stat_recycler_title_row, parent, false)
            return InterestStatisticsAdapter.InterestStatTitleViewHolder(v)
        }

        val v = LayoutInflater.from(parent.context).inflate(R.layout.interest_recycler_row, parent, false)
        return InterestStatisticsAdapter.ViewHolder(v)

    }


    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == ITEM_ROW)
        {
            val holderStatItem = viewHolder as InterestStatisticsAdapter.ViewHolder

            holderStatItem.bindItems(statistics[position])
        }
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


    class InterestStatTitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }



}