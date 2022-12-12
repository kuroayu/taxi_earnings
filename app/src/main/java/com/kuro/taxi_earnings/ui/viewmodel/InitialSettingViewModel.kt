package com.kuro.taxi_earnings.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuro.taxi_earnings.data.repository.InitialSettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class InitialSettingViewModel @Inject constructor(
    private val initialSettingRepository: InitialSettingRepository,
) : ViewModel() {

    val initialSettingKbnText = MutableLiveData<String>()
    val initialSettingClosingDateText = MutableLiveData<String>()
    val initialSettingDaysText = MutableLiveData<String>()
    val initialSettingGoalText = MutableLiveData<String>()

    fun onButtonClick() {
        val initialSettingKbnString = initialSettingKbnText.value.toString()
        initialSettingRepository.input(initialSettingKbnString)
        val initialSettingClosingDateString = initialSettingClosingDateText.value.toString()
        initialSettingRepository.input(initialSettingClosingDateString)
        val initialSettingDaysString = initialSettingDaysText.value.toString()
        initialSettingRepository.input(initialSettingDaysString)
        val initialSettingGoalString = initialSettingGoalText.value.toString()
        initialSettingRepository.input(initialSettingGoalString)


    }


//    //ラムダ、ifができなかったら関数渡す
//    fun onButtonClick(onInvalidate: ()->Unit) {
////        if (!initialSettingDeadlineText.value.isNullOrBlank()
////            && !initialSettingGoalText.value.isNullOrBlank()
////        ) {
//            val initialSettingDeadlineString = initialSettingDeadlineText.value.toString()
//            val initialSettingGoalString = initialSettingGoalText.value.toString()
//            initialSettingRepository.input(initialSettingDeadlineString, initialSettingGoalString)
//
//
////        } else {
////            onInvalidate()
////        }
//    }
}

