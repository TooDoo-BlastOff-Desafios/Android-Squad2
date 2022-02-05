package br.com.toodoo.fipay.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.toodoo.fipay.R
import br.com.toodoo.fipay.adapter.TransactionHistoryAdapter
import br.com.toodoo.fipay.model.Transaction
import br.com.toodoo.fipay.model.TransactionType
import java.util.*
import kotlin.collections.ArrayList


class StatisticsFragment : Fragment() {

    private lateinit var transactionHistoryAdapter: TransactionHistoryAdapter
    private lateinit var rvTransactionHistory: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_statistics, container, false)

        transactionHistoryAdapter = TransactionHistoryAdapter(transactions(), container!!.context)
        rvTransactionHistory = view.findViewById(R.id.rvTransactionHistory)
        rvTransactionHistory.adapter = transactionHistoryAdapter
        rvTransactionHistory.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        return view
    }

    private fun transactions(): ArrayList<Transaction> {
        return arrayListOf<Transaction>(
            Transaction("Salary", 1246.0, Calendar.getInstance().time.toString(), "", TransactionType.PURCHASE),
            Transaction("Salary", 1246.0, Calendar.getInstance().time.toString(), "", TransactionType.PURCHASE),
            Transaction("Salary", 1246.0, Calendar.getInstance().time.toString(), "", TransactionType.PURCHASE),
        )
    }

}