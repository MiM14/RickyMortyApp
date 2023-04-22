package com.moaimar.ricknmortyapp.features.characterslist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import com.moaimar.ricknmortyapp.R
import com.moaimar.ricknmortyapp.features.characterslist.domain.CharactersFeed
import javax.inject.Inject

class CharacterFeedAdapter @Inject constructor() :
    ListAdapter<CharactersFeed, CharacterFeedViewHolder>(
        AsyncDifferConfig.Builder(FeedDiff()).build()
    ) {

    private var itemClick: ((Int) -> Unit)? = null

    fun setOnClickedItem(itemClick: (Int) -> Unit) {
        this.itemClick = itemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterFeedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character_list, parent, false)
        return CharacterFeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterFeedViewHolder, position: Int) {
        holder.render(currentList[position], itemClick)
    }

    override fun getItemCount(): Int = currentList.size

}