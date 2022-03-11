package com.deerangle.dacc.android.ui.home.homemain.currencypicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.deerangle.dacc.android.R
import com.deerangle.dacc.ui.home.homemain.currencypicker.CurrencyItemViewModel


class CurrencyAdapter(private val mList: List<CurrencyItemViewModel>) : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_currency, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewModel = mList[position]

        holder.imageView.setImageResource(viewModel.currency.flag)
        holder.textView.text = viewModel.currency.getName()
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.item_currency_iv_flag)
        val textView: TextView = itemView.findViewById(R.id.item_currency_tv_currency)
    }
}