package br.com.toodoo.fipay.model

import com.google.gson.annotations.SerializedName

class Transfer(
    val description: String,
    val cash_value: Double,
    val date: String,
    val destinatario: String
) {
}