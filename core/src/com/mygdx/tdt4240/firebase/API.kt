package com.mygdx.tdt4240.firebase
import java.util.ArrayList

interface API {
    fun submitUser(user: User)
    fun getHighscores(dataHolder: ArrayList<User>)
    fun checkUserExists(username: String): Boolean
}