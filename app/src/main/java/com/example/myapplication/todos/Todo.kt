package com.example.myapplication.todos

import android.text.Editable

data class Todo(
    val title: String,
    var isFinished: Boolean = false
)