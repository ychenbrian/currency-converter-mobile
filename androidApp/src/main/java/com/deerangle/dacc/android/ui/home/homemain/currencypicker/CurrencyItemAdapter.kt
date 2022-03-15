package com.deerangle.dacc.android.ui.home.homemain.currencypicker

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.deerangle.dacc.android.ItemCurrencyBinding
import com.deerangle.dacc.android.R
import com.deerangle.dacc.android.application.MainApplication
import com.deerangle.dacc.android.utils.base.BaseAdapter
import com.deerangle.dacc.data.model.Currency
import com.deerangle.dacc.ui.home.homemain.currencypicker.CurrencyItemViewModel
import com.qmuiteam.qmui.kotlin.onClick

interface CurrencyItemCallback {
    fun currencyClicked(currency: Currency)
}

class CurrencyItemAdapter(private val callback: CurrencyItemCallback) : BaseAdapter<Currency, ItemCurrencyBinding>(
    R.layout.item_currency,
    object: DiffUtil.ItemCallback<Currency>() {
        override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem.code == newItem.code
        }
    }
) {
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun bind(binding: ItemCurrencyBinding, item: Currency) {
        binding.apply {
            itemCurrencyIvFlag.setImageDrawable(MainApplication.getAppContext().getDrawable(item.flag))
            itemCurrencyTvCurrency.text = item.getFullName()
            rootView.onClick {
                callback.currencyClicked(item)
            }
        }
    }
}