package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants

class Fire {

    private val fireSize = Constants.GAME_HEIGHT * 0.1f
    private var fireX = Constants.GAME_WIDTH * 0.5f + Constants.GAME_HEIGHT * 0.35f
    private var fireY = Constants.GAME_HEIGHT * 0.05f
    fun createFire(): Sprite {
        var sprite = Sprite(Texture("gameView/fire.png"))
        sprite.setSize(fireSize, fireSize)
        sprite.setPosition(fireX, fireY)
        return sprite
    }
}