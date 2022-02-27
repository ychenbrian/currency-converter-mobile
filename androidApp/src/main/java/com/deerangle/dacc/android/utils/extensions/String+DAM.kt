package com.deerangle.dacc.android.utils.extensions

import com.deerangle.dacc.android.application.MainApplication

fun String.getAsStringResource(): String {
    val packageName = MainApplication.getAppContext().packageName
    var resId = MainApplication.getAppContext().resources.getIdentifier(this, "string", packageName)
    if (resId == 0) {
        resId = MainApplication.getAppContext().resources.getIdentifier("res_$this", "string", packageName)
        if (resId == 0)
            return this
    }
    return MainApplication.getAppContext().getString(resId)
}
