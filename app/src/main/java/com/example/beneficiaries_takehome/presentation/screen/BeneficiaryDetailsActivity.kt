package com.example.beneficiaries_takehome.presentation.screen

import android.os.Build
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import com.example.beneficiaries_takehome.domain.models.Beneficiary
import com.example.beneficiaries_takehome.util.CommonUtil
import com.example.beneficiaries_takehome.util.Constants.PADDING_32

class BeneficiaryDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the beneficiary data from the intent
        val beneficiary: Beneficiary? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("beneficiary", Beneficiary::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra("beneficiary")
            }

        // Create a LinearLayout to display the beneficiary data
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16)
        }

        // Add beneficiary details to the layout
        beneficiary?.let {
            val formattedAddress = CommonUtil.buildAddress(it.beneficiaryAddress)
            val formattedPhoneNumber = CommonUtil.formatPhoneNumber(it.phoneNumber)
            layout.apply {
                addView(
                    CommonUtil.createTextView(
                        this@BeneficiaryDetailsActivity,
                        "Name: ${it.firstName} ${it.middleName ?: ""} ${it.lastName}"
                    )
                )
                addView(
                    CommonUtil.createTextView(
                        this@BeneficiaryDetailsActivity,
                        "SSN: ${it.socialSecurityNumber}"
                    )
                )
                addView(
                    CommonUtil.createTextView(
                        this@BeneficiaryDetailsActivity,
                        "Date of Birth: ${CommonUtil.formatDate(it.dateOfBirth)}"
                    )
                )
                addView(
                    CommonUtil.createTextView(
                        this@BeneficiaryDetailsActivity,
                        "Phone: $formattedPhoneNumber"
                    )
                )
                addView(
                    CommonUtil.createTextView(
                        this@BeneficiaryDetailsActivity, "Address:"
                    )
                )
                formattedAddress.split("\n").forEach { line ->
                    addView(CommonUtil.createTextView(
                        this@BeneficiaryDetailsActivity, line
                    ).apply { setPadding(PADDING_32, 0, 0, 0) })
                }
            }
        } ?: layout.addView(
            CommonUtil.createTextView(
                this@BeneficiaryDetailsActivity,
                "Beneficiary not found"
            )
        )

        setContentView(layout)
    }

}