package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants

class UpBtn {
    private val upBtnSize = Constants.GAME_HEIGHT * 0.16f
    private val upBtnX = Constants.GAME_HEIGHT * 0.25f
    private val upBtnY = Constants.GAME_HEIGHT * 0.05f + upBtnSize

    fun createUpBtn(): Sprite {
        val sprite = Sprite(Texture("gameView/arrowVerticalBtn.png"))
        sprite.setSize(upBtnSize, upBtnSize)
        sprite.setPosition(upBtnX, upBtnY)
        return sprite
    }

    fun upBtnPressed(): Boolean {
        return Gdx.input.justTouched() && Gdx.input.x > upBtnX && Gdx.input.x < upBtnX + upBtnSize && Gdx.input.y < Constants.GAME_HEIGHT - upBtnY && Gdx.input.y > Constants.GAME_HEIGHT - upBtnY - upBtnSize
    }
}