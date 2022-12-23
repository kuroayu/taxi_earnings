package com.kuro.taxi_earnings.data.repository

import com.kuro.taxi_earnings.data.database.SalesDao
import com.kuro.taxi_earnings.data.database.SalesData
import javax.inject.Inject

class SalesRepository @Inject constructor(private val salesDao:SalesDao) {

    suspend fun allSalesData():List<SalesData>{
        return salesDao.allSalesData()
    }

    suspend fun insert(salesData: SalesData){
        salesDao.insert(salesData)
    }
}