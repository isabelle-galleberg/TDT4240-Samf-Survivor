package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants

class Bomb {
    private val bombSize = Constants.GAME_HEIGHT * 0.1f
    private val bombX = Constants.GAME_WIDTH * 0.5f + Constants.GAME_HEIGHT * 0.35f //sett riktige verdier
    private val bombY = Constants.GAME_HEIGHT * 0.05f

    fun createBomb(): Sprite {
        val sprite = Sprite(Texture("gameView/NPC.png")) //sett riktig bilde
        sprite.setSize(bombSize, bombSize)
        sprite.setPosition(bombX, bombY)
        return sprite
    }
}