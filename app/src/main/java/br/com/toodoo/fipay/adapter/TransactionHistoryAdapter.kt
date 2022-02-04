package br.com.toodoo.fipay.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.toodoo.fipay.R
import br.com.toodoo.fipay.model.Transaction
import java.util.*
import kotlin.collections.ArrayList

class TransactionHistoryAdapter(
    private val transactions: List<Transaction> = ArrayList()
) : RecyclerView.Adapter<TransactionHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.bindView(transaction)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val months = arrayListOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
        fun bindView(transaction: Transaction) {
            val title: TextView = itemView.findViewById(R.id.txtTitle)
            val date: TextView = itemView.findViewById(R.id.txtDate)
            val value: TextView = itemView.findViewById(R.id.txtValue)

            val calendar = GregorianCalendar()
            calendar.time = transaction.date!!

            title.text = transaction.title
            value.text = "$ ${transaction.value}"
            date.text = "${months[calendar.get(Calendar.MONTH)]} ${calendar.get(Calendar.DAY_OF_MONTH)}, ${calendar.get(Calendar.YEAR)}"
        }
    }

}