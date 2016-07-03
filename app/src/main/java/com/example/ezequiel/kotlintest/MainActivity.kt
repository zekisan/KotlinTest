package com.example.ezequiel.kotlintest

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MyListAdapter
    lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recycler_view) as RecyclerView


        fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener({ startActivity(Intent(this, NewNameActivity::class.java)) })
    }

    override fun onStart() {
        super.onStart()
        adapter = MyListAdapter(this)
        recyclerView.adapter = adapter
    }

    override fun onStop() {
        super.onStop()

        adapter.cleanupListener()
    }
}
