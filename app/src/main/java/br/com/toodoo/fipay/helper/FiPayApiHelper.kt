package br.com.toodoo.fipay.helper

import br.com.toodoo.fipay.api.FiPayApi

class FiPayApiHelper {

    companion object {

        private const val baseUrl: String = "https://fipaydebonaire.herokuapp.com"
        private val retrofitClient = NetworkHelper.getRetrofitInstance(baseUrl)
        val endpoint: FiPayApi = retrofitClient.create(FiPayApi::class.java)

    }

}