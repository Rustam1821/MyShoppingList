package com.example.myshoppinglist.presentation

import androidx.databinding.BindingAdapter
import com.example.myshoppinglist.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorInputName")
fun bindErrorInputName(textInput: TextInputLayout, isError: Boolean) {
    val message = if (isError) textInput.context.getString(R.string.error_input_name) else null
    textInput.error = message
}

@BindingAdapter("errorInputCount")
fun bindErrorInputCount(textInput: TextInputLayout, isError: Boolean) {
    val message = if (isError) textInput.context.getString(R.string.error_input_count) else null
    textInput.error = message
}