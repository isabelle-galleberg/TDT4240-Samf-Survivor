package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants
import java.util.*

class Bomb {
    private val bombSize = Constants.GAME_HEIGHT * 0.1f
    private val bombX = Constants.GAME_WIDTH * 0.5f + Constants.GAME_HEIGHT * 0.35f
    private val bombY = Constants.GAME_HEIGHT * 0.05f

    fun createBomb(): Sprite {
        val sprite = Sprite(Texture("gameView/bomb.png"))
        sprite.setSize(bombSize, bombSize)
        sprite.setPosition(bombX, bombY)
        Timer().schedule(object : TimerTask() {
            override fun run() {

            }
        }, (2000))

        return sprite
    }


}