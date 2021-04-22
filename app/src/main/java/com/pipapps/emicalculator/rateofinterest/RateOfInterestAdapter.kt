package com.pipapps.emicalculator.rateofinterest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pipapps.emicalculator.R
import com.pipapps.emicalculator.StatisticsData
import com.pipapps.emicalculator.fdcalculator.FdStatsticsAdapter


class RateOfInterestAdapter(val statistics: ArrayList<RateOfInterestData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val HEADER_ROW = 0
    private val ITEM_ROW = 1

    override fun getItemViewType(position: Int): Int {

        /*if(position == 0){
            return HEADER_ROW
        }*/

        return ITEM_ROW
    }


    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == HEADER_ROW){

            val v = LayoutInflater.from(parent.context).inflate(R.layout.rateofinterest_stat_recycler_title_row, parent, false)
            return RateOfInterestAdapter.RateOfInterestStatTitleViewHolder(v)
        }

        val v = LayoutInflater.from(parent.context).inflate(R.layout.rateofinterest_recycler_row, parent, false)
        return RateOfInterestAdapter.ViewHolder(v)

    }


    //this method is binding the data on the list
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == ITEM_ROW)
        {
            val holderStatItem = viewHolder as RateOfInterestAdapter.ViewHolder

            holderStatItem.bindItems(statistics[position])
        }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return statistics.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(statistics: RateOfInterestData) {
            val textViewYear = itemView.findViewById(R.id.yearCountforInterest) as TextView
            val textViewPrincipal  = itemView.findViewById(R.id.principleCountforInterest) as TextView
            val textViewInterest  = itemView.findViewById(R.id.interestCountforInterest) as TextView
            val textViewBalance  = itemView.findViewById(R.id.balanceCountforInterest) as TextView

            textViewYear.text = statistics.yearforinterest
            textViewPrincipal.text = statistics.principleforinterest
            textViewBalance.text=statistics.balanceforinterest
            textViewInterest.text=statistics.interestforinterest
        }
    }

    class RateOfInterestStatTitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}
