package com.example.adultifyandroid.ui.home

import android.content.Context
import com.example.adultifyandroid.gameserver.World
import dagger.assisted.AssistedFactory

@AssistedFactory
interface WordAdapterFactory {
    fun create(itemsList: MutableList<World>, context: Context) : WorldAdapter
}