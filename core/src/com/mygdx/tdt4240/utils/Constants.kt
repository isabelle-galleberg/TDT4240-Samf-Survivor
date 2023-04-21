package com.mygdx.tdt4240.utils

import com.badlogic.gdx.Gdx

/**
 * Constants used in the game.
 */
object Constants {
    val GAME_WIDTH = Gdx.graphics.width.toFloat()
    val GAME_HEIGHT = Gdx.graphics.height.toFloat()
    val FONT_SIZE = GAME_HEIGHT / 250

    val INPUT_WIDTH = GAME_WIDTH * 0.6f
    val INPUT_HEIGHT = GAME_HEIGHT * 0.15f

    val BUTTON_HEIGHT = GAME_HEIGHT * 0.12f
}
