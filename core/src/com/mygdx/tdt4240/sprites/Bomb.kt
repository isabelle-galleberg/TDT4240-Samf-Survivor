package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants
import java.util.*

class Bomb {
    private val bombSize = Constants.GAME_HEIGHT * 0.1f
    private var bombX = Constants.GAME_WIDTH * 0.5f + Constants.GAME_HEIGHT * 0.35f
    private var bombY = Constants.GAME_HEIGHT * 0.05f

    fun createBomb(): Sprite {
        var sprite = Sprite(Texture("gameView/bomb.png"))
        sprite.setSize(bombSize, bombSize)
        sprite.setPosition(bombX, bombY)

        return sprite
    }



}