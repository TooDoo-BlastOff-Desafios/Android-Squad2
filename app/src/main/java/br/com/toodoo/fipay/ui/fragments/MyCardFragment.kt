package br.com.toodoo.fipay.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.toodoo.fipay.R
import br.com.toodoo.fipay.adapter.UpcomingBillAdapter
import br.com.toodoo.fipay.helper.AuthenticationHelper
import br.com.toodoo.fipay.helper.FiPayApiHelper
import br.com.toodoo.fipay.model.Bill
import java.text.NumberFormat

class MyCardFragment : Fragment() {

    private lateinit var txtBalanceValue: TextView
    private lateinit var upcomingBillAdapter: UpcomingBillAdapter
    private lateinit var rvUpcomingBill: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_card, container, false)

        txtBalanceValue = view.findViewById(R.id.txtBalanceValue)
        txtBalanceValue.text = NumberFormat.getCurrencyInstance().format(AuthenticationHelper.getUser()?.purchaseBalance)

//        Log.i("FIPAY_LOG", "AuthHelper Balance: ${AuthenticationHelper.logedUser?.balance} ")

        upcomingBillAdapter = UpcomingBillAdapter(bills())
        rvUpcomingBill = view.findViewById(R.id.rvUpcomingBill)
        rvUpcomingBill.adapter = upcomingBillAdapter
        rvUpcomingBill.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        return view
    }

    private fun bills(): ArrayList<Bill> {
        return arrayListOf<Bill>(
            Bill("Market bills", "December, 28, 2021"),
            Bill("Supermarket bills", "December, 28, 2021"),
            Bill("Store bills", "December, 28, 2021"),
            Bill("Wifi bills", "December, 28, 2021"),
            Bill("Supermarket bills", "December, 28, 2021")
        )
    }

}