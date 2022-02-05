package br.com.toodoo.fipay.model

import com.google.gson.annotations.SerializedName
import java.util.*

open class Transaction (
    val description: String,
    val cash_value: Double,
    val date: String,
    @SerializedName("destinatario")
    var cliente: String,
    var type: TransactionType? = null
) {
}

enum class TransactionType {
    DEPOSIT, PURCHASE, TRANSFER
}