package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH

class Player {
    private val playerSize = GAME_HEIGHT * 0.1f
    private var playerX = GAME_WIDTH * 0.5f - GAME_HEIGHT * 0.45f
    private var playerY = GAME_HEIGHT * 0.85f

    fun createPlayer(): Sprite {
        val sprite = Sprite(Texture("gameView/player.png"))
        sprite.setSize(playerSize, playerSize)
        sprite.setPosition(playerX, playerY)
        return sprite
    }

    fun updatePosition(player: Sprite, i: Float, j: Float) {
        playerX = GAME_HEIGHT * 0.05f + GAME_WIDTH * 0.5f - GAME_HEIGHT * 0.5f + i * GAME_HEIGHT * 0.1f
        playerY = GAME_HEIGHT * 0.05f + j * GAME_HEIGHT * 0.1f

        player.setPosition(playerX, playerY)
    }
}