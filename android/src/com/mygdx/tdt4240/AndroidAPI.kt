package com.mygdx.tdt4240

import com.google.firebase.database.*
import com.mygdx.tdt4240.firebase.API
import com.mygdx.tdt4240.firebase.User
import java.util.*


class AndroidAPI : API {
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val users: DatabaseReference = database.getReference("users")

    override fun submitUser(user: User) {
        users.push().setValue(user)
    }

    override fun checkUserExists(username: String): Boolean {
        var exists = false
        var completed = false
        users.get().addOnCompleteListener { task ->
            val response = task.result.children
            for (child in response) {
                if (child.getValue(User::class.java)?.username.toString() == username) {
                    exists = true
                }
            }
            completed = true
        }
        while (!completed) {
            Thread.sleep(100)
        }
        return exists
    }

    override fun getHighscores(dataHolder: ArrayList<User>) {
        println("Getting highscores")
        users.get().addOnCompleteListener { task ->
            println("Got highscores")
            val response = task.result.children
            for (child in response) {
                child.getValue(User::class.java)?.let { dataHolder.add(it) }
            }
            dataHolder.sort()
        }
    }

}


