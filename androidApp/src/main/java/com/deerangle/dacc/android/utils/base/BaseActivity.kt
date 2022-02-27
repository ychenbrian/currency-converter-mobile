package com.deerangle.dacc.android.utils.base

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.akexorcist.localizationactivity.core.LocalizationActivityDelegate
import com.deerangle.dacc.android.BR
import com.deerangle.dacc.android.utils.helpers.activityBinding
import java.util.Locale

abstract class BaseActivity<BINDING : ViewDataBinding, VIEW_MODEL : androidx.lifecycle.ViewModel>
constructor(@LayoutRes private val layoutResource: Int) : AppCompatActivity() {

    private val localizationDelegate = LocalizationActivityDelegate(this)

    protected val binding by activityBinding<BINDING>(layoutResource)
    protected abstract val viewModel: VIEW_MODEL

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        localizationDelegate.onCreate()
        localizationDelegate.setLanguage(this, Locale.CHINESE)
        super.onCreate(savedInstanceState)
        binding.also {
            it.lifecycleOwner = this
            it.setVariable(BR.viewModel, viewModel)
        }
        setupView()
    }

    abstract fun setupView()

    public override fun onResume() {
        super.onResume()
        localizationDelegate.onResume(this)
    }

    override fun attachBaseContext(newBase: Context) {
        applyOverrideConfiguration(localizationDelegate.updateConfigurationLocale(newBase))
        super.attachBaseContext(newBase)
    }

    override fun getApplicationContext(): Context {
        return localizationDelegate.getApplicationContext(super.getApplicationContext())
    }

    override fun getResources(): Resources {
        return localizationDelegate.getResources(super.getResources())
    }

    fun setLanguage(language: String?) {
        localizationDelegate.setLanguage(this, language!!)
    }

    fun setLanguage(locale: Locale?) {
        localizationDelegate.setLanguage(this, locale!!)
    }

    val currentLanguage: Locale
        get() = localizationDelegate.getLanguage(this)
}
