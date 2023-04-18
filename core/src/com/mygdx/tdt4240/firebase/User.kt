package com.mygdx.tdt4240.firebase

class User(val username: String, val password: String, val highscore: Int ) : Comparable<User> {

    // Firebase requires an empty constructor
    constructor() : this("", "", 0)

    override fun compareTo(other: User): Int {
        if (this.highscore > other.highscore) {
            return -1
        } else if (this.highscore < other.highscore) {
            return 1
        }
        return username.compareTo(other.username)
    }
}