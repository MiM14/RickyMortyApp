package com.moaimar.ricknmortyapp.features.characterslist.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.moaimar.ricknmortyapp.databinding.ItemCharacterListBinding
import com.moaimar.ricknmortyapp.features.characterslist.domain.CharactersFeed

class CharacterFeedViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun render(categoryModel: CharactersFeed, itemClick: ((Int) -> Unit)?) {
        val binding = ItemCharacterListBinding.bind(view)
    }
}