package com.it.finance.enums

enum class PaymentType(val type: String) {
    PROFIT("PROFIT"),
    EXPENSE("EXPENSE");

    companion object {
        private val DEFAULT = EXPENSE.type
        fun findByType(type: String):String = values().find { it.type.contentEquals(type) }?.type ?: DEFAULT
    }
}