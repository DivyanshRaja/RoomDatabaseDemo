package com.raja.roomdatabase.utils

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.widget.TextView
import android.widget.Toast

object UtilKotlin {
    fun showMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun setFont(textView: TextView, start: Int, end: Int, msg: String) {

        val spannable = SpannableString(msg)

        spannable.setSpan(
            RelativeSizeSpan(1.1f), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        spannable.setSpan(
            ForegroundColorSpan(Color.WHITE),
            start, // start
            end, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            start, // start
            end, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        textView.text = spannable
    }

}