package com.deerangle.dacc.data.model

data class Currency(
    var code: String? = null,
    var name: String? = null,
    var flag: Int = 0,
    var visaRate: Double = 0.0,
    var mastercardRate: Double = 0.0,
    var jcbRate: Double = 0.0,
    var unionPayRate: Double = 0.0,
    var dinersClubRate: Double = 0.0
) {
    fun getFullName(): String {
        return "$code: $name"
    }
}
