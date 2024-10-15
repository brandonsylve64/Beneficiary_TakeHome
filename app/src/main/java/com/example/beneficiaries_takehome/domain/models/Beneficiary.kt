package com.example.beneficiaries_takehome.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Beneficiary(
    val beneType: String,
    val beneficiaryAddress: BeneficiaryAddress,
    val dateOfBirth: String,
    val designationCode: String,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val phoneNumber: String,
    val socialSecurityNumber: String
) : Parcelable