package com.example.beneficiaries_takehome.util

import android.content.res.ColorStateList
import android.graphics.Color

object Constants {

    const val TEXT_SIZE_18 = 18f
    const val TEXT_SIZE_16 = 16f

    const val MARGINS_8 = 8

    const val PADDING_16 = 16
    const val PADDING_32 = 32

    private val TEXT_STATES = arrayOf(
        intArrayOf(android.R.attr.state_enabled), // enabled
        intArrayOf(-android.R.attr.state_enabled), // disabled
        intArrayOf(-android.R.attr.state_checked), // unchecked
        intArrayOf(android.R.attr.state_pressed)  // pressed
    )

    private val TEXT_COLORS = intArrayOf(
        Color.BLACK,
        Color.RED,
        Color.GREEN,
        Color.BLUE
    )
    val TEXTVIEW_DEFAULT_COLOR_STATE_LIST = ColorStateList(TEXT_STATES, TEXT_COLORS)
}