package com.kuro.taxi_earnings.ui.viewmodel

import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuro.taxi_earnings.data.repository.InitialSettingRepository
import com.kuro.taxi_earnings.ui.fragment.NumberPickerDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class InitialSettingViewModel @Inject constructor(
    private val initialSettingRepository: InitialSettingRepository,
) : ViewModel() {

    private val _initialSettingKbnText:MutableLiveData<String> = MutableLiveData("")
    val initialSettingKbnText:LiveData<String> = _initialSettingKbnText

    val initialSettingClosingDateText = MutableLiveData<String>()
    val initialSettingDaysText = MutableLiveData<String>()
    val initialSettingGoalText = MutableLiveData<String>()



    fun onButtonClick() {
        initialSettingRepository.inputString("settingKbn",initialSettingKbnText.value.toString())
        initialSettingRepository.inputString("settingClosingDate",initialSettingClosingDateText.value.toString())
        initialSettingRepository.inputInt("settingDays",initialSettingDaysText.value.toString())
        initialSettingRepository.inputInt("settingGoal",initialSettingGoalText.value.toString())
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

