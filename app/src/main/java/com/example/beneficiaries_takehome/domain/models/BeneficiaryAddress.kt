package com.example.beneficiaries_takehome.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BeneficiaryAddress(
    val city: String,
    val country: String,
    val firstLineMailing: String,
    val scndLineMailing: String?,
    val stateCode: String,
    val zipCode: String
) : Parcelable