package com.kuro.taxi_earnings.ui.viewmodel

import android.app.DatePickerDialog
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuro.taxi_earnings.data.database.SalesData
import com.kuro.taxi_earnings.data.repository.InitialSettingRepository
import com.kuro.taxi_earnings.data.repository.SalesRepository
import com.kuro.taxi_earnings.ui.fragment.dummyData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.coroutines.coroutineContext
import kotlin.math.log

@HiltViewModel
class InputViewModel @Inject constructor(
    private val salesRepository: SalesRepository,
    private val initialSettingRepository: InitialSettingRepository
    ) :ViewModel(){

    val initialSettingGoalText = MutableLiveData<String>()
    val dateEdit =MutableLiveData<String>()
    val earningEdit =MutableLiveData<String>()
    val timesEdit =MutableLiveData<String>()
    val monthEdit =MutableLiveData<String>()



    fun onButtonClick() {

        val initialSettingGoalString = initialSettingGoalText.value.toString()
        initialSettingRepository.inputInt("settingGoal",initialSettingGoalString)

        //もし回数が未入力なら、０を足す
        val data = SalesData(
            dateEdit.value.toString(),
            earningEdit.value!!.toInt(),
            timesEdit.value!!.toInt(),
            monthEdit.value.toString())

      Log.d("salesData", data.toString())
     //   salesRepository.insert(data)

    }
}

//windws
//class InputViewModelFactory(
//    private val salesRepository: SalesRepository
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(InputViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return InputViewModel(salesRepository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}