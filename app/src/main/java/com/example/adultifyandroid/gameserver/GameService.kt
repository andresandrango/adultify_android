package com.example.adultifyandroid.gameserver

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameService {
    val _baseUrl: String = "http://192.168.0.249:7070/"
    var api : GameServiceInterface = Retrofit.Builder().baseUrl(_baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        // we need to add converter factory to
        // convert JSON object to Java object
        .build().create(GameServiceInterface::class.java)
}