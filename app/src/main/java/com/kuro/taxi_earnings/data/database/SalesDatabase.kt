package com.kuro.taxi_earnings.data.database

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kuro.taxi_earnings.di.DatabaseModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Databaseの生成
 * @Database(第3引数はスキーマのバージョン履歴を保持するか)
 * DBが存在する時はDBを返す、nullの時はDBを作成する
 */

@Database(entities = [SalesData::class],version = 1,exportSchema = false)
abstract class SalesDatabase: RoomDatabase() {

    abstract fun salesDao(): SalesDao

}