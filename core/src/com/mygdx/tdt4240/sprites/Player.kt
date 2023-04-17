package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH

class Player {
    private val playerSize = Constants.GAME_HEIGHT * 0.1f
    private var playerX = Constants.GAME_WIDTH * 0.5f - Constants.GAME_HEIGHT * 0.45f
    private var playerY = Constants.GAME_HEIGHT * 0.85f

    fun createPlayer(): Sprite {
        val sprite = Sprite(Texture("gameView/player.png"))
        sprite.setSize(playerSize, playerSize)
        sprite.setPosition(playerX, playerY)
        return sprite
    }

    fun updatePosition(player: Sprite, i: Float, j: Float) {
        playerX = Constants.GAME_HEIGHT * 0.05f + Constants.GAME_WIDTH * 0.5f - Constants.GAME_HEIGHT * 0.5f + i * Constants.GAME_HEIGHT * 0.1f
        playerY = Constants.GAME_HEIGHT * 0.05f + j * Constants.GAME_HEIGHT * 0.1f

        player.setPosition(playerX, playerY)
    }
}