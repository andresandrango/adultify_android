package com.example.adultifyandroid.gameserver

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GameServiceInterface {

    @GET("worlds")
    suspend fun listWorlds(): Response<List<World>>

    @GET("worlds/{wId}")
    suspend fun getWorld(@Path("wId") wId: String?): Response<World>

    @DELETE("worlds/{wId}")
    suspend fun deleteWorld(@Path("wId") wId: String): Response<ResponseBody>

    @GET("citizens")
    suspend fun listCitizens(): Response<List<Citizen>>

    @DELETE("citizens/{cId}")
    suspend fun deleteCitizen(@Path("cId") cId: String): Response<ResponseBody>

    @GET("citizens/{cId}/worlds")
    suspend fun listWorldsOfCitizen(@Path("cId") cId: String): Response<List<World>>

    @GET("citizens/{cId}/missions")
    suspend fun listMissionsOfCitizen(@Path("cId") cId: String): Response<List<Mission>>

    @POST("missions/create")
    suspend fun createMission(@Body mission: Mission): Response<Mission>
}