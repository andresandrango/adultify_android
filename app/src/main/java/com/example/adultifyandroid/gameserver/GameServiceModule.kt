package com.example.adultifyandroid.gameserver

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class GameServiceModule {

    @Provides
    @Singleton
    fun provideGameService() :GameService {
        return GameService();
    }
}