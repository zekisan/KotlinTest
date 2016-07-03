package com.example.ezequiel.kotlintest

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.item.view.*
import java.util.*

class MyListAdapter(val mContext: Context) : RecyclerView.Adapter<MyListAdapter.ViewHolder>() {

    var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("users")
    lateinit var mChildEventListener: ChildEventListener

    var userIds = ArrayList<String>()
    var users = ArrayList<User>()

    init {


        var childEventListener: ChildEventListener = object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                Log.d("MyListAdapter", "onChildAdded:" + dataSnapshot.key)

                var user = dataSnapshot.getValue(User::class.java)

                userIds.add(dataSnapshot.key)
                users.add(user)
                notifyItemInserted(users.size - 1)
                notifyDataSetChanged()
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String) {
                Log.d("MyListAdapter", "onChildChanged:" + dataSnapshot.key)

                var newUser = dataSnapshot.getValue(User::class.java)
                var userKey = dataSnapshot.key

                var userIndex = userIds.indexOf(userKey)
                if (userIndex > -1) {
                    users[userIndex] = newUser

                    notifyItemChanged(userIndex)
                } else {
                    Log.w("MyListAdapter", "onChildChanged:unknown_child:" + userKey)
                }
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                Log.d("MyListAdapter", "onChildRemoved:" + dataSnapshot.key)

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so remove it.
                val userKey = dataSnapshot.key

                // [START_EXCLUDE]
                val userIndex = userIds.indexOf(userKey)
                if (userIndex > -1) {
                    // Remove data from the list
                    userIds.removeAt(userIndex)
                    users.removeAt(userIndex)

                    // Update the RecyclerView
                    notifyItemRemoved(userIndex)
                } else {
                    Log.w("MyListAdapter", "onChildRemoved:unknown_child:" + userKey)
                }
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String) {
                Log.d("MyListAdapter", "onChildMoved:" + dataSnapshot.key)

                // A comment has changed position, use the key to determine if we are
                // displaying this comment and if so move it.
                val movedUser = dataSnapshot.getValue(User::class.java)
                val userKey = dataSnapshot.key
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("MyListAdapter", "postComments:onCancelled", databaseError.toException())
                Toast.makeText(mContext, "Failed to load users.",
                        Toast.LENGTH_SHORT).show();
            }
        }
        databaseReference.addChildEventListener(childEventListener)

        mChildEventListener = childEventListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindNames(users[position])
    }

    override fun getItemCount() = 1

    fun cleanupListener() {
        if (mChildEventListener != null) {
            databaseReference.removeEventListener(mChildEventListener)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindNames(user: User) {
            with(user) {
                itemView.textView.text = name
            }
        }
    }
}
