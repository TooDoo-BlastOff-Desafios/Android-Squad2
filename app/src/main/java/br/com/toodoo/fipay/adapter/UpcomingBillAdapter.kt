package br.com.toodoo.fipay.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.toodoo.fipay.R
import br.com.toodoo.fipay.model.Bill

class UpcomingBillAdapter(
    private val upcomingBills: List<Bill> = ArrayList()
) : RecyclerView.Adapter<UpcomingBillAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.upcoming_bill_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bill = upcomingBills[position]
        holder.bindView(bill)
    }

    override fun getItemCount(): Int {
        return upcomingBills.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(bill: Bill) {
            val category: TextView = itemView.findViewById(R.id.txtCategory)
            val date: TextView = itemView.findViewById(R.id.txtDate)

            category.text = bill.category
            date.text = bill.date
        }
    }

}