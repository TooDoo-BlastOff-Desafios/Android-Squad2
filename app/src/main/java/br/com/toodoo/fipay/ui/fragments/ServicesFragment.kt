package br.com.toodoo.fipay.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.appcompat.widget.LinearLayoutCompat
import br.com.toodoo.fipay.R

class ServicesFragment : Fragment() {

    private lateinit var transferRecipient: LinearLayoutCompat
    private lateinit var rgTransationType: RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_services, container, false)

        rgTransationType = view.findViewById(R.id.rgTransationType)
        transferRecipient = view.findViewById(R.id.transferRecipient)

        rgTransationType.setOnCheckedChangeListener { radioGroup, i ->
            if (i == R.id.rbTransfer) {
                transferRecipient.visibility = View.VISIBLE
            } else {
                transferRecipient.visibility = View.GONE
            }
        }

        return view
    }

    fun onRadioButtonClicked(view: View) {}

}