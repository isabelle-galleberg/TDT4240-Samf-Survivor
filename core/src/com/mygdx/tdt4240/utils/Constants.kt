package com.mygdx.tdt4240.utils

import com.badlogic.gdx.Gdx

object Constants {
    const val GAME_TITLE = "Samf Survivor"
    val GAME_WIDTH = Gdx.graphics.width.toFloat()
    val GAME_HEIGHT = Gdx.graphics.height.toFloat()
    val FONT_SIZE = GAME_HEIGHT / 250

    val INPUT_WIDTH = GAME_WIDTH * 0.6f
    val INPUT_HEIGHT = GAME_HEIGHT * 0.15f

    val BUTTON_HEIGHT = GAME_HEIGHT * 0.12f

    val TUTORIAL_WIDTH=(GAME_WIDTH /10).toInt()*7
    val TUTORIAL_HEIGHT=(GAME_HEIGHT /8).toInt()*6

    const val SAMF_RED= "A03033"
    const val SAMF_RED_DARK="5B0E10"
    const val BLACK="190304"
    const val CREAM="BBAEAE"
    const val WHITE="F1F1F1"


}
