package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants

class RightBtn {
    private val rightBtnSize = Constants.GAME_HEIGHT * 0.16f
    private val rightBtnX = Constants.GAME_HEIGHT * 0.25f + rightBtnSize
    private val rightBtnY = Constants.GAME_HEIGHT *  0.05f

    fun createRightBtn(): Sprite {
        val sprite = Sprite(Texture("gameView/rightBtn.png"))
        sprite.setSize(rightBtnSize, rightBtnSize)
        sprite.setPosition(rightBtnX, rightBtnY)
        return sprite
    }

    fun rightBtnPressed(): Boolean {
        return Gdx.input.justTouched() && Gdx.input.x > rightBtnX && Gdx.input.x < rightBtnX + rightBtnSize && Gdx.input.y < Constants.GAME_HEIGHT - rightBtnY && Gdx.input.y > Constants.GAME_HEIGHT - rightBtnY - rightBtnSize
    }
}