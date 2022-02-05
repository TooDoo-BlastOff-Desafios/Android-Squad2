package br.com.toodoo.fipay.helper

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkHelper {

    companion object {

        val fipayBaseUrl = "https://fipaydebonaire.herokuapp.com"

        fun getRetrofitInstance(path: String) : Retrofit {
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }

}