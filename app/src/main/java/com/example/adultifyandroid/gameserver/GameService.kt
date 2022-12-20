package com.example.adultifyandroid.gameserver

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URL
import kotlinx.coroutines.flow.Flow

class GameService {

    private val _HOST :String = "http://192.168.0.249:7070";

    suspend fun getWorlds() : Flow<List<World>> {
        return flow {
            val response = withContext(Dispatchers.IO) {
                URL("${_HOST}/worlds")
                    .openStream()
            }
                .bufferedReader()
                .use { it.readText() }

            emit(Json.decodeFromString<List<World>>(response))
        }.catch { cause -> println(cause) }
    }

    suspend fun getCitizens() : Flow<List<Citizen>> {
        return flow {
            val response = withContext(Dispatchers.IO) {
                URL("${_HOST}/citizens")
                    .openStream()
            }
                .bufferedReader()
                .use { it.readText() }

            emit(Json.decodeFromString<List<Citizen>>(response))
        }.catch { cause -> println(cause) }
    }
}