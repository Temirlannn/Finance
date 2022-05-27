package com.it.finance.enums

enum class CategoryType(val type: String) {
    FAMILY("FAMILY"),
    SELF_INTEREST("SELF_INTERESTS"),
    HOBBY("HOBBY"),
    EDUCATION("EDUCATION");

    companion object {
        private val DEFAULT = EDUCATION.type
        fun findByCode(type: String):String = values().find { it.type.contentEquals(type) }?.type ?: DEFAULT
    }
}