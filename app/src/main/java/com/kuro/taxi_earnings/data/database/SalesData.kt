package com.kuro.taxi_earnings.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "sales_table")
data class SalesData(
    @PrimaryKey
    val date:String,
    val sales:Int,
    val times:Int,
    val month:String
)