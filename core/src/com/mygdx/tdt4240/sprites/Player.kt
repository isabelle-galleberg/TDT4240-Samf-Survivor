package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH

/**
 * Class for creating and handling the player.
 */
class Player {
    private val screenMiddleWidth = getScreenMiddleWidth()
    private val boardSize = getBoardSize()
    private var boardX = GAME_WIDTH * 0.25f + (screenMiddleWidth-boardSize) * 0.5f
    private var boardY = (GAME_HEIGHT-boardSize) * 0.5f

    private val playerSize = boardSize/9
    private var playerX = boardX
    private var playerY = boardY + 8 * playerSize

    private fun getScreenMiddleWidth(): Float {
        var screenMiddleWidth = GAME_WIDTH * 0.5f
        if (screenMiddleWidth > GAME_HEIGHT) { screenMiddleWidth = GAME_HEIGHT }
        return screenMiddleWidth
    }

    private fun getBoardSize(): Float {
        return if (screenMiddleWidth < GAME_HEIGHT) { screenMiddleWidth * 0.9f } else { GAME_HEIGHT * 0.9f }
    }

    fun createPlayer(): Sprite {
        val sprite = Sprite(Texture("gameView/player.png"))
        sprite.setSize(playerSize, playerSize)
        sprite.setPosition(playerX, playerY)
        return sprite
    }

    fun updatePosition(player: Sprite, i: Float, j: Float) {
        playerX = boardX + i * playerSize
        playerY = boardY + j * playerSize

        player.setPosition(playerX, playerY)
    }
}