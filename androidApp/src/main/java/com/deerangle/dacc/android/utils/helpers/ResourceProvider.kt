package com.deerangle.dacc.android.utils.helpers

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.deerangle.dacc.android.application.MainApplication

interface ResourceProvider {
    fun getString(@StringRes stringResId: Int): String
    fun getString(@StringRes stringResId: Int, vararg formatArgs: Any?): String
    fun getDrawable(@DrawableRes drawableRes: Int): Drawable?
}

object ResourceProviderImpl : ResourceProvider {

    override fun getString(@StringRes stringResId: Int): String {
        return MainApplication.getAppContext().getString(stringResId)
    }

    override fun getString(@StringRes stringResId: Int, vararg formatArgs: Any?): String {
        return MainApplication.getAppContext().getString(stringResId, formatArgs)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun getDrawable(@DrawableRes drawableRes: Int): Drawable? {
        return MainApplication.getAppContext().getDrawable(drawableRes)
    }
}
