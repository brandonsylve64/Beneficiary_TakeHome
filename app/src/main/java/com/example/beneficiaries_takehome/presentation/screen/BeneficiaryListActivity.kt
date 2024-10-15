package com.example.beneficiaries_takehome.presentation.screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beneficiaries_takehome.data.di.DependencyInjection
import com.example.beneficiaries_takehome.presentation.adapter.BeneficiaryListAdapter
import com.example.beneficiaries_takehome.presentation.viewmodel.BeneficiaryViewModel
import com.example.beneficiaries_takehome.presentation.viewmodel.BeneficiaryViewModelFactory

class BeneficiaryListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var beneficiaryListAdapter: BeneficiaryListAdapter

    // Using viewModels delegate to obtain ViewModel instance getting the repository from the Dependency Injection
    private val viewModel: BeneficiaryViewModel by viewModels {
        BeneficiaryViewModelFactory(DependencyInjection.provideRepository(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create RecyclerView programmatically and set the layout manager
        recyclerView = RecyclerView(this).apply {
            layoutManager = LinearLayoutManager(this@BeneficiaryListActivity)
        }
        // Set the RecyclerView as the content view
        setContentView(recyclerView)

        // Initialize the adapter with an empty list
        beneficiaryListAdapter = BeneficiaryListAdapter(emptyList()) { beneficiary ->
            // Start the BeneficiaryDetailActivity when a beneficiary is clicked and use the intent to pass the beneficiary data
            val intent = Intent(this, BeneficiaryDetailsActivity::class.java)
            intent.putExtra("beneficiary", beneficiary)
            startActivity(intent)
        }
        recyclerView.adapter = beneficiaryListAdapter


        viewModel.beneficiary.observe(this) { beneficiaryList ->
            // Updates the adapter's data
            beneficiaryListAdapter.updateData(beneficiaryList)
        }
    }
}