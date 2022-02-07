package br.com.toodoo.fipay.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.toodoo.fipay.R
import br.com.toodoo.fipay.helper.FiPayApiHelper
import br.com.toodoo.fipay.model.Currency
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        getCurrency()
        return inflater.inflate(R.layout.fragment_currency, container, false)
    }

    private fun getCurrency() {
        val callback = FiPayApiHelper.endpoint.getCurrency()

        callback.enqueue(object: Callback<List<HashMap<String, List<HashMap<String, String>>>>> {
            override fun onResponse(
                call: Call<List<HashMap<String, List<HashMap<String, String>>>>>,
                response: Response<List<HashMap<String, List<HashMap<String, String>>>>>
            ) {
                Log.i("FIPAY_LOG", "onResponse: ${response.body()?.get(0)?.get("moeda")}")
            }

            override fun onFailure(call: Call<List<HashMap<String, List<HashMap<String, String>>>>>, t: Throwable) {
                Log.e("FIPAY_LOG", "onResponse: ${t.message}")
            }

        })
    }

}