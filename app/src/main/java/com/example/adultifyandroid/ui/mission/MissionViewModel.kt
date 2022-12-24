package com.example.adultifyandroid.ui.mission

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adultifyandroid.gameserver.GameService
import com.example.adultifyandroid.gameserver.Mission
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MissionViewModel @Inject internal constructor(
    private val gameService: GameService
): ViewModel() {

    var missions: MutableLiveData<List<Mission>> = MutableLiveData()

    fun fetchMissions(cId: String) {
        viewModelScope.launch {
            val result = gameService.api.listMissionsOfCitizen(cId)

            if (result.isSuccessful) {
                missions.postValue(result.body())
            }

        }
    }
}