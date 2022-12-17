package com.example.adultifyandroid.gameserver

@kotlinx.serialization.Serializable
data class World(val name: String, val id: String, val citizens: List<Citizen>)