package com.example.ezequiel.kotlintest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.text.TextUtils
import android.widget.EditText
import com.google.firebase.database.*
import java.util.*

class NewNameActivity : AppCompatActivity() {

    lateinit var etNewName: EditText
    lateinit var fabNewName: FloatingActionButton

    var rootRef: DatabaseReference = FirebaseDatabase.getInstance().reference
    var nameRef: DatabaseReference = rootRef.child("names")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_name)

        etNewName = findViewById(R.id.et_name) as EditText
        fabNewName = findViewById(R.id.fab_new_name) as FloatingActionButton
        fabNewName.setOnClickListener({
            if (TextUtils.isEmpty(etNewName.text)) {
                etNewName.error = "Required!"
            } else {
                var key = nameRef.push().key
                var user: User = User()
                user.name = etNewName.text.toString().trim()
                var userValues: Map<String, Any> = user.toMap()

                val childUpdates = HashMap<String, Any>()
                childUpdates.put("/users/" + key, userValues)
                rootRef.updateChildren(childUpdates)
                finish()
            }
        })
    }

    override fun onStart() {
        super.onStart()

        nameRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }
}
