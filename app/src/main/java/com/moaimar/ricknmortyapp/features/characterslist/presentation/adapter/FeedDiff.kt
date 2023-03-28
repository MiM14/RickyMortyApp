package com.moaimar.ricknmortyapp.features.characterslist.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.moaimar.ricknmortyapp.features.characterslist.domain.CharactersFeed

class FeedDiff (): DiffUtil.ItemCallback<CharactersFeed>(){
    override fun areItemsTheSame(oldItem: CharactersFeed, newItem: CharactersFeed): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: CharactersFeed, newItem: CharactersFeed): Boolean {
        return oldItem == newItem
    }

}