package com.example.adultifyandroid.ui.citizen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adultifyandroid.gameserver.Citizen
import com.example.adultifyandroid.gameserver.GameService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitizenViewModel @Inject internal constructor(
    private val gameService: GameService
) : ViewModel() {

    var citizens: MutableLiveData<List<Citizen>> = MutableLiveData()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            gameService.getCitizens().collect {
                citizens.postValue(it)
            }
        }
    }
}