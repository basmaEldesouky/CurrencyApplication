package com.example.presentation.currencyRates.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.entity.RatesX
import com.example.presentation.currencyRates.viewHolders.HistoricalDataViewHolder
import com.example.presentation.databinding.ItemHistoricalDataBinding


class HistoricalDataAdapter: ListAdapter<RatesX, HistoricalDataViewHolder>(RatesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricalDataViewHolder {
        val itemBinding = ItemHistoricalDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoricalDataViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: HistoricalDataViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RatesDiffCallback : DiffUtil.ItemCallback<RatesX>() {
        override fun areItemsTheSame(
            oldItem: RatesX,
            newItem: RatesX
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: RatesX,
            newItem: RatesX
        ): Boolean {
            return oldItem == newItem
        }
    }
}