package com.example.adultifyandroid.ui.User

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adultifyandroid.gameserver.Citizen

class UserViewModel : ViewModel() {

    private var _user: MutableLiveData<Citizen> = MutableLiveData()

    init {
        _user.value = Citizen("42de169c-8b30-4ab6-9d3b-ebd3a999c659", "andres")

    }

    fun getUser() : LiveData<Citizen> {
        return _user
    }
}