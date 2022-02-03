package br.com.toodoo.fipay.model

import br.com.toodoo.fipay.helper.FirebaseHelper
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Exclude
import com.google.firebase.database.ValueEventListener
import java.io.Serializable

class User(
    var id: String = "",
    var fullName: String = "",
    var cpf: String = "",
    var email: String = "",
    @get:Exclude var password: String = "",
    var address: Address = Address()
) : Serializable {

}