package br.com.toodoo.fipay.ui.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
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
import br.com.toodoo.fipay.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ServicesFragment : Fragment() {

    private lateinit var rgTransationType: RadioGroup
    private lateinit var editCashValue: EditText
    private lateinit var editDescription: EditText
    private lateinit var editDate: EditText
    private lateinit var transferRecipient: LinearLayoutCompat
    private lateinit var btnValidateTransaction: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_services, container, false)

        initComponents(view)

        rgTransationType.setOnCheckedChangeListener { radioGroup, i ->
            if (i == R.id.rbTransfer) {
                transferRecipient.visibility = View.VISIBLE
            } else {
                transferRecipient.visibility = View.GONE
            }
        }

        setDatePicker()

        btnValidateTransaction.setOnClickListener {
            if (editCashValue.text.isNotEmpty()) {

                if (rgTransationType.checkedRadioButtonId == R.id.rbDeposit) {
                    makeDeposit()
                }
            }
        }

        return view
    }

    private fun setDatePicker() {
        val cal = Calendar.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MM/yyyy" // mention the format you need
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
    }

    private fun makeDeposit() {
        val description = editDescription.text.toString()
        val value = editCashValue.text.toString().toDouble()
        val dateFormatFromString = SimpleDateFormat("dd/MM/yyyy")
        val dateFormat = SimpleDateFormat("yyyy-dd-MM")
        val date = dateFormat.format(dateFormatFromString.parse(editDate.text.toString()))
        Log.i("FIPAY_LOG", "makeDeposit: $date")
//        val date = dateFormat.format(editDate.text.toString())
        Toast.makeText(context, "Data: $date", Toast.LENGTH_SHORT).show()
        val client = FirebaseHelper.logedUser

        if (description.isNotEmpty() && !value.isNaN() && client != null) {
            val deposit = Deposit(description, value, date, client.cpf)

            val retrofitClient = NetworkHelper.getRetrofitInstance(NetworkHelper.fipayBaseUrl)
            val endpoint = retrofitClient.create(FiPayApi::class.java)
            val callback = endpoint.makeDeposit(deposit)

            callback.enqueue(object : Callback<Deposit> {
                override fun onResponse(call: Call<Deposit>, response: Response<Deposit>) {
                    Toast.makeText(context, "Deposited with success.", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Deposit>, t: Throwable) {
                    Toast.makeText(context, "Erro while making deposit.", Toast.LENGTH_SHORT).show()
                }

            })
        }

        Toast.makeText(
            context,
            "Deposito do ${FirebaseHelper.logedUser?.fullName}",
            Toast.LENGTH_SHORT
        ).show()
    }

}