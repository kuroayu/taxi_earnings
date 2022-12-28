package com.kuro.taxi_earnings.ui.fragment

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kuro.taxi_earnings.HistoryAdapter
import com.kuro.taxi_earnings.R
import com.kuro.taxi_earnings.databinding.FragmentHistoryBinding
import com.kuro.taxi_earnings.ui.viewmodel.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HistoryFragment : Fragment(),NumberPickerDialogFragment.NoticeDialogListener{

    private val viewModel: HistoryViewModel by viewModels()


    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val historyAdapter = HistoryAdapter(listOf(dummyData()))

    override fun onNumberPickerDialogPositiveClick(dialog: DialogFragment, selectedYearItem: Int, selectedMonthItem:Int) {
        val selectedYearMonth = selectedYearItem.toString() + "年" + selectedMonthItem.toString() + "月"
        binding.yearMonthEdit.setText(selectedYearMonth)
    }
    override fun onNumberPickerDialogNegativeClick(dialog: DialogFragment) {
        return
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.yearMonthEdit.setRawInputType(InputType.TYPE_NULL)
        binding.yearMonthEdit.setOnClickListener {
            val numberPicker = NumberPickerDialogFragment()
            val fragmentManager = childFragmentManager
            numberPicker.show(fragmentManager,"NumberPickerDialog")
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = historyAdapter

        historyAdapter.setOnItemClickListener(object : HistoryAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int, data: List<dummyData>) {
                findNavController().navigate(R.id.action_history_button_to_editFragment)
            }
        })
    }
}


data class dummyData(
    val dummyDate: String = "2022年2月14日",
    val dummyTime: String = "30",
    val dummyEarning: String = "20000"
)