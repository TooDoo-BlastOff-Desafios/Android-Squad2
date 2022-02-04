package br.com.toodoo.fipay.model

import java.util.*

class Transaction (
    var title: String? = "",
    var value: Double? = 0.0,
    var type: TransactionType? = null,
    var date: Date? = null
) {
}

enum class TransactionType {
    INCOME, OUTCOME
}