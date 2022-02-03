package br.com.toodoo.fipay.model

import java.io.Serializable

class Address(
    var country: String = "",
    var state: String = "",
    var city: String = "",
    var street: String = ""
) : Serializable {
}