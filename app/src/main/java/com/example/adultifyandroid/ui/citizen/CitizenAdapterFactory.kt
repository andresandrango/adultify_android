package com.example.adultifyandroid.ui.citizen

import com.example.adultifyandroid.gameserver.Citizen
import dagger.assisted.AssistedFactory

@AssistedFactory
interface CitizenAdapterFactory {
    fun create(itemsList: MutableList<Citizen>) : CitizenAdapter
}