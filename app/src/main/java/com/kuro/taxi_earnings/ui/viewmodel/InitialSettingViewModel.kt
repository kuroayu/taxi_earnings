package com.kuro.taxi_earnings.ui.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuro.taxi_earnings.data.repository.InitialSettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import javax.inject.Inject


@HiltViewModel
class InitialSettingViewModel @Inject constructor(
    private val initialSettingRepository: InitialSettingRepository,
) : ViewModel() {

    val initialSettingKbnText = MutableLiveData<String>()
    val initialSettingClosingDateText = MutableLiveData<String>()
    val initialSettingDaysText = MutableLiveData<String>()
    val initialSettingGoalText = MutableLiveData<String>()

    //入力チェックと登録
    fun onButtonClick():String {

        var str = ""

        if (!initialSettingKbnText.value.isNullOrBlank() && !initialSettingClosingDateText.value.isNullOrBlank() &&
            !initialSettingDaysText.value.isNullOrBlank() && !initialSettingGoalText.value.isNullOrBlank()
        ) {

            val monthLength = endOfMonth()
            if (initialSettingClosingDateText.value!!.toInt() <= monthLength &&
                initialSettingDaysText.value!!.toInt() <= monthLength
            ) {

                initialSettingRepository.inputString(
                    "settingKbn",
                    initialSettingKbnText.value.toString()
                )
                initialSettingRepository.inputInt(
                    "settingClosingDate",
                    initialSettingClosingDateText.value.toString()
                )
                initialSettingRepository.inputInt(
                    "settingDays",
                    initialSettingDaysText.value.toString()
                )
                initialSettingRepository.inputInt(
                    "settingGoal",
                    initialSettingGoalText.value.toString()
                )

                return str

            }else{
                str ="締め日または勤務日数が正しくありません"
                return str
            }
        } else {
            str = "入力が完了していません"
            return str
        }
   }

    //月度区分で選択された年月の最終日を取得する
    fun endOfMonth():Int{

        var yearMonth = ""
        val monthText = initialSettingKbnText.value.toString().drop(5).dropLast(1)

        if(monthText.length == 1){
         yearMonth = initialSettingKbnText.value.toString().dropLast(2) + monthText.padStart(2,'0') +"月"
        }else{
            yearMonth = initialSettingKbnText.value.toString()
        }

        val formatter = DateTimeFormatter.ofPattern("yyyy年MM月")
        val kbnParse = YearMonth.parse(yearMonth ,formatter)

        return kbnParse.lengthOfMonth()
    }
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



