package com.deerangle.dacc.data.model

data class Currency(
    var code: String? = null,
    var name: String? = null,
    var flag: Int = 0
) {
    fun getFullName(): String {
        return "$code: $name"
    }
}
