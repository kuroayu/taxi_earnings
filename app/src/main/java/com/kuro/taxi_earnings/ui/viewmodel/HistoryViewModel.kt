package com.kuro.taxi_earnings.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.kuro.taxi_earnings.data.repository.SalesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val salesRepository: SalesRepository)
    :ViewModel(){
}