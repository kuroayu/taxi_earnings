package com.kuro.taxi_earnings.data.repository
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class InitialSettingRepository @Inject constructor(
    @ApplicationContext private val context: Context
){

    fun inputInt(itemName:String ,item: String) {
        val itemInt = item.toInt()
        context.getSharedPreferences("initial_setting", Context.MODE_PRIVATE).edit().apply {
            putInt(itemName, itemInt)
            apply()
        }
    }

    fun inputString(itemName: String,item:String){
        context.getSharedPreferences("initial_setting", Context.MODE_PRIVATE).edit().apply(){
            putString(itemName,item)
            apply()
        }
    }
}