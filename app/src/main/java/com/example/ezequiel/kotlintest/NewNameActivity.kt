package com.example.ezequiel.kotlintest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.widget.EditText

class NewNameActivity : AppCompatActivity() {

    lateinit var etNewName: EditText
    lateinit var fabNewName: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_name)

        etNewName = findViewById(R.id.et_name) as EditText
        fabNewName = findViewById(R.id.fab_new_name) as FloatingActionButton
        fabNewName.setOnClickListener({
            finish()
        })
    }
}
