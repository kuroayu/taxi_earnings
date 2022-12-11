package com.kuro.taxi_earnings.ui.fragment

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kuro.taxi_earnings.R
import com.kuro.taxi_earnings.databinding.FragmentInputBinding
import com.kuro.taxi_earnings.ui.viewmodel.InputViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.properties.Delegates

@AndroidEntryPoint
class InputFragment : Fragment() {

    private val viewModel: InputViewModel by viewModels()

//    by activityViewModels {
//        val application = requireActivity().application as MainApplication
//        InputViewModelFactory(
//            application.salesRepository
//        )
//    }

    //get使っている意味調べる
    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!

    lateinit var initSharedPreferences: SharedPreferences
    var goalPreferencesData by Delegates.notNull<Int>()

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

        viewModel.initialSettingGoalText.value = goalPreferencesData.toString()

        binding.monthEditText.setOnClickListener {
            val numberPicker = NumberPickerDialog()
            val fragmentManager = parentFragmentManager
            numberPicker.show(fragmentManager,"NumberPickerDialog")
        }

        binding.inputSaveButton.setOnClickListener {

            if (!viewModel.initialSettingGoalText.value.isNullOrBlank()
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
