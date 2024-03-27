package com.example.test2.model.home

import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("result")
    val result: HomeResult
)

data class HomeResult(
    @field:SerializedName("greeting")
    val greeting: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("saldo")
    val saldo: Int,

    @field:SerializedName("point")
    val point: Int,

    @field:SerializedName("qrcode")
    val qrcode: String,

    @field:SerializedName("banner")
    val banner: List<String>
)
