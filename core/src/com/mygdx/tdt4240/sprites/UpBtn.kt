package com.mygdx.tdt4240.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.tdt4240.utils.Constants.GAME_HEIGHT
import com.mygdx.tdt4240.utils.Constants.GAME_WIDTH

class UpBtn {
    private val screenSideWidth = GAME_WIDTH * 0.25f
    private val upBtnSize = screenSideWidth * 0.3f
    private val upBtnX = (screenSideWidth - upBtnSize) * 0.5f
    private val upBtnY = GAME_HEIGHT * 0.05f + upBtnSize

    fun createUpBtn(): Sprite {
        val sprite = Sprite(Texture("gameView/arrowVerticalBtn.png"))
        sprite.setSize(upBtnSize, upBtnSize)
        sprite.setPosition(upBtnX, upBtnY)
        return sprite
    }

    fun upBtnPressed(): Boolean {
        return Gdx.input.isTouched && Gdx.input.x > upBtnX && Gdx.input.x < upBtnX + upBtnSize && Gdx.input.y < GAME_HEIGHT - upBtnY && Gdx.input.y > GAME_HEIGHT - upBtnY - upBtnSize
    }
}