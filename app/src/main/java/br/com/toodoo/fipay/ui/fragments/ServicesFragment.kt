package br.com.toodoo.fipay.ui.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import br.com.toodoo.fipay.R
import br.com.toodoo.fipay.api.FiPayApi
import br.com.toodoo.fipay.helper.FirebaseHelper
import br.com.toodoo.fipay.helper.NetworkHelper
import br.com.toodoo.fipay.model.Deposit
import br.com.toodoo.fipay.model.Purchase
import br.com.toodoo.fipay.model.Transfer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class ServicesFragment : Fragment() {

    private lateinit var rgTransationType: RadioGroup
    private lateinit var editCashValue: EditText
    private lateinit var editDescription: EditText
    private lateinit var editDate: EditText
    private lateinit var transferRecipient: LinearLayoutCompat
    private lateinit var btnValidateTransaction: Button
    private lateinit var editTransferRecipientCpf: EditText

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_services, container, false)

        initComponents(view)
        setDatePicker()

        rgTransationType.setOnCheckedChangeListener { radioGroup, i ->
            if (i == R.id.rbTransfer) {
                transferRecipient.visibility = View.VISIBLE
            } else {
                transferRecipient.visibility = View.GONE
            }
        }

        validateTransaction()

        return view
    }

    private fun validateTransaction() {
        btnValidateTransaction.setOnClickListener {
            if (editCashValue.text.isNotEmpty()) {

                if (editDate.text.isNotEmpty()) {
                    val description = editDescription.text.toString()
                    val value = editCashValue.text.toString().toDouble()
                    val dateFormatFromString = SimpleDateFormat("dd/MM/yyyy")
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                    val date =
                        dateFormat.format(dateFormatFromString.parse(editDate.text.toString()))

                    // Verify the checked radio button and call the corresponding transaction method
                    if (rgTransationType.checkedRadioButtonId == R.id.rbDeposit) {
                        val deposit =
                            Deposit(description, value, date, FirebaseHelper.logedUser!!.cpf)
                        makeDeposit(deposit)
                    } else if (rgTransationType.checkedRadioButtonId == R.id.rbPurchase) {
                        val purchase =
                            Purchase(description, value, date, FirebaseHelper.logedUser!!.cpf)
                        makePurchase(purchase)
                    } else if (rgTransationType.checkedRadioButtonId == R.id.rbTransfer) {
                        if (editTransferRecipientCpf.text.isNotEmpty()) {
                            val recipientCpf = editTransferRecipientCpf.text.toString()
                            val transfer =
                                Transfer(description, value, date, recipientCpf)
                            makeTransfer(transfer)
                        } else {
                            editTransferRecipientCpf.error =
                                "You must provide a transfer recipient."
                            editTransferRecipientCpf.requestFocus()
                        }
                    }

                }
            } else {
                editCashValue.error = "You must provide a value."
                editCashValue.requestFocus()
            }
        }
    }

    private fun setDatePicker() {
        val cal = Calendar.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MM/yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                editDate.setText(sdf.format(cal.time))
            }

        editDate.setOnClickListener {
            DatePickerDialog(
                requireContext(), R.style.DialogTheme, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun initComponents(view: View) {
        rgTransationType = view.findViewById(R.id.rgTransationType)
        editCashValue = view.findViewById(R.id.editCashValue)
        editDescription = view.findViewById(R.id.editDescription)
        editDate = view.findViewById(R.id.editDate)
        transferRecipient = view.findViewById(R.id.transferRecipient)
        btnValidateTransaction = view.findViewById(R.id.btnValidateTransaction)
        editTransferRecipientCpf = view.findViewById(R.id.editTransferRecipientCpf)
    }

    private fun makeDeposit(deposit: Deposit) {

        val retrofitClient = NetworkHelper.getRetrofitInstance(NetworkHelper.fipayBaseUrl)
        val endpoint = retrofitClient.create(FiPayApi::class.java)

        val callback: Call<Deposit> = endpoint.makeDeposit(deposit)

        callback.enqueue(object : Callback<Deposit> {
            override fun onResponse(call: Call<Deposit>, response: Response<Deposit>) {
                Toast.makeText(context, "Deposit succeed.", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<Deposit>, t: Throwable) {
                Toast.makeText(context, "Error while making transaction.", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun makePurchase(purchase: Purchase) {

        val retrofitClient = NetworkHelper.getRetrofitInstance(NetworkHelper.fipayBaseUrl)
        val endpoint = retrofitClient.create(FiPayApi::class.java)

        val callback: Call<Purchase> = endpoint.makePurchase(purchase)

        callback.enqueue(object : Callback<Purchase> {
            override fun onResponse(call: Call<Purchase>, response: Response<Purchase>) {
                Toast.makeText(context, "Purchase succeed.", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<Purchase>, t: Throwable) {
                Toast.makeText(context, "Error while making transaction.", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun makeTransfer(transfer: Transfer) {

        val retrofitClient = NetworkHelper.getRetrofitInstance(NetworkHelper.fipayBaseUrl)
        val endpoint = retrofitClient.create(FiPayApi::class.java)

        val callback: Call<Transfer> = endpoint.makeTransfer(transfer)

        callback.enqueue(object : Callback<Transfer> {
            override fun onResponse(call: Call<Transfer>, response: Response<Transfer>) {
                Toast.makeText(context, "Transfer succeed.", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<Transfer>, t: Throwable) {
                Toast.makeText(context, "Error while making transaction.", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

}