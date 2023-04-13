package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants

class Player {
    private val playerSize = Constants.GAME_HEIGHT * 0.1f
    private val playerX = Constants.GAME_WIDTH * 0.5f - Constants.GAME_HEIGHT * 0.45f
    private val playerY = Constants.GAME_HEIGHT * 0.85f

    fun createPlayer(): Sprite {
        val sprite = Sprite(Texture("gameView/player.png"))
        sprite.setSize(playerSize, playerSize)
        sprite.setPosition(playerX, playerY)
        return sprite
    }
}