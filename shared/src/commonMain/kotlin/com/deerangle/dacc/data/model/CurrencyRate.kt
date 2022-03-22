package com.deerangle.dacc.data.model

data class CurrencyRate(
    var visaRate: Double = 0.0,
    var mastercardRate: Double = 0.0,
    var jcbRate: Double = 0.0,
    var unionPayRate: Double = 0.0,
    var dinersClubRate: Double = 0.0
)
