package com.kuro.taxi_earnings.ui.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.DialogFragment
import com.kuro.taxi_earnings.R
import java.time.LocalDate
import java.util.*

class DatePickerFragment(val editText: EditText) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    //日付が消えない
    //カレンダーオフ
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)



        var datePickerDialog = DatePickerDialog(
            requireContext(),
            android.R.style.Theme_Holo_Dialog,
            this,
            year,
            month,
            day,
        )

        datePickerDialog.datePicker.calendarViewShown = false

        //datepickerで年月だけ表示させたい
//        val dayId = Resources.getSystem().getIdentifier("day", "id", "android")
//        datePickerDialog.findViewById<EditText>(dayId).visibility = View.GONE

        return datePickerDialog
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
    //    val yearMonthEdit = activity?.findViewById<EditText>(R.id.yearMonth_edit)
        editText.setText(String.format("%d/%02d", year, month+1))
    }
}