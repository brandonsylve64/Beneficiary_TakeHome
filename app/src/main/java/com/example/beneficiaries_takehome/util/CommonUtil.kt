package com.example.beneficiaries_takehome.util

import android.content.Context
import android.graphics.Color
import android.telephony.PhoneNumberUtils
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setMargins
import com.example.beneficiaries_takehome.domain.models.BeneficiaryAddress
import com.example.beneficiaries_takehome.util.Constants.MARGINS_8
import com.example.beneficiaries_takehome.util.Constants.PADDING_16
import com.example.beneficiaries_takehome.util.Constants.TEXTVIEW_DEFAULT_COLOR_STATE_LIST
import com.example.beneficiaries_takehome.util.Constants.TEXT_SIZE_16
import java.text.SimpleDateFormat
import java.util.Locale

object CommonUtil {

    /**
     * Formats a date string from "MMddyyyy" to "MM/dd/yyyy".
     *
     * This function takes a date string in the format "MMddyyyy" and attempts to
     * convert it to the format "MM/dd/yyyy". If the parsing or formatting fails
     * for any reason, the original date string is returned.
     *
     * @param dateString The date string in the format "MMddyyyy".
     * @return The formatted date string in the format "MM/dd/yyyy", or the
     * original date string if parsing or formatting fails.
     */
    fun formatDate(dateString: String): String {
        return try {
            val originalFormat = SimpleDateFormat("MMddyyyy", Locale.US)
            val targetFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
            val date = originalFormat.parse(dateString)
            targetFormat.format(date)
        } catch (e: Exception) {
            dateString // Return the original string if parsing fails
        }
    }

    /**
     * Builds a formatted address string from a `BeneficiaryAddress` object.
     *
     * This function takes a `BeneficiaryAddress` object and constructs a formatted
     * address string that includes the first line of the mailing address, the
     * second line of the mailing address (if it is not null or blank), the city,
     * state code, zip code, and country.
     *
     * The address components are concatenated with newline characters to create
     * a multi-line address string.
     *
     * @param address The `BeneficiaryAddress` object containing the address information.
     * @return The formatted address string.
     */
    fun buildAddress(address: BeneficiaryAddress): String {
        return buildString {
            append(address.firstLineMailing)
            // add second line if it is not null or blank
            address.scndLineMailing?.takeIf { it.isNotBlank() && it != "null" }?.let {
                append("\n$it")
            }
            append("\n${address.city}, ${address.stateCode} ${address.zipCode}, ${address.country}")
        }
    }

    /**
     * Formats a phone number according to the current locale's country.
     *
     * This function uses the `PhoneNumberUtils.formatNumber()` method to format
     * the given phone number string based on the default country of the current
     * locale. This ensures that the phone number is formatted in a way that is
     * appropriate for the user's region.
     *
     * @param phoneNumber The phone number string to format.
     * @return The formatted phone number string, or the original string if
     * formatting fails.
     *
     * @see android.telephony.PhoneNumberUtils#formatNumber(String, String)
     */
    fun formatPhoneNumber(phoneNumber: String): String =
        PhoneNumberUtils.formatNumber(phoneNumber, Locale.getDefault().country)

    /**
     * Creates a TextView with specified text, text size, and margins.
     *
     * This function creates a new TextView instance with the given text, text size,
     * and margins. It sets the text color to the default color state list and applies
     * layout parameters to wrap the content and set the margins.
     *
     * @param context The context used to create the TextView.
     * @param text The text to display in the TextView.
     * @param textSize The size of the text in sp. Defaults to `TEXT_SIZE_16`.
     * @param margins The margins to apply to the TextView in pixels. Defaults to `MARGINS_8`.
     * @return The created TextView instance.
     */
    fun createTextView(
        context: Context,
        text: String,
        textSize: Float = TEXT_SIZE_16,
        margins: Int = MARGINS_8
    ): TextView {
        return TextView(context).apply {
            this.text = text
            this.textSize = textSize
            this.setTextColor(TEXTVIEW_DEFAULT_COLOR_STATE_LIST)
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(margins)
            }
        }
    }

    /**
     * Creates a horizontal divider View.
     *
     * This function creates a new View instance that acts as a horizontal divider.
     * It sets the layout parameters to match the parent width and a height of 1dp.
     * It also applies a top margin of 16dp and sets the background color to a light gray.
     *
     * @param context The context used to create the View.
     * @return The created View instance representing the horizontal divider.
     */
    fun createHorizontalDivider(context: Context): View {
        return View(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, resources.displayMetrics)
                    .toInt()
            ).apply {
                setMargins(0, PADDING_16, 0, 0)
            }
            this.setBackgroundColor(Color.parseColor("#BFBEBE"))
        }
    }
}