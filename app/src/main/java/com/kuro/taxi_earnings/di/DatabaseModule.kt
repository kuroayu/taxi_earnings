package com.kuro.taxi_earnings.di

import android.content.Context
import androidx.room.Room
import com.kuro.taxi_earnings.data.database.SalesDao
import com.kuro.taxi_earnings.data.database.SalesDatabase
import com.kuro.taxi_earnings.data.repository.SalesRepository
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//調べる:以下の処理の意味、使い方

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context):SalesDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            SalesDatabase::class.java,
            "Sales.db"
        )
            .build()
    }
    @Singleton
    @Provides
    fun providesSalesDao(database: SalesDatabase):SalesDao{
        return database.salesDao()
    }


    @Module
    @InstallIn(SingletonComponent::class)
    object providesSalesRepository{
    @Singleton
    @Provides
     fun bindRepository(salesDao: SalesDao):SalesRepository{
        return SalesRepository(salesDao)
    }
}
}