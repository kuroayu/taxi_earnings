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

/**
 * 売上月度区分用NumberPickerFragment：yyyy年MM月
 * year:現在年-1 ~ 現在年+30
 * month:1~12
 */

class NumberPickerDialog:DialogFragment(){

    private lateinit var dialogView: View

    // 親に渡すためのリスナー, 選択された年/月の定義
    private lateinit var listener: NoticeDialogListener
    private var selectedYearItem by Delegates.notNull<Int>()
    private var selectedMonthItem by Delegates.notNull<Int>()

    interface NoticeDialogListener {
        fun onNumberPickerDialogPositiveClick(dialog: DialogFragment, selectedYearItem: Int, selectedMonthItem:Int)
        fun onNumberPickerDialogNegativeClick(dialog: DialogFragment)
    }

    //調べる：「onAttachメソッドを使用することで、親画面へイベントを伝搬することが出来るようになる」の意味
    //　　　　親画面に子画面の値をセットする際の処理方法(listener,onValueChange 等)
    //　　　　ClassCastException
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
        builder.setTitle(R.string.month)
        builder.setMessage(R.string.month_explanation)

        //調べる：「SAM変換、setPositiveButton()の2つ目の関数を()の外に出してラムダ式で表している」の意味
        builder.setPositiveButton(R.string.ok){_,_ -> this.listener.onNumberPickerDialogPositiveClick(this,this.selectedYearItem,selectedMonthItem)}
        builder.setNegativeButton(R.string.cancel){_,_ -> this.listener.onNumberPickerDialogNegativeClick(this) }

        val current = LocalDateTime.now()

        //yearNumberPicker作成
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

        //monthNumberPicker作成
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
