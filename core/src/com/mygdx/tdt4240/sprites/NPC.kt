package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH

class NPC {

    private val screenMiddleWidth = getScreenMiddleWidth()
    private val boardSize = getBoardSize()
    private var boardX = GAME_WIDTH * 0.25f + (screenMiddleWidth-boardSize) * 0.5f
    private var boardY = (GAME_HEIGHT-boardSize) * 0.5f

    private val npcSize = boardSize/9
    private var npcX = boardX + 8 * npcSize
    private var npcY = boardY

    private fun getScreenMiddleWidth(): Float {
        var screenMiddleWidth = GAME_WIDTH * 0.5f
        if (screenMiddleWidth > GAME_HEIGHT) { screenMiddleWidth = GAME_HEIGHT
        }
        return screenMiddleWidth
    }

    private fun getBoardSize(): Float {
        return if (screenMiddleWidth < GAME_HEIGHT) { screenMiddleWidth * 0.9f } else { GAME_HEIGHT * 0.9f }
    }

    fun createNPC(): Sprite {
        val sprite = Sprite(Texture("gameView/npc.png"))
        sprite.setSize(npcSize, npcSize)
        sprite.setPosition(npcX, npcY)
        return sprite
    }

    fun updatePosition(npc: Sprite, i: Float, j: Float) {
        npcX = boardX + i * npcSize
        npcY = boardY + j * npcSize

        npc.setPosition(npcX, npcY)
    }
}