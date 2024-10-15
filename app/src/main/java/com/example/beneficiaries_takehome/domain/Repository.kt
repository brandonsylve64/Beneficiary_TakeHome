package com.example.beneficiaries_takehome.domain

import com.example.beneficiaries_takehome.domain.models.Beneficiary

interface Repository {
    fun getBeneficiaries(): List<Beneficiary>
}