package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants

class Range {

    private val rangeSize = Constants.GAME_HEIGHT * 0.1f
    private var rangeX = Constants.GAME_WIDTH * 0.5f + Constants.GAME_HEIGHT * 0.35f
    private var rangeY = Constants.GAME_HEIGHT * 0.05f

    fun createRange(): Sprite {
        var sprite = Sprite(Texture("gameView/bombRange.png"))
        sprite.setSize(rangeSize, rangeSize)
        sprite.setPosition(rangeX, rangeY)
        return sprite
    }
}