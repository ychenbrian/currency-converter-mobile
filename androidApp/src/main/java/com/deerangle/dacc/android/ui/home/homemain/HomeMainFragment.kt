package com.deerangle.dacc.android.ui.home.homemain

import com.deerangle.dacc.android.FragmentHomeMaininding
import com.deerangle.dacc.android.R
import com.deerangle.dacc.android.utils.base.BaseFragment
import com.deerangle.dacc.android.utils.helpers.RateHelper
import com.deerangle.dacc.ui.home.homemain.HomeMainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeMainFragment : BaseFragment<FragmentHomeMaininding, HomeMainViewModel>(R.layout.fragment_home_main) {

    override val viewModel: HomeMainViewModel by viewModel()

    override fun setupObservers() {}

    override fun setupView() {
        viewModel.currency = RateHelper.getByCurrencyCode(viewModel.currency.code?.lowercase() ?: "gbp")
    }
}
