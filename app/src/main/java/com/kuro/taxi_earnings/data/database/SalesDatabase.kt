package com.kuro.taxi_earnings.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Database(entities = [SalesData::class],version = 1,exportSchema = false)
abstract class SalesDatabase: RoomDatabase() {

    abstract fun salesDao():SalesDao
}