package br.com.toodoo.fipay.api

import br.com.toodoo.fipay.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface FiPayApi {
    @POST("cadastro_clientes/")
    fun insertUser(@Body user: User): Call<User>

    @GET("cadastro_clientes/")
    fun getUsers(): Call<List<User>>

    @GET("deposito/")
    fun getDeposits(): Call<List<Deposit>>

    @POST("deposito/")
    fun makeDeposit(@Body transaction: Deposit): Call<Deposit>

    @GET("compras-feitas/")
    fun getPurchases(): Call<List<Purchase>>

    @POST("compras-feitas/")
    fun makePurchase(@Body transaction: Purchase): Call<Purchase>

    @POST("transferencia/")
    fun makeTransfer(@Body transaction: Transfer): Call<Transfer>

    @GET("moedas/")
    fun getCurrency(): Call<List<HashMap<String, List<HashMap<String, String>>>>>

}