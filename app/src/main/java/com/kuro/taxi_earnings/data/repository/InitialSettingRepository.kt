package com.kuro.taxi_earnings.data.repository
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class InitialSettingRepository @Inject constructor(
    @ApplicationContext private val context: Context
){

    fun input(goalEdit: String) {

        val goalInt = goalEdit.toInt()

        context.getSharedPreferences("initial_setting", Context.MODE_PRIVATE).edit().apply {
            putInt("settingGoal", goalInt)
            apply()
        }

    }
}