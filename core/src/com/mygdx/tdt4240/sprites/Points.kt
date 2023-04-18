package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants

class Points {
    private val pointSize = Constants.GAME_HEIGHT * 0.1f
    private var pointX = Constants.GAME_WIDTH * 0.5f + Constants.GAME_HEIGHT * 0.35f
    private var pointY = Constants.GAME_HEIGHT * 0.05f

    fun createPoints(): Sprite {
        var sprite = Sprite(Texture("gameView/points.png"))
        sprite.setSize(pointSize, pointSize)
        sprite.setPosition(pointX, pointY)
        return sprite
    }
}