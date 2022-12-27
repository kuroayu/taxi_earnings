package com.kuro.taxi_earnings.ui.fragment


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kuro.taxi_earnings.R
import com.kuro.taxi_earnings.data.database.SalesDao
import com.kuro.taxi_earnings.data.database.SalesDatabase
import com.kuro.taxi_earnings.data.repository.SalesRepository
import com.kuro.taxi_earnings.databinding.FragmentInputBinding
import com.kuro.taxi_earnings.di.DatabaseModule
import com.kuro.taxi_earnings.ui.activity.MainApplication
import com.kuro.taxi_earnings.ui.viewmodel.InputViewModel

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * 売上入力画面：yyyy年MM月
 * DBに勤務日、売上、営業回数、月度区分を保存
 */

@AndroidEntryPoint
class InputFragment : Fragment() ,NumberPickerDialogFragment.NoticeDialogListener{

  //  @Inject lateinit var  database: SalesDatabase

    private val viewModel:InputViewModel by viewModels()


    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!

    lateinit var initSharedPreferences: SharedPreferences
    var goalPreferencesData by Delegates.notNull<Int>()

    override fun onNumberPickerDialogPositiveClick(dialog: DialogFragment, selectedYearItem: Int, selectedMonthItem:Int) {
        val selectedYearMonth = selectedYearItem.toString() + "年" + selectedMonthItem.toString() + "月"
        binding.monthEditText.setText(selectedYearMonth)
    }
    override fun onNumberPickerDialogNegativeClick(dialog: DialogFragment) {
        return
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initSharedPreferences =
            context.getSharedPreferences("initial_setting", Context.MODE_PRIVATE)
        goalPreferencesData = initSharedPreferences.getInt("settingGoal", -1)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.init()

        binding.dateEditText.setRawInputType(InputType.TYPE_NULL)
        binding.dateEditText.setOnClickListener {
            val datePickerFragment = DatePickerDialogFragment(binding.dateEditText)
            datePickerFragment.show(requireFragmentManager(), "datePicker")
        }

        binding.monthEditText.setRawInputType(InputType.TYPE_NULL)
        binding.monthEditText.setOnClickListener {
            val numberPicker = NumberPickerDialogFragment()
            val fragmentManager = childFragmentManager
            numberPicker.show(fragmentManager,"NumberPickerDialog")
        }

        binding.inputSaveButton.setOnClickListener {

            if (!viewModel.dateEdit.value.isNullOrBlank() &&
                !viewModel.earningEdit.value.isNullOrBlank() &&
                !viewModel.timesEdit.value.isNullOrBlank() &&
                !viewModel.monthKbnEdit.value.isNullOrBlank()
            ) {
                viewModel.onButtonClick()
                findNavController().popBackStack()

            } else {
                val toast =
                    Toast.makeText(requireContext(), R.string.error_message, Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }
}
