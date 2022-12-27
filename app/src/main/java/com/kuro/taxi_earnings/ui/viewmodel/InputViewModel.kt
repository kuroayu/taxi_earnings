package com.kuro.taxi_earnings.ui.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kuro.taxi_earnings.data.database.SalesData
import com.kuro.taxi_earnings.data.repository.SalesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class InputViewModel @Inject constructor(
    private val salesRepository: SalesRepository,
    ) :ViewModel() {

    var dateEdit = MutableLiveData<String>()
    val earningEdit = MutableLiveData<String>()
    val timesEdit = MutableLiveData<String>()
    val monthKbnEdit = MutableLiveData<String>()

    //初期値セット(勤務日：現在日付)
    fun init() {

        val currentDay = LocalDate.now()
        dateEdit.value = String.format(
            "%d年%02d月%02d日",
            currentDay.year,
            currentDay.month.value,
            currentDay.dayOfMonth
        )

    }


    fun onButtonClick() {
        viewModelScope.launch {

            //もし回数が未入力なら、０を足す
            val data = SalesData(
                dateEdit.value.toString(),
                earningEdit.value!!.toInt(),
                timesEdit.value!!.toInt(),
                monthKbnEdit.value.toString()
            )

            salesRepository.insert(data)

            Log.d("dataBase", salesRepository.allSalesData().toString())
            Log.d("dataBase", "database")
        }
    }

}

//
//class InputViewModelFactory(
//    private val salesRepository:SalesRepository
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(InputViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return InputViewModel(salesRepository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}