package br.com.toodoo.fipay.api

import br.com.toodoo.fipay.helper.NetworkHelper
import br.com.toodoo.fipay.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @POST("cadastro_clientes/")
    fun insertUser(@Body user: User): Call<User>

    @GET("cadastro_clientes/")
    fun getUsers(): Call<List<User>>
}