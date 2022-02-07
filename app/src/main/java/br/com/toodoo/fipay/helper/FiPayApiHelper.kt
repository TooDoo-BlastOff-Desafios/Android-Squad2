package br.com.toodoo.fipay.helper

import br.com.toodoo.fipay.api.FiPayApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FiPayApiHelper {

    companion object {

        private val baseUrl: String = "https://fipaydebonaire.herokuapp.com"
        val retrofitClient = NetworkHelper.getRetrofitInstance(baseUrl)
        val endpoint = retrofitClient.create(FiPayApi::class.java)

    }

}