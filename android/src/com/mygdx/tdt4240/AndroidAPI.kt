package com.mygdx.tdt4240

import com.google.firebase.database.*
import com.mygdx.tdt4240.api.API
import com.mygdx.tdt4240.api.User
import com.mygdx.tdt4240.utils.Globals.connectionLost
import java.util.*

/**
 * Class for handling the API calls to the Firebase database.
 */
class AndroidAPI : API {
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val users: DatabaseReference = database.getReference("users")
    private val connectedRef = database.getReference(".info/connected")

    override fun submitUser(user: User) {
        users.push().setValue(user)
    }

    override fun userExists(username: String, password: String): Boolean {
        var exists = false
        var completed = false
        users.get().addOnCompleteListener { task ->
            val response = task.result.children
            for (child in response) {
                if (child.getValue(User::class.java)?.username.toString() == username &&
                    child.getValue(User::class.java)?.password.toString() == password) {
                    exists = true
                }
            }
            completed = true
        }
        // wait for the database to respond
        while (!completed) {
            Thread.sleep(100)
        }
        return exists
    }

    override fun usernameExists(username: String): Boolean {
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
        // wait for the database to respond
        while (!completed) {
            Thread.sleep(100)
        }
        return exists
    }

    override fun getHighscores(dataHolder: ArrayList<User>) {
        users.get().addOnCompleteListener { task ->
            val response = task.result.children
            for (child in response) {
                child.getValue(User::class.java)?.let { dataHolder.add(it) }
            }
            dataHolder.sort()
        }
    }

    override fun getHighscore(username: String): Int {
        var highscore = 0
        var completed = false
        users.get().addOnCompleteListener { task ->
            val response = task.result.children
            for (child in response) {
                if (child.getValue(User::class.java)?.username.toString() == username) {
                    highscore = child.getValue(User::class.java)?.highscore ?: 0
                }
            }
            completed = true
        }
        // wait for the database to respond
        while (!completed) {
            Thread.sleep(100)
        }
        return highscore
    }

    override fun updateHighscore(username: String, highscore: Int) {
        var completed = false
        users.get().addOnCompleteListener { task ->
            val response = task.result.children
            for (child in response) {
                if (child.getValue(User::class.java)?.username.toString() == username) {
                    if ((child.getValue(User::class.java)?.highscore ?: 0) < highscore){
                        child.ref.child("highscore").setValue(highscore)
                    }
                }
            }
            completed = true
        }
        // wait for the database to respond
        while (!completed) {
            Thread.sleep(100)
        }
    }

    override fun checkDatabaseConnection() {
        connectedRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val connected = dataSnapshot.getValue(Boolean::class.java)
                if (connected == false) {
                    connectionLost = true
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                println("Listener was cancelled")
            }
        })
    }

}


