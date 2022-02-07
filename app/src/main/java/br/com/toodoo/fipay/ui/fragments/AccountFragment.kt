package br.com.toodoo.fipay.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import br.com.toodoo.fipay.R
import br.com.toodoo.fipay.helper.AuthenticationHelper

class AccountFragment : Fragment() {

    private lateinit var editFullName: TextView
    private lateinit var editEmail: TextView
    private lateinit var editCPF: TextView
    private lateinit var editAddress: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        initComponents(view)

        editFullName.text = AuthenticationHelper.logedUser!!.fullName
        editEmail.text = AuthenticationHelper.logedUser!!.email
        editCPF.text = "******${AuthenticationHelper.logedUser!!.cpf.substring(6)}"
        editAddress.text = AuthenticationHelper.logedUser!!.street

        return view
    }

    private fun initComponents(view: View) {
        editFullName = view.findViewById(R.id.editFullName)
        editEmail = view.findViewById(R.id.editEmail)
        editCPF = view.findViewById(R.id.editCPF)
        editAddress = view.findViewById(R.id.editAddress)
    }

}