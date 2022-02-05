package br.com.toodoo.fipay.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Address(
    @SerializedName("Country")
    var country: String = "",
    @SerializedName("State")
    var state: String = "",
    @SerializedName("City")
    var city: String = "",
    @SerializedName("Street")
    var street: String = ""
) : Serializable {
}