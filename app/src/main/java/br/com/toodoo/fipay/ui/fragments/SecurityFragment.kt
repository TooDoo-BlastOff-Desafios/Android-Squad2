package br.com.toodoo.fipay.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.com.toodoo.fipay.R
import br.com.toodoo.fipay.helper.AuthenticationHelper
import br.com.toodoo.fipay.helper.FirebaseHelper

class SecurityFragment : Fragment() {

    private lateinit var editNewPassword: EditText
    private lateinit var editConfirmPassword: EditText
    private lateinit var btnChangePassword: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_security, container, false)

        initComponents(view)

        btnChangePassword.setOnClickListener {
            validateFields()
        }

        return view
    }

    private fun validateFields() {
        if (editNewPassword.text.isNotEmpty()) {
            if (editConfirmPassword.text.isNotEmpty() && editConfirmPassword.text.toString() == editNewPassword.text.toString()) {
                changePassword(editNewPassword.text.toString())
            } else {
                editConfirmPassword.error = "Passwords not matching."
                editConfirmPassword.requestFocus()
            }
        } else {
            editNewPassword.error = "You must type a new password."
            editNewPassword.requestFocus()
        }
    }

    private fun changePassword(newPassword: String) {
        val user = FirebaseHelper.getFirebaseAuth().currentUser

        user!!.updatePassword(newPassword).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(requireContext(), "Password updated!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "${it.exception}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initComponents(view: View) {
        editNewPassword = view.findViewById(R.id.editNewPassword)
        editConfirmPassword = view.findViewById(R.id.editConfirmPassword)
        btnChangePassword = view.findViewById(R.id.btnChangePassword)
    }

}