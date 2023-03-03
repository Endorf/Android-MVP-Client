package com.mvp.sharednotes.login

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("onDoneAction")
fun EditText.onDoneAction(action: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->

        actionId == EditorInfo.IME_ACTION_DONE
            .also { action() }
    }
}