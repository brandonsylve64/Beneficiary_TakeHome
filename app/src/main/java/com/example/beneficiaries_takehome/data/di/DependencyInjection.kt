package com.example.beneficiaries_takehome.data.di

import android.content.Context
import com.example.beneficiaries_takehome.data.RepositoryImpl
import com.example.beneficiaries_takehome.domain.Repository

object DependencyInjection {
    // this method provides an instance of BeneficiaryRepository
    fun provideRepository(context: Context): Repository {
        return RepositoryImpl(context)
    }
}