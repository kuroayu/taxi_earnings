package com.kuro.taxi_earnings.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kuro.taxi_earnings.R
import com.kuro.taxi_earnings.databinding.FragmentRecordBinding
import com.kuro.taxi_earnings.ui.viewmodel.InputViewModel
import com.kuro.taxi_earnings.ui.viewmodel.RecordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class RecordFragment : Fragment() {

    private val viewModel: RecordViewModel by viewModels()

    lateinit var initSharedPreferences:SharedPreferences
    var goalPreferencesData by Delegates.notNull<Int>()


    private lateinit var binding: FragmentRecordBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        initSharedPreferences = context.getSharedPreferences("initial_setting",Context.MODE_PRIVATE)
        goalPreferencesData = initSharedPreferences.getInt("settingGoal",-1)

        if(goalPreferencesData == -1)
        {
            findNavController().navigate(R.id.initialSettingFragment)
        }else{
            Log.d("goal",goalPreferencesData.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentRecordBinding.inflate(inflater, container, false)

        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.settingGoalText.value = goalPreferencesData.toString()

        binding.initialInputSaveButton.setOnClickListener {
            findNavController().navigate(R.id.action_input_button_to_inputFragment)
        }
        //前に¥つけたい(R.string.yenは用意したけど連結できない)
   //     binding.goalText.text = goalPreferencesData.toString()

    }
}