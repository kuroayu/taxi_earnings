package com.kuro.taxi_earnings.ui.fragment


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.kuro.taxi_earnings.R
import com.kuro.taxi_earnings.databinding.FragmentInputBinding
import com.kuro.taxi_earnings.databinding.FragmentRecordBinding
import com.kuro.taxi_earnings.ui.viewmodel.RecordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

/**
 * Top画面：売上の進捗状況確認、売上入力画面遷移ボタン
 * 初回起動時はinitialSettingFragmentへ遷移
 */

@AndroidEntryPoint
class RecordFragment : Fragment() {

    private val viewModel: RecordViewModel by viewModels()

    lateinit var initSharedPreferences:SharedPreferences

    var kbnPreferencesData by Delegates.notNull<String>()
    var closingDateData by Delegates.notNull<String>()
    var daysData by Delegates.notNull<Int>()
    var goalPreferencesData by Delegates.notNull<Int>()

    //調べる：get()使用している意味
    private var _binding: FragmentRecordBinding? = null
    private val binding get() = _binding!!


    //初回起動判定、初回の場合はinitialSettingFragmentに遷移
    override fun onAttach(context: Context) {
        super.onAttach(context)

        initSharedPreferences = context.getSharedPreferences("initial_setting", Context.MODE_PRIVATE)
        goalPreferencesData = initSharedPreferences.getInt("settingGoal",-1)

        if(goalPreferencesData == -1)
        {
            findNavController().navigate(R.id.initialSettingFragment)
        }else{
            Log.d("goal",goalPreferencesData.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        goalPreferencesData = initSharedPreferences.getInt("settingGoal",-1)

        viewModel.init()

        //売上目標にデータをセット、初期値の-1だった場合は0をセットする
        if(goalPreferencesData == -1){
            viewModel.settingGoalText.value ="0"
        }
        viewModel.settingGoalText.value = goalPreferencesData.toString()



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = FragmentRecordBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        //inputFragmentに遷移
        binding.initialInputSaveButton.setOnClickListener {
            findNavController().navigate(R.id.action_input_button_to_inputFragment)
        }
    }
}