package com.example.ezequiel.kotlintest

import java.util.*

class User {

    var name: String? = null

    fun toMap(): Map<String, Any> {
        val result = HashMap<String, Any>()
        result.put("name", name!!)
        return result
    }
}
