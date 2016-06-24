package com.example.ezequiel.kotlintest

interface ListContract {

    interface View {
        fun showList(items: List<String>)
    }

}