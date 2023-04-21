package com.mygdx.tdt4240.utils

import com.mygdx.tdt4240.api.API

/**
 * Global variables used in the game.
 */
object Globals {
    var api: API? = null
    var currentUser: String = ""
    var connectionLost: Boolean = false
    var soundOn: Boolean = true
    var newGame: Boolean = true
}