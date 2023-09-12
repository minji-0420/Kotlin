package com.example.lifecycle

import android.content.Context
import android.widget.Toast

internal fun Context.showToast(message: String, time: Int) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()