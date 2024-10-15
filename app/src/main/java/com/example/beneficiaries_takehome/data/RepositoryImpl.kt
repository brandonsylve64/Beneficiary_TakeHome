package com.example.beneficiaries_takehome.data

import android.content.Context
import android.util.Log
import com.example.beneficiaries_takehome.domain.Repository
import com.example.beneficiaries_takehome.domain.models.Beneficiary
import com.example.beneficiaries_takehome.domain.models.BeneficiaryAddress
import org.json.JSONArray
import java.io.IOException

class RepositoryImpl(private val context: Context) : Repository {

    /**
     * Retrieves a list of beneficiaries from a JSON file.
     *
     * This function loads the JSON data from the "Beneficiaries.json" asset file,
     * parses it into a list of `Beneficiary` objects, and returns the list.
     * If the JSON file is not found or cannot be parsed, an empty list is returned.
     *
     * @return A list of `Beneficiary` objects, or an empty list if the JSON data
     * is unavailable or invalid.
     */
    override fun getBeneficiaries(): List<Beneficiary> {
        val jsonString = loadJsonFromAsset(context, "Beneficiaries.json")
        return if (jsonString != null) {
            parseBeneficiariesJson(jsonString)
        } else {
            emptyList()
        }
    }

    /**
     * Loads a JSON string from an asset file.
     *
     * This function attempts to open the specified asset file, read its contents
     * into a byte array, and then convert the byte array to a string using UTF-8
     * encoding. If any error occurs during this process, such as the file not
     * being found or an I/O exception, the function will print the stack trace
     * and return null.
     *
     * @param context The context used to access the assets.
     * @param fileName The name of the asset file to load.
     * @return The JSON string read from the asset file, or null if an error
     * occurs.
     */
    private fun loadJsonFromAsset(context: Context, fileName: String): String? {
        return try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    /**
     * Parses a JSON string into a list of Beneficiary objects.
     *
     * This function takes a JSON string representing an array of beneficiaries
     * and converts it into a list of `Beneficiary` objects. It iterates through
     * the JSON array, extracts the data for each beneficiary, creates a
     * `Beneficiary` object, and adds it to the list.
     *
     * @param jsonString The JSON string to parse.
     * @return A list of `Beneficiary` objects parsed from the JSON string.
     * @throws JSONException If an error occurs during JSON parsing.
     */
    private fun parseBeneficiariesJson(jsonString: String): List<Beneficiary> {
        val beneficiaries = mutableListOf<Beneficiary>()
        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val addressObject = jsonObject.getJSONObject("beneficiaryAddress")
            val beneficiaryAddress = BeneficiaryAddress(
                city = addressObject.getString("city"),
                country = addressObject.getString("country"),
                firstLineMailing = addressObject.getString("firstLineMailing"),
                scndLineMailing = addressObject.optString("scndLineMailing"),
                stateCode = addressObject.getString("stateCode"),
                zipCode = addressObject.getString("zipCode")
            )
            val beneficiary = Beneficiary(
                firstName = jsonObject.getString("firstName"),
                middleName = jsonObject.optString("middleName"),
                lastName = jsonObject.getString("lastName"),
                beneType = jsonObject.getString("beneType"),
                designationCode = jsonObject.getString("designationCode"),
                socialSecurityNumber = jsonObject.getString("socialSecurityNumber"),
                dateOfBirth = jsonObject.getString("dateOfBirth"),
                phoneNumber = jsonObject.getString("phoneNumber"),
                beneficiaryAddress = beneficiaryAddress
            )
            Log.d("RepositoryImpl", "Parsed beneficiary: $beneficiary")
            beneficiaries.add(beneficiary)
        }
        return beneficiaries
    }
}