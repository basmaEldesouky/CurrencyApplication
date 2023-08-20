package com.example.presentation.currencyRates.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.RatesX
import com.example.presentation.databinding.ItemHistoricalDataBinding

class HistoricalDataViewHolder (private val itemBinding: ItemHistoricalDataBinding): RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(rates: RatesX) {
        itemBinding.tvCurrencySymbol.text = "AFN"
        itemBinding.tvCurrencyRate.text = rates.AFN.toString()
    }

}