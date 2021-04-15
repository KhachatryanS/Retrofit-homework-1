package com.example.retrofit

class UserModel{

    var code: Int? = null
    var data: Data? = null
    var meta: Any? = null

    data class Data(
        val categories: List<Category>,
        val description: String,
        val discount_amount: String,
        val id: Int,
        val image: String,
        val name: String,
        val price: String,
        val status: Boolean
    ) {
        data class Category(
            val id: Int,
            val name: String
        )
    }

}