package com.kuro.taxi_earnings.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuro.taxi_earnings.data.repository.SalesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel  @Inject constructor(
    private val salesRepository: SalesRepository
    ) : ViewModel() {

 //   val settingDeadlineText = MutableLiveData<String>()
    val settingGoalText = MutableLiveData<String>()
    val settingDaysText = MutableLiveData<String>()


    fun init(){
        viewModelScope.launch {
          val result =  salesRepository.allSalesData().getOrNull(0)
            settingDaysText.value=result?.date.toString()
        }
    }
}