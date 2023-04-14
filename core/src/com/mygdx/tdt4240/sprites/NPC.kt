package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants

class NPC {
    private val nPCSize = Constants.GAME_HEIGHT * 0.1f
    private val nPCX = Constants.GAME_WIDTH * 0.5f + Constants.GAME_HEIGHT * 0.35f
    private val nPCY = Constants.GAME_HEIGHT * 0.05f

    fun createNPC(): Sprite {
        val sprite = Sprite(Texture("gameView/NPC.png"))
        sprite.setSize(nPCSize, nPCSize)
        sprite.setPosition(nPCX, nPCY)
        return sprite
    }
}