package com.kuro.taxi_earnings.ui.fragment

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigation.NavigationBarItemView
import com.google.android.material.navigation.NavigationView
import com.kuro.taxi_earnings.R
import com.kuro.taxi_earnings.databinding.FragmentInitialSettingBinding
import com.kuro.taxi_earnings.ui.viewmodel.InitialSettingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InitialSettingFragment : Fragment(),NumberPickerDialog.NoticeDialogListener{

    private val viewModel: InitialSettingViewModel by viewModels()

    private var _binding: FragmentInitialSettingBinding? = null
    private val binding get() = _binding!!



    override fun onNumberPickerDialogPositiveClick(dialog: DialogFragment, selectedYearItem: Int, selectedMonthItem:Int) {
        val YYmm = selectedYearItem.toString() + "年" + selectedMonthItem.toString() + "月"
        binding.initialSettingKbnText.setText(YYmm)
    }

    override fun onNumberPickerDialogNegativeClick(dialog: DialogFragment) {
        return
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentInitialSettingBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.initialSettingKbnText.setRawInputType(InputType.TYPE_NULL)
        binding.initialSettingKbnText.setOnClickListener {
            val numberPicker = NumberPickerDialog()
            val fragmentManager = childFragmentManager
            numberPicker.show(fragmentManager,"NumberPickerDialog")
        }
        binding.initialInputSaveButton.setOnClickListener {

            if (!viewModel.initialSettingKbnText.value.isNullOrBlank() && !viewModel.initialSettingClosingDateText.value.isNullOrBlank() &&
                !viewModel.initialSettingDaysText.value.isNullOrBlank() && !viewModel.initialSettingGoalText.value.isNullOrBlank()
            ) {
                viewModel.onButtonClick()
                findNavController().popBackStack()

            }else{
                val toast =
                    Toast.makeText(requireContext(), R.string.error_message, Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
