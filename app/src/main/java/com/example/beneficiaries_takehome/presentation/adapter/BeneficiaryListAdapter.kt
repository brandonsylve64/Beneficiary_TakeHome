package com.example.beneficiaries_takehome.presentation.adapter

import android.annotation.SuppressLint
import android.app.ActionBar.LayoutParams
import android.graphics.Typeface
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.beneficiaries_takehome.domain.models.Beneficiary
import com.example.beneficiaries_takehome.util.CommonUtil
import com.example.beneficiaries_takehome.util.CommonUtil.createHorizontalDivider
import com.example.beneficiaries_takehome.util.Constants.PADDING_16
import com.example.beneficiaries_takehome.util.Constants.TEXT_SIZE_16
import com.example.beneficiaries_takehome.util.Constants.TEXT_SIZE_18

class BeneficiaryListAdapter(
    private var beneficiaries: List<Beneficiary>,
    private val onClickListener: (Beneficiary) -> Unit
) : RecyclerView.Adapter<BeneficiaryListAdapter.BeneficiaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeneficiaryViewHolder {


        // Create item view programmatically
        val layout = LinearLayout(parent.context).apply {
            layoutParams =
                ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(PADDING_16, PADDING_16, PADDING_16, 0)
        }

        val text1 = CommonUtil.createTextView(parent.context, "", TEXT_SIZE_18, 0).apply {
            id = android.R.id.text1
            setTypeface(null, Typeface.BOLD)
        }
        val text2 = CommonUtil.createTextView(parent.context, "", TEXT_SIZE_16, 0).apply {
            id = android.R.id.text2
            setTypeface(null, Typeface.BOLD)
        }

        layout.addView(text1)
        layout.addView(text2)
        layout.addView(createHorizontalDivider(parent.context))

        return BeneficiaryViewHolder(layout)
    }

    override fun getItemCount(): Int = beneficiaries.size

    override fun onBindViewHolder(holder: BeneficiaryViewHolder, position: Int) {
        val beneficiary = beneficiaries[position]
        holder.bind(beneficiary)
        holder.itemView.setOnClickListener {
            onClickListener(beneficiary)
        }
    }

    // Update the adapter's data and notify the adapter that the data has changed
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newBeneficiaries: List<Beneficiary>) {
        beneficiaries = newBeneficiaries
        notifyDataSetChanged()
    }

    class BeneficiaryViewHolder(view: ViewGroup) : RecyclerView.ViewHolder(view) {

        // Get the text views from the view used naming convention "Tv" for TextView
        private val nameTv: TextView = view.findViewById(android.R.id.text1)
        private val typeTv: TextView = view.findViewById(android.R.id.text2)

        @SuppressLint("SetTextI18n")
        fun bind(beneficiary: Beneficiary) {
            // convert designation code to designation
            val designation = if (beneficiary.designationCode == "P") "Primary" else "Contingent"
            // Set the text for the text views
            nameTv.text = "${beneficiary.firstName} ${beneficiary.lastName}"
            typeTv.text = "${beneficiary.beneType} - $designation"
        }
    }
}