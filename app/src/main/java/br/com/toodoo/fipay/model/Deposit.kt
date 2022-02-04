package br.com.toodoo.fipay.model

import com.google.gson.annotations.SerializedName
import java.util.*

class Deposit(
    val description: String,
    val cash_value: Double = 0.0,
    val date: String,
    val cliente: String
) {
}