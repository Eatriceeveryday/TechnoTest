package com.example.test2.common


data class ApiState(
    val error: String? = null,
    val result: Resource<Unit>? = null,
)


