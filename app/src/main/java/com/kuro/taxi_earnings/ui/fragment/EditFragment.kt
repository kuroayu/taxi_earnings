package com.kuro.taxi_earnings.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kuro.taxi_earnings.R

class EditFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_edit,container,false)
        val editSaveButton = view.findViewById<Button>(R.id.edit_save_button)

        editSaveButton.setOnClickListener {

            findNavController().popBackStack()

        }

        return view
    }
}