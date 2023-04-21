package com.mygdx.tdt4240.api
import java.util.ArrayList

/**
 * Interface for the game API.
 */
interface API {
    fun submitUser(user: User)

    fun userExists(username: String, password: String): Boolean

    fun usernameExists(username: String): Boolean

    fun getHighscores(dataHolder: ArrayList<User>)

    fun getHighscore(username: String): Int

    fun updateHighscore(username: String, highscore: Int)

    fun checkDatabaseConnection()
}