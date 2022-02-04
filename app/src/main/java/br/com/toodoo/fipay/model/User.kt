package br.com.toodoo.fipay.model

import br.com.toodoo.fipay.helper.FirebaseHelper
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Exclude
import com.google.firebase.database.ValueEventListener
import com.google.gson.annotations.SerializedName
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

}