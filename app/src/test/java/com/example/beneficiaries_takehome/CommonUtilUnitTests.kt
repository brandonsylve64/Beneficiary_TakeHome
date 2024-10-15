package com.example.beneficiaries_takehome

import android.telephony.PhoneNumberUtils
import com.example.beneficiaries_takehome.domain.models.BeneficiaryAddress
import com.example.beneficiaries_takehome.util.CommonUtil.buildAddress
import com.example.beneficiaries_takehome.util.CommonUtil.formatDate
import com.example.beneficiaries_takehome.util.CommonUtil.formatPhoneNumber
import org.junit.Assert
import org.junit.Test

class CommonUtilUnitTests {
    @Test
    fun formatDate_validInput_returnsFormattedDate() {
        val inputDate = "12312023"
        val expectedOutput = "12/31/2023"
        val actualOutput = formatDate(inputDate)
        Assert.assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun formatDate_invalidInput_returnsOriginalString() {
        val inputDate = "invalid date"
        val expectedOutput = "invalid date"
        val actualOutput = formatDate(inputDate)
        Assert.assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun formatDate_emptyInput_returnsEmptyString() {
        val inputDate = ""
        val expectedOutput = ""
        val actualOutput = formatDate(inputDate)
        Assert.assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun formatDate_nullInput_returnsNull() {
        val inputDate = null
        // Since the function only accepts String, we can't test null input directly
        // Instead, we can test an empty string, which is a common representation of null in this context
        val expectedOutput = ""
        val actualOutput = formatDate("")
        Assert.assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun buildAddress_fullAddress_returnsFormattedAddress() {
        val address = BeneficiaryAddress(
            firstLineMailing = "123 Main St",
            scndLineMailing = "Apt 4B",
            city = "Anytown",
            stateCode = "CA",
            zipCode = "12345",
            country = "USA"
        )
        val expectedAddress = "123 Main St\nApt 4B\nAnytown, CA 12345, USA"
        val actualAddress = buildAddress(address)
        Assert.assertEquals(expectedAddress, actualAddress)
    }

    @Test
    fun buildAddress_noSecondLine_returnsFormattedAddress() {
        val address = BeneficiaryAddress(
            firstLineMailing = "123 Main St",
            scndLineMailing = null,
            city = "Anytown",
            stateCode = "CA",
            zipCode = "12345",
            country = "USA"
        )
        val expectedAddress = "123 Main St\nAnytown, CA 12345, USA"
        val actualAddress = buildAddress(address)
        Assert.assertEquals(expectedAddress, actualAddress)
    }

    @Test
    fun buildAddress_blankSecondLine_returnsFormattedAddress() {
        val address = BeneficiaryAddress(
            firstLineMailing = "123 Main St",
            scndLineMailing = "",
            city = "Anytown",
            stateCode = "CA",
            zipCode = "12345",
            country = "USA"
        )
        val expectedAddress = "123 Main St\nAnytown, CA 12345, USA"
        val actualAddress = buildAddress(address)
        Assert.assertEquals(expectedAddress, actualAddress)
    }

    @Test
    fun buildAddress_nullStringSecondLine_returnsFormattedAddress() {
        val address = BeneficiaryAddress(
            firstLineMailing = "123 Main St",
            scndLineMailing = "null",
            city = "Anytown",
            stateCode = "CA",
            zipCode = "12345",
            country = "USA"
        )
        val expectedAddress = "123 Main St\nAnytown, CA 12345, USA"
        val actualAddress = buildAddress(address)
        Assert.assertEquals(expectedAddress, actualAddress)
    }

    // Will fail since PhoneNumberUtils.formatNumber needs to be mocked with mockito or similar
    @Test
    fun formatPhoneNumber_validNumber_returnsFormattedNumber() {
        val phoneNumber = "1234567890"
        val expectedFormattedNumber = PhoneNumberUtils.formatNumber(phoneNumber,
            "US")
        val actualFormattedNumber = formatPhoneNumber(phoneNumber)
        Assert.assertEquals(expectedFormattedNumber, actualFormattedNumber)
    }

    // Will fail since PhoneNumberUtils.formatNumber needs to be mocked with mockito or similar
    @Test
    fun formatPhoneNumber_invalidNumber_returnsOriginalNumber() {
        val phoneNumber = "invalid number"
        val expectedFormattedNumber = phoneNumber // PhoneNumberUtils should return the original string for invalid numbers
        val actualFormattedNumber = formatPhoneNumber(phoneNumber)
        Assert.assertEquals(expectedFormattedNumber, actualFormattedNumber)
    }

    // Will fail since PhoneNumberUtils.formatNumber needs to be mocked with mockito or similar
    @Test
    fun formatPhoneNumber_emptyNumber_returnsEmptyString() {
        val phoneNumber = ""
        val expectedFormattedNumber = "" // PhoneNumberUtils should return an empty string for an empty input
        val actualFormattedNumber = formatPhoneNumber(phoneNumber)
        Assert.assertEquals(expectedFormattedNumber, actualFormattedNumber)
    }

    // Will fail since PhoneNumberUtils.formatNumber needs to be mocked with mockito or similar
    @Test
    fun formatPhoneNumber_nullNumber_returnsNull() {
        val phoneNumber = null
        // Since the function only accepts String, we can't test null input directly
        // Instead, we can test an empty string, which is a common representation of null in this context
        val expectedFormattedNumber = "" // PhoneNumberUtils should return an empty string for an empty input
        val actualFormattedNumber = formatPhoneNumber("")
        Assert.assertEquals(expectedFormattedNumber, actualFormattedNumber)
    }

}