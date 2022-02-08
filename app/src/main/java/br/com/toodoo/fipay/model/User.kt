package br.com.toodoo.fipay.model

import android.util.Log
import br.com.toodoo.fipay.helper.FiPayApiHelper
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class User(
    var cpf: String = "",
    @SerializedName("nome")
    var fullName: String = "",
    var email: String = "",
    var password: String = "",
    var country: String = "",
    var state: String = "",
    var city: String = "",
    var street: String = ""
//    var address: Address = Address()
) : Serializable {

    var deposits: MutableList<Deposit> = mutableListOf()
    var transfers: MutableList<Transfer> = mutableListOf()
    var purchases: MutableList<Purchase> = mutableListOf()
    var purchaseBalance: Double = 0.0
}