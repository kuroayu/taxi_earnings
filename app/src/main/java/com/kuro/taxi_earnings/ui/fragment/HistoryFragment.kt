package com.kuro.taxi_earnings.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kuro.taxi_earnings.HistoryAdapter
import com.kuro.taxi_earnings.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class HistoryFragment : Fragment() {

    val historyAdapter = HistoryAdapter(listOf(dummyData()))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_history, container, false)
        val dateEdit = view.findViewById<EditText>(R.id.yearMonth_edit)

        val date = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM")
        val formatted = date.format(formatter)

        dateEdit.setText(formatted)

        dateEdit.setOnClickListener {
            val datePickerFragment = DatePickerDialogFragment(dateEdit)
            datePickerFragment.show(requireFragmentManager(), "datePicker")
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = historyAdapter


        historyAdapter.setOnItemClickListener(object : HistoryAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int, data: List<dummyData>) {
                findNavController().navigate(R.id.action_history_button_to_editFragment)
            }
        })

        return view
    }
}


data class dummyData(
    val dummyDate: String = "2022年2月14日",
    val dummyTime: String = "30",
    val dummyEarning: String = "20000"
)