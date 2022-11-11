package com.alexilinskiy.currencyexchange.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.alexilinskiy.currencyexchange.R
import com.alexilinskiy.currencyexchange.data.db.rates.RatesModelDB

class RatesListAdapter(
    private val onFavoriteIconClick: (RatesModelDB) -> Unit
) : ListAdapter<RatesModelDB, RateViewHolder>(DiffCallBack()) {
    class DiffCallBack : DiffUtil.ItemCallback<RatesModelDB>() {
        override fun areItemsTheSame(oldItem: RatesModelDB, newItem: RatesModelDB): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RatesModelDB, newItem: RatesModelDB): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.shortName == newItem.shortName
                    && oldItem.isFavorite == newItem.isFavorite
                    && oldItem.count == newItem.count
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        return RateViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rates_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(
            item,
            onFavoriteIconClick
        )
    }

}
