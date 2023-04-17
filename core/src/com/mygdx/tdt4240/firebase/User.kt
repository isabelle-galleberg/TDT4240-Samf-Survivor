package com.mygdx.tdt4240.firebase

class User(val username: String, val password: String, val score: Int ) : Comparable<User> {

    // Firebase requires an empty constructor
    constructor() : this("", "", 0)

    override fun toString(): String {
        return "$username: $score"
    }

    override fun compareTo(other: User): Int {
        if (this.score > other.score) {
            return -1
        } else if (this.score < other.score) {
            return 1
        }
        return username.compareTo(other.username)
    }
}