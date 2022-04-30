package com.projects.myapplication.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.projects.myapplication.api.CurrencyRepo

class MyViewModelFactory constructor(private val repository: CurrencyRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CurrencyViewModel::class.java)) {
            CurrencyViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}