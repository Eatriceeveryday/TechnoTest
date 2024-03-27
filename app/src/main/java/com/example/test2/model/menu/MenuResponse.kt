package com.example.test2.model.menu

import com.google.gson.annotations.SerializedName

data class MenuResponse(
    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("result")
    val result: MenuResult
)

data class MenuResult(
    @field:SerializedName("categories")
    val categories: List<Category>,
)

data class Category(
    @field:SerializedName("category_name")
    val categoryName: String,

    @field:SerializedName("menu")
    val menu: List<Menu>,
)

data class Menu(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("photo")
    val photo: String,

    @field:SerializedName("price")
    val status: Int,
)