package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class SetTodo(
    val title:String,
    val done:Boolean
)
