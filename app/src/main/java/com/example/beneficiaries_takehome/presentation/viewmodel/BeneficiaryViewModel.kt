package com.example.beneficiaries_takehome.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beneficiaries_takehome.domain.Repository
import com.example.beneficiaries_takehome.domain.models.Beneficiary

class BeneficiaryViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _beneficiaries = MutableLiveData<List<Beneficiary>>()

    val beneficiary: LiveData<List<Beneficiary>>
        get() = _beneficiaries

    init {
        getBeneficiariesFromRepository()
    }

    private fun getBeneficiariesFromRepository() {
        _beneficiaries.value = repository.getBeneficiaries()
    }
}