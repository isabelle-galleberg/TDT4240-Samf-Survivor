package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants

class LeftBtn {
    private val leftBtnSize = Constants.GAME_HEIGHT * 0.16f
    private val leftBtnX = Constants.GAME_HEIGHT * 0.25f - leftBtnSize
    private val leftBtnY = Constants.GAME_HEIGHT * 0.05f

    fun createLeftBtn(): Sprite {
        val sprite = Sprite(Texture("gameView/arrowHorizontalBtn.png"))
        sprite.setSize(leftBtnSize, leftBtnSize)
        sprite.setPosition(leftBtnX, leftBtnY)
        sprite.setFlip(true, false)
        return sprite
    }

    fun leftBtnPressed(): Boolean {
        return Gdx.input.justTouched() && Gdx.input.x > leftBtnX && Gdx.input.x < leftBtnX + leftBtnSize && Gdx.input.y < Constants.GAME_HEIGHT - leftBtnY && Gdx.input.y > Constants.GAME_HEIGHT - leftBtnY - leftBtnSize
    }
}