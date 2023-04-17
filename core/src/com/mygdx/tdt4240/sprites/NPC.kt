package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants

class NPC {
    private val nPCSize = Constants.GAME_HEIGHT * 0.1f
    private var nPCX = Constants.GAME_WIDTH * 0.5f + Constants.GAME_HEIGHT * 0.35f
    private var nPCY = Constants.GAME_HEIGHT * 0.05f

    fun createNPC(): Sprite {
        val sprite = Sprite(Texture("gameView/NPC.png"))
        sprite.setSize(nPCSize, nPCSize)
        sprite.setPosition(nPCX, nPCY)
        return sprite
    }

    fun updatePosition(npc: Sprite, i: Float, j: Float) {
        nPCX = Constants.GAME_HEIGHT * 0.05f + Constants.GAME_WIDTH * 0.5f - Constants.GAME_HEIGHT * 0.5f + i * Constants.GAME_HEIGHT * 0.1f
        nPCY = Constants.GAME_HEIGHT * 0.05f + j * Constants.GAME_HEIGHT * 0.1f

        npc.setPosition(nPCX, nPCY)
    }
}