package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants

class Speed {
    private val speedSize = Constants.GAME_HEIGHT * 0.1f
    private var speedX = Constants.GAME_WIDTH * 0.5f + Constants.GAME_HEIGHT * 0.35f
    private var speedY = Constants.GAME_HEIGHT * 0.05f

    fun createSpeed(): Sprite {
        var sprite = Sprite(Texture("gameView/speed.png"))
        sprite.setSize(speedSize, speedSize)
        sprite.setPosition(speedX, speedY)

        return sprite
    }

}