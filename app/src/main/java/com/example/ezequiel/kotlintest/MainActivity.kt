package com.example.ezequiel.kotlintest

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.google.firebase.database.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var recyclerView: RecyclerView
    var myRef: DatabaseReference = FirebaseDatabase.getInstance().reference
    var conditionRef: DatabaseReference = myRef.child("name")
    lateinit var adapter: MyListAdapter
    lateinit var namesList: List<String>
    lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        namesList = ArrayList()

        recyclerView = findViewById(R.id.recycler_view) as RecyclerView
        adapter = MyListAdapter(namesList)

        fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener({ startActivity(Intent(this, NewNameActivity::class.java)) })
    }

    override fun onStart() {
        super.onStart()

        conditionRef.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                //throw UnsupportedOperationException()
            }

            override fun onDataChange(p0: DataSnapshot?) {
                var t: GenericTypeIndicator<List<String>> = object : GenericTypeIndicator<List<String>>() {}
                namesList = p0!!.getValue(t)
            }
        })
    }
}
