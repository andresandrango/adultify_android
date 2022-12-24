package com.example.adultifyandroid.gameserver

import kotlinx.datetime.LocalDate

@kotlinx.serialization.Serializable
data class Mission(val id: String,
                   val name: String,
                   val state: String, // TODO use enum
                   val owner: Citizen?,
                   val reward: Life,
                   val scheduledAt: LocalDate?,
                   val dueAt: LocalDate?,
                   val completedAt: LocalDate?) {}