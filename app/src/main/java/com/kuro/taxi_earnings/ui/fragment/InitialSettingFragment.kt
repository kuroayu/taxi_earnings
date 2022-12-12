package com.kuro.taxi_earnings.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kuro.taxi_earnings.R
import com.kuro.taxi_earnings.databinding.FragmentInitialSettingBinding
import com.kuro.taxi_earnings.ui.viewmodel.InitialSettingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InitialSettingFragment : Fragment() {

    private val viewModel: InitialSettingViewModel by viewModels()

    private var _binding: FragmentInitialSettingBinding? = null
    private val binding get() = _binding!!

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

//            viewModel.onButtonClick{
//                val toast =
//                    Toast.makeText(requireContext(), R.string.error_message, Toast.LENGTH_SHORT)
//                toast.show()
//            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
