package com.example.adultifyandroid.ui.mission

import com.example.adultifyandroid.gameserver.Mission
import dagger.assisted.AssistedFactory

@AssistedFactory
interface MissionAdapterFactory {
    fun create(items: MutableList<Mission>) : MissionAdapter
}