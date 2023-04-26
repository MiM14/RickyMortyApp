package com.moaimar.ricknmortyapp.features.locations.presentation.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.moaimar.ricknmortyapp.databinding.ItemLocationsListBinding
import com.moaimar.ricknmortyapp.features.locations.domain.LocationsFeed

class LocationsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun render(locations: LocationsFeed, itemClick: ((Int) -> Unit)?) {
        val binding = ItemLocationsListBinding.bind(view)
        binding.apply {
            title.text = locations.name
            type.text = locations.type
            dimension.text = locations.dimension

            view.setOnClickListener {
                itemClick?.invoke(locations.id)
            }
        }
    }

}