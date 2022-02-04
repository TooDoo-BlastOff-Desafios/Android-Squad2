package br.com.toodoo.fipay.api

import br.com.toodoo.fipay.model.Deposit
import br.com.toodoo.fipay.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface FiPayApi {
    @POST("cadastro_clientes/")
    fun insertUser(@Body user: User): Call<User>

    @GET("cadastro_clientes/")
    fun getUsers(): Call<List<User>>

    @POST("deposito/")
    fun makeDeposit(@Body deposit: Deposit): Call<Deposit>
}