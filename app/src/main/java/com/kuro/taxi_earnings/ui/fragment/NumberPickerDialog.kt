package com.kuro.taxi_earnings.ui.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import com.kuro.taxi_earnings.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

//月度区分用NumberPicker
class NumberPickerDialog:DialogFragment() {

    private lateinit var dialogView: View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_numberpicker,null)
        val builder = AlertDialog.Builder(context)

        builder.setView(dialogView)
        builder.setTitle("何月分の売上か設定してください")
        builder.setMessage("2022年12月度の区分で設定した場合、今回の売上は2022年10月分に振り分けられます")
        builder.setPositiveButton("OK"){dialog,id ->}
        builder.setNegativeButton("キャンセル"){dialog,id ->}

        val current = LocalDateTime.now()

        val yearNumberPicker = dialogView.findViewById<NumberPicker>(R.id.yearNumberPicker)
        yearNumberPicker.minValue = 2022
        yearNumberPicker.maxValue = 2050
        val yearFormatter = DateTimeFormatter.ofPattern("yyyy")
        val thisYear = current.format(yearFormatter).toInt()
        yearNumberPicker.value = thisYear

        val monthNumberPicker = dialogView.findViewById<NumberPicker>(R.id.monthNumberPicker)
        monthNumberPicker.minValue = 1
        monthNumberPicker.maxValue = 12
        val monthFormatter = DateTimeFormatter.ofPattern("MM")
        val thisMonth = current.format(monthFormatter).toInt()
        monthNumberPicker.value = thisMonth


        return builder.create()
    }
}