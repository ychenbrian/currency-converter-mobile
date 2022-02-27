package com.deerangle.dacc.android.ui.home

import com.deerangle.dacc.android.ActivityHomeBinding
import com.deerangle.dacc.android.R
import com.deerangle.dacc.android.utils.base.BaseActivity
import com.deerangle.dacc.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(R.layout.activity_home) {

    override val viewModel: HomeViewModel by viewModel()

    override fun setupView() {
    }
}
