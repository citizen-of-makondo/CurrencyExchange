package com.alexilinskiy.currencyexchange.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexilinskiy.currencyexchange.R
import com.alexilinskiy.currencyexchange.data.db.rates.RatesModelDB

class RateViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val currency: TextView = itemView.findViewById(R.id.currency)
    private val rate: TextView = itemView.findViewById(R.id.rate)
    private val favoriteIcon: ImageView = itemView.findViewById(R.id.favoriteIcon)

    fun bind(rate: RatesModelDB, onFavoriteIconClick: (RatesModelDB) -> Unit) {
        currency.text = rate.name
        this.rate.text = rate.count.toString()
        val drawableId = if (rate.isFavorite) {
            R.drawable.baseline_star_24_black
        } else {
            R.drawable.baseline_star_24
        }
        favoriteIcon.setImageResource(drawableId)
        favoriteIcon.setOnClickListener {
            onFavoriteIconClick.invoke(rate)
        }
    }

}
