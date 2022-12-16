package com.kuro.taxi_earnings.ui.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import com.kuro.taxi_earnings.R
import java.lang.ClassCastException
import java.time.LocalDateTime
import kotlin.properties.Delegates

//月度区分用NumberPicker:yyyyMM
class NumberPickerDialog():DialogFragment(){

    private lateinit var dialogView: View

    private lateinit var listener: NoticeDialogListener // 親に渡すためのリスナー定義
    private var selectedYearItem by Delegates.notNull<Int>() // 選択した年のアイテム格納
    private var selectedMonthItem by Delegates.notNull<Int>()// 選択した年のアイテム格納


    interface NoticeDialogListener {
        fun onNumberPickerDialogPositiveClick(dialog: DialogFragment, selectedYearItem: Int, selectedMonthItem:Int)
        fun onNumberPickerDialogNegativeClick(dialog: DialogFragment)
    }

    //onAttachメソッドを使用することで、親画面へイベントを伝搬することが出来るようになる？
    //Activityに関連図けられたときに呼び出される
    //ClassCastExceptionについて
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            val fragment = parentFragment
            this.listener = fragment as NoticeDialogListener
        }catch (e: ClassCastException){
            throw  ClassCastException("$context must implement NoticeDialogListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_numberpicker,null)
        val builder = AlertDialog.Builder(context)

        builder.setView(dialogView)
        builder.setTitle("月度区分の設定")
        builder.setMessage("設定した年月に売上を振り分けます")
        //setPositiveButton(テキストに表示する文字列、ボタンを押下後の処理)
        builder.setPositiveButton("OK"){_,_ -> this.listener.onNumberPickerDialogPositiveClick(this,this.selectedYearItem,selectedMonthItem)}
        builder.setNegativeButton("キャンセル"){_,_ -> this.listener.onNumberPickerDialogNegativeClick(this) }

        val current = LocalDateTime.now()

        val yearNumberPicker = dialogView.findViewById<NumberPicker>(R.id.yearNumberPicker)
        yearNumberPicker.setOnValueChangedListener(object :NumberPicker.OnValueChangeListener{
            override fun onValueChange(picker: NumberPicker?, old: Int, new: Int) {
                selectedYearItem = new
            }
        })
        yearNumberPicker.minValue = current.year-1
        yearNumberPicker.maxValue = current.year+30
        yearNumberPicker.value = current.year
        selectedYearItem = yearNumberPicker.value

        val monthNumberPicker = dialogView.findViewById<NumberPicker>(R.id.monthNumberPicker)
        monthNumberPicker.setOnValueChangedListener(object :NumberPicker.OnValueChangeListener{
            override fun onValueChange(picker: NumberPicker?, old: Int, new: Int) {
                selectedMonthItem = new
            }
        })
        monthNumberPicker.minValue = 1
        monthNumberPicker.maxValue = 12
        monthNumberPicker.value = current.monthValue
        selectedMonthItem = monthNumberPicker.value

        return builder.create()
    }


   override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }


}
