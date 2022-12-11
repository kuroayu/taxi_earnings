package com.kuro.taxi_earnings.data.database

import androidx.room.*
import com.kuro.taxi_earnings.data.repository.SalesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



//抽象クラス(abstract)とinterfaceについて勉強しなおす



@Dao
interface SalesDao  {
    @Query("SELECT * FROM sales_table ORDER BY date DESC")
    suspend fun allSalesData(): List<SalesData>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(salesData: SalesData)

}


