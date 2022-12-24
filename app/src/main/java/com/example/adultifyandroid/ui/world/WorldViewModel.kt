package com.example.adultifyandroid.ui.world

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adultifyandroid.gameserver.GameService
import com.example.adultifyandroid.gameserver.World
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorldViewModel @Inject internal constructor(
    private val gameService: GameService
) : ViewModel() {

    var worlds: MutableLiveData<List<World>> = MutableLiveData()

    var counter: MutableLiveData<Int> = MutableLiveData(0)

    fun fetchWorlds(cId : String) {
        counter.postValue(counter.value?.plus(1))
        viewModelScope.launch {
            val result = gameService.api.listWorldsOfCitizen(cId)
            if (result.isSuccessful) {
                worlds.postValue(result.body())
            }
        }
    }
}