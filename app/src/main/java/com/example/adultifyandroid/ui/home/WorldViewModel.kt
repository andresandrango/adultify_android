package com.example.adultifyandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adultifyandroid.gameserver.GameService
import com.example.adultifyandroid.gameserver.World
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorldViewModel @Inject internal constructor(
    private val gameService: GameService,
) : ViewModel() {

    var worlds: MutableLiveData<List<World>> = MutableLiveData()

//    fun loadWorlds() = viewModelScope.launch {
//        gameService.getWorlds().collect {
//            worlds.postValue(it)
//        }
//    }

    init {
        viewModelScope.launch {
            gameService.getWorlds().collect {
                worlds.postValue(it)
            }
        }
    }
}