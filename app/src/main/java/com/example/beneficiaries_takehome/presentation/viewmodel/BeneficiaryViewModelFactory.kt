package com.example.beneficiaries_takehome.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.beneficiaries_takehome.domain.Repository

class BeneficiaryViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {
    // Create instance of the BeneficiaryViewModel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeneficiaryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BeneficiaryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}